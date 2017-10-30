package com.zhengjy.test.nio.t2;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by zhengjy on 2017/10/29.
 */
public class TimeClienthandle implements Runnable{

    private String host;
    private int port;
    private Selector selector;
    private SocketChannel socketChannel;
    private volatile boolean stop;


    public TimeClienthandle(String host,int port) throws IOException {
        this.host = host;
        this.port = port;
        //初始化多路复用器
        selector = Selector.open();
        //初始化SocketChannel
        socketChannel = SocketChannel.open();
        //设置为异步非阻塞模式
        socketChannel.configureBlocking(false);
    }

    @Override
    public void run() {
        try {
            //发送连接请求
            doConnent();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            while (!stop){
                selector.select();
                Collection selectionKeys = selector.keys();
                selectionKeys.stream().filter(v -> {
                    SelectionKey key = null;
                    try{
                        //当有就绪的Channel时
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
                    return false;
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
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
        //判断SelectionKey处于什么状态
        if(key.isValid()){
            SocketChannel socketChannel = (SocketChannel) key.channel();
            //处于连接状态，表示服务端已经返回ACK应答消息。
            if(key.isConnectable()){
                //表示客户端连接成功
                if(socketChannel.finishConnect()){
                    //将SocketChannel注册到多路复用器上，注册SelectionKey.OP_READ操作，
                    socketChannel.register(selector,SelectionKey.OP_READ);
                    //发送请求消息给服务端
                    doWrite(socketChannel);
                }
            }
            //服务端应答，如果客户端接收到服务端的应答消息，则SOcketChannel是可读的
            if(key.isReadable()){
                //无法判定应答流的大小，预分配1M的接收缓冲区用于读取应答消息
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                //异步读取操作
                int readByte = socketChannel.read(readBuffer);
                //由于是异步的需要做判定
                if(readByte > 0){
                    ////将缓冲区当前的limit设置为poition,poition设置为0，用于后续对缓冲区的读取操作
                    readBuffer.flip();
                    //根据缓冲区的大小创建字节数组
                    byte[] bytes = new byte[readBuffer.remaining()];
                    //将缓冲区可读的字节数组复制到新创建的字节数组中，
                    readBuffer.get(bytes);
                    //解码
                    String body = new String(bytes,"UTF-8");
                    System.out.println("Now is : " + body);
                    this.stop = true;
                }else if(readByte < 0 ){
                    key.cancel();
                    socketChannel.close();
                }
            }
        }
    }

    public void doConnent() throws IOException {
        //如果连接成功，则将SocketChannel注册到多路复用器selector上，注册SelectionKey.OP_READ
        if (socketChannel.connect(new InetSocketAddress("127.0.0.1",8088))){
            socketChannel.register(selector,SelectionKey.OP_READ);
            doWrite(socketChannel);
         //如果没有直接连成功，则说明服务器没有返回TCP握手应答消息，但这并不代表失败，需要将SocketChannel注册到多路复用器
         //Selector上，注册SelectionKey.OP_CONNECT,当服务端返回TCP syn-ack消息后，Selector就能够轮询这个SocketChannel
         //处于连接就绪状态
        }else {
            socketChannel.register(selector,SelectionKey.OP_CONNECT);
        }
    }

    /**
     *
     * @param socketChannel
     * @param
     * @throws IOException
     */
    private void doWrite(SocketChannel socketChannel) throws IOException {
        byte[] req = "QUERY TIME ORDER".getBytes();
        //编码
        ByteBuffer writeBuffer = ByteBuffer.allocate(req.length);
        //写入缓冲区
        writeBuffer.put(req);
        writeBuffer.flip();
        //发送，由于发送是异步的，所以会存在“半包写”的问题
        socketChannel.write(writeBuffer);
        //如果缓冲区的消息全部发送出完成，则打印
        if(!writeBuffer.hasRemaining()){
            System.out.println("Send order 2 server successd.");
        }
    }
}
