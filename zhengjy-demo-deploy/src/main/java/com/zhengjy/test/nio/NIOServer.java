package com.zhengjy.test.nio;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
/**
 *  NIO服务端
 * Created by zhengjy on 2017/2/22.
 */
public class NIOServer {
    /**
     *
     1、打开ServerSocketChannel，用于监听客户端的连接，他是所有客户端连接的父管道
     ServerSocketChannel acceptorSvr = ServerSocketChannel.open();
     2、绑定监听端，设置连接为非阻塞模式
     acceptorSvr.socket().bind(new InetSocketAddress(InetAddress.getByName("ip"),port));
     acceptorSvr.configureBlocking(false);
     3、创建Reactor线程，创建多路复用器并启动线程
     Selector selector = Selector.open();
     new Thread(new ReactorTask()).start();
     4、将ServerSocketChannel注册到Reactor线程的多路复用器Selector上，监听accept事件
     SelectionKey key = acceptorSvr.register(select,SelectionKey.OP_ACCEPT,ioHandler);
     5、多路复用器在线程run方法的无线循环体内轮询准备就绪的key
     int num =selector.select();
     Set selectKeys = selector.selectedKeys();
     Interator it = selectKeys.iterator();
     while(it.hasNext()){
     SelectionKey key = it.next();
     }
     6、多路复用器监听有新的客户端接入，处理新的接入请求，完成TCP三次握手，建立物理链接
     ServerSocketChannel server = (ServerSocketChannel)key.channle();
     SocketChannel socketChannel = server.accept();
     7、设置客户端链路为非阻塞模式
     socketChannel.cconfigureBlocking(false);
     socketChannel.socket().setReuseAddress(true);
     8、将新接入的客户端连接注册到Reactor线程的多路复用器上，监听读操作，读取客户端发送的网络消息
     SelectionKey key = socketChannel.register(selector,SelectionKey.OP_READ,ioHandler);
     9、异步读取客户端请求消息到缓冲区
     int readNumber = socketChannel.read(receiveedBuffer);
     10、对ByteBuffer进行编解码，如果有半包消息指针reset，继续读取后续的报文，将解码成功的消息封装成task，投递到业务线程池中，进行业务逻辑编排
     // 服务器可读取消息:得到事件发生的Socket通道
     SocketChannel socketChannel = (SocketChannel) key.channel();
     // 创建读取的缓冲区
     ByteBuffer buffer = ByteBuffer.allocate(1024);
     socketChannel.read(buffer);
     byte[] data = buffer.array();
     String msg = new String(data);
     System.out.println("服务器端收到客户端信息:" + msg);
     // 将消息回送给客户端
     ByteBuffer outBuffer = ByteBuffer.wrap(new String("服务器端发送客户端消息: world.").getBytes());

     11、将对象encode成ByteBuffer，调用SocketChannel的异步write接口，将消息异步发生给客户端
     socketChannel.write(outBuffer);




     */

    //通道管理器
    private Selector selector;

    /**
     * 获得一个ServerSocket通道，并对该通道做一些初始化的工作
     * @param port  绑定的端口号
     * @throws IOException
     */
    public void initServer(int port) throws IOException {
        // 获得一个ServerSocket通道
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        // 设置通道为非阻塞
        serverChannel.configureBlocking(false);
        // 将该通道对应的ServerSocket绑定到port端口
        serverChannel.socket().bind(new InetSocketAddress(port));
        // 获得一个通道管理器
        this.selector = Selector.open();
        //将通道管理器和该通道绑定，并为该通道注册SelectionKey.OP_ACCEPT事件,注册该事件后，
        //当该事件到达时，selector.select()会返回，如果该事件没到达selector.select()会一直阻塞。
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    /**
     * 采用轮询的方式监听selector上是否有需要处理的事件，如果有，则进行处理
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    public void listen() throws IOException {
        System.out.println("服务端启动成功！");
        // 轮询访问selector
        while (true) {
            //当注册的事件到达时，方法返回；否则,该方法会一直阻塞
            selector.select();
            // 获得selector中选中的项的迭代器，选中的项为注册的事件
            Iterator ite = this.selector.selectedKeys().iterator();
            while (ite.hasNext()) {
                SelectionKey key = (SelectionKey) ite.next();
                // 删除已选的key,以防重复处理
                ite.remove();
                // 客户端请求连接事件
                if (key.isAcceptable()) {
                    ServerSocketChannel server = (ServerSocketChannel) key
                            .channel();
                    // 获得和客户端连接的通道
                    SocketChannel channel = server.accept();
                    // 设置成非阻塞
                    channel.configureBlocking(false);

                    //在这里可以给客户端发送信息哦
                    channel.write(ByteBuffer.wrap(new String("向客户端发送了一条信息").getBytes()));
                    //在和客户端连接成功之后，为了可以接收到客户端的信息，需要给通道设置读的权限。
                    channel.register(this.selector, SelectionKey.OP_READ);

                    // 获得了可读的事件
                } else if (key.isReadable()) {
                    read(key);
                }else if(key.isWritable()){
                    SocketChannel socketChanne = (SocketChannel) key.channel();
                    ByteBuffer buffer = (ByteBuffer) key.attachment();
                    if(!buffer.hasRemaining()){
                        //用下一行重新填充缓冲区
                        buffer.rewind();
                        //得到上一行的首字符
                        int first = buffer.get();
                        //准备改写缓冲区中的数据
                        buffer.rewind();
                        //寻找rotation中新的首字符位置
                    }

                }

            }

        }
    }
    /**
     * 处理读取客户端发来的信息 的事件
     * @param key
     * @throws IOException
     */
    public void read(SelectionKey key) throws IOException{
        // 服务器可读取消息:得到事件发生的Socket通道
        SocketChannel channel = (SocketChannel) key.channel();
        // 创建读取的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(10);
        channel.read(buffer);
        byte[] data = buffer.array();
        String msg = new String(data).trim();
        System.out.println("服务端收到信息："+msg);
        ByteBuffer outBuffer = ByteBuffer.wrap(msg.getBytes());
        channel.write(outBuffer);// 将消息回送给客户端
    }

    /**
     * 启动服务端测试
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        NIOServer server = new NIOServer();
        server.initServer(8000);
        server.listen();
    }
}
