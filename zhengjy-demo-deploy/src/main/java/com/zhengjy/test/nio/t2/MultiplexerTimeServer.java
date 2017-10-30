package com.zhengjy.test.nio.t2;

import org.apache.commons.lang.StringUtils;
import org.springframework.expression.spel.ast.Selection;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by zhengjy on 2017/10/29.
 */
public class MultiplexerTimeServer implements Runnable {

    private Selector selector;

    private ServerSocketChannel serverSocketChannel;

    private volatile boolean stop;

    /**
     * 初始化多路复用器，绑定端口
     * @param port
     */
    public MultiplexerTimeServer(int port){
        try {
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            //设置为异步非阻塞模式
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.bind(new InetSocketAddress(port));
            //将serverSocketChannel注册到selector中
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("server start port :"+port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void stop(){
        this.stop = true;
    }

    @Override
    public void run() {
        //循环遍历select
        while (!stop){
            try {
                //无论是否有读写事件发生，selector每隔1s都被唤醒、
                selector.select();
                //返回SelectionKey集合
                Collection<SelectionKey> selectionKeys = selector.selectedKeys();
                for (Iterator it = selectionKeys.iterator();it.hasNext();){
                    SelectionKey key = (SelectionKey) it.next();
                    try{
                        handleInput(key);
                    }catch (Exception e){
                        if(key != null){
                            key.cancel();
                            if(key.channel() != null){
                                try {
                                    key.channel().close();
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }
                            }
                        }
                    }
                }
                //通过对就绪的channel集合进行迭代，可以通过网络的异步读写
                selectionKeys.stream().filter( key ->{
                    try{
                        handleInput(key);
                    }catch (Exception e){
                        if(key != null){
                            key.cancel();
                            if(key.channel() != null){
                                try {
                                    key.channel().close();
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }
                            }
                        }
                    }
                    return true;
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //多路复用器关闭后，所有注册在上面的channel和pipe等资源都会自动去注册并关闭，所以不需要重复释放资源
        if(selector != null){
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void handleInput(SelectionKey key) throws IOException {
        if(key.isValid()){
            //处理新的请求
            if(key.isAcceptable()){
                ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                //通过ServerSocketChannel的accept接收客户端的连接请求并创建SocketChannel实例，完成上述相当于完成了TCP
                //三次握手，TCP物理链路正式建成。
                SocketChannel socketChannel = serverSocketChannel.accept();
                // 设置成非阻塞
                socketChannel.configureBlocking(false);
                socketChannel.register(selector,SelectionKey.OP_READ);
            }
            if(key.isReadable()){
                //读取客户端的请求消息
                SocketChannel socketChannel = (SocketChannel) key.channel();
                //事先无法得知客户端发送的码流大小，开辟1M的缓冲区
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                //然后调用SocketChannel的read方法读取请求码流，read是非阻塞的，因为socketChannel.configureBlocking(false)已经非阻塞模式
                int readBytes = socketChannel.read(byteBuffer);
                if(readBytes > 0){
                    //将缓冲区当前的limit设置为poition,poition设置为0，用于后续对缓冲区的读取操作
                    byteBuffer.flip();
                    //根据缓冲区的大小创建字节数组
                    byte[] bytes = new byte[byteBuffer.remaining()];
                    //将缓冲区可读的字节数组复制到新创建的字节数组中，
                    byteBuffer.get(bytes);
                    String body = new String(bytes,"UTF-8");
                    System.out.println("server receive order :"+body);
                    //如果请求指令“QUERY TIME ORDER”，则把服务器的当前时间编码后返回给客户端
                    String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
                    doWrite(socketChannel,currentTime);
                }else if(readBytes < 0){
                    key.cancel();
                    socketChannel.close();
                }
            }

        }
    }

    /**
     * 将应答消息异步发送给客户端
     * @param socketChannel
     * @param str
     * @throws IOException
     */
    private void doWrite(SocketChannel socketChannel,String str) throws IOException {
        if(StringUtils.isNotBlank(str)){
            byte[] bytes = str.getBytes();
            //将字符串编码成字节数组
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            //将字符数组复制到缓冲区，
            writeBuffer.put(bytes);
            //
            writeBuffer.flip();
            //将缓冲区中的字节数组发送出去
            socketChannel.write(writeBuffer);
        }
    }
}
