//package com.zhengjy.test.netty.t3;
//
//import java.net.InetSocketAddress;
//import java.nio.channels.spi.SelectorProvider;
//import java.util.concurrent.Executor;
//import java.util.concurrent.ThreadFactory;
//
//import io.netty.bootstrap.ServerBootstrap;
//import io.netty.buffer.ByteBuf;
//import io.netty.channel.*;
//import io.netty.channel.ChannelHandler.Sharable;
//import io.netty.channel.nio.NioEventLoopGroup;
//import io.netty.channel.socket.nio.NioServerSocketChannel;
//import io.netty.channel.socket.nio.NioSocketChannel;
//import io.netty.handler.codec.AsciiString;
//import io.netty.handler.codec.http.*;
//import io.netty.util.AttributeKey;
//import io.netty.util.concurrent.DefaultExecutorServiceFactory;
//import io.netty.util.concurrent.DefaultThreadFactory;
//
//import io.netty.util.concurrent.ExecutorServiceFactory;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.apache.log4j.BasicConfigurator;
//
//public class TestHTTPNetty {
//    static {
//        BasicConfigurator.configure();
//    }
//
//    public static void main(String[] args) throws Exception {
//        //这就是主要的服务启动器
//        ServerBootstrap serverBootstrap = new ServerBootstrap();
//
//        //=======================下面我们设置线程池（代码已经详细讲解过，就不再赘述了）
//        EventLoopGroup bossLoopGroup = new NioEventLoopGroup(1);
//        ExecutorServiceFactory threadFactory = new DefaultExecutorServiceFactory("work thread pool");
//        int processorsNumber = Runtime.getRuntime().availableProcessors();
//        EventLoopGroup workLoogGroup = new NioEventLoopGroup(processorsNumber * 2, threadFactory, SelectorProvider.provider());
//        serverBootstrap.group(bossLoopGroup , workLoogGroup);
//
//        //========================下面我们设置我们服务的通道类型（代码已经详细讲解过，就不再赘述了）
//        serverBootstrap.channel(NioServerSocketChannel.class);
//
//        //========================设置处理器
//        serverBootstrap.childHandler(new ChannelInitializer<NioSocketChannel>() {
//            /* (non-Javadoc)
//             * @see io.netty.channel.ChannelInitializer#initChannel(io.netty.channel.Channel)
//             */
//            @Override
//            protected void initChannel(NioSocketChannel ch) throws Exception {
//                //我们在socket channel pipeline中加入http的编码和解码器
//                ch.pipeline().addLast(new HttpResponseEncoder());
//                ch.pipeline().addLast(new HttpRequestDecoder());
//                ch.pipeline().addLast(new HTTPServerHandler());
//            }
//        });
//
//        serverBootstrap.option(ChannelOption.SO_BACKLOG, 128);
//        serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
//        ChannelFuture f = serverBootstrap.bind(new InetSocketAddress("0.0.0.0", 8888)).sync();
//        f.channel().closeFuture().sync();
//        bossLoopGroup.shutdownGracefully();
//        workLoogGroup.shutdownGracefully();
//    }
//}
//
///**
// * @author yinwenjie
// */
//@Sharable
//class HTTPServerHandler extends ChannelInboundHandlerAdapter {
//    /**
//     * 日志
//     */
//    private static Log LOGGER = LogFactory.getLog(HTTPServerHandler.class);
//
//    /**
//     * 由于一次httpcontent可能没有传输完全部的请求信息。所以这里要做一个连续的记录
//     * 然后在channelReadComplete方法中（执行了这个方法说明这次所有的http内容都传输完了）进行处理
//     */
//    private static AttributeKey<StringBuffer> CONNTENT = AttributeKey.valueOf("content");
//
//    /* (non-Javadoc)
//     * @see io.netty.channel.ChannelInboundHandlerAdapter#channelRead(io.netty.channel.ChannelHandlerContext, java.lang.Object)
//     */
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        /*
//         * 在测试中，我们首先取出客户端传来的参数、URL信息，并且返回给一个确认信息。
//         * 要使用HTTP服务，我们首先要了解Netty中http的格式，如下：
//         * ----------------------------------------------
//         * | http request | http content | http content |
//         * ----------------------------------------------
//         *
//         * 所以通过HttpRequestDecoder channel handler解码后的msg可能是两种类型：
//         * HttpRquest：里面包含了请求head、请求的url等信息
//         * HttpContent：请求的主体内容
//         * */
//        if(msg instanceof HttpRequest) {
//            HttpRequest request = (HttpRequest)msg;
//            HttpMethod method = request.method();
//
//            AsciiString methodName = method.name();
//            String url = request.uri();
//            HTTPServerHandler.LOGGER.info("methodName = " + methodName + " && url = " + url);
//        }
//
//        //如果条件成立，则在这个代码段实现http请求内容的累加
//        if(msg instanceof HttpContent) {
//            StringBuffer content = ctx.attr(HTTPServerHandler.CONNTENT).get();
//            if(content == null) {
//                content = new StringBuffer();
//                ctx.attr(HTTPServerHandler.CONNTENT).set(content);
//            }
//
//            HttpContent httpContent = (HttpContent)msg;
//            ByteBuf contentBuf = httpContent.content();
//            String preContent = contentBuf.toString(io.netty.util.CharsetUtil.UTF_8);
//            content.append(preContent);
//        }
//    }
//
//    /* (non-Javadoc)
//     * @see io.netty.channel.ChannelInboundHandlerAdapter#channelReadComplete(io.netty.channel.ChannelHandlerContext)
//     */
//    @Override
//    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//        HTTPServerHandler.LOGGER.info("super.channelReadComplete(ChannelHandlerContext ctx)");
//
//        /*
//         * 一旦本次http请求传输完成，则可以进行业务处理了。
//         * 并且返回响应
//         * */
//        StringBuffer content = ctx.attr(HTTPServerHandler.CONNTENT).get();
//        HTTPServerHandler.LOGGER.info("http客户端传来的信息为：" + content);
//
//        //开始返回信息了
//        String returnValue = "return response";
//        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
//        HttpHeaders httpHeaders = response.headers();
//        //这些就是http response 的head信息咯，参见http规范。另外您还可以设置自己的head属性
//        httpHeaders.add("param", "value");
//        response.headers().set(org.springframework.http.HttpHeaders.CONTENT_TYPE, "text/plain");
//        //一定要设置长度，否则http客户端会一直等待（因为返回的信息长度客户端不知道）
//        response.headers().set(org.springframework.http.HttpHeaders.CONTENT_LENGTH, returnValue.length()+"");
//
//        ByteBuf responseContent = response.content();
//        responseContent.writeBytes(returnValue.getBytes("UTF-8"));
//
//        //开始返回
//        ctx.writeAndFlush(response);
//    }
//
//
//
//}