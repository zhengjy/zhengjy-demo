package com.zhengjy.test.socket;

import com.fasterxml.jackson.databind.util.ByteBufferBackedInputStream;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by zhengjy on 2017/11/25.
 */
public class SingleFileHTTPServer {

    private static final Logger log = Logger.getLogger("SingleFileHTTPServer");

    private final byte[] content;
    private final byte[] header;
    private final int port;
    private final String encoding;


    public SingleFileHTTPServer(String data,String encoding,String mimeType,int port) throws UnsupportedEncodingException {
        this(data.getBytes(encoding),encoding,mimeType,port);
    }

    public SingleFileHTTPServer(byte[] data,String encoding,String mimeType,int port){
        this.content = data;
        this.port = port;
        this.encoding = encoding;
        String header = "HTTP/1.0 200 OK\r\n " +
                "Server:OneFile 2.0\r\n " +
                "Content-length:"+ this.content.length +"\r\n " +
                "Content-type:"+mimeType +";charset="+encoding +"\r\n\r\n";
        this.header = header.getBytes(Charset.forName("US-ASCII"));
    }

    public void start(){
        ExecutorService pool = Executors.newFixedThreadPool(100);
        try {
            ServerSocket serverSocket = new ServerSocket(this.port);
            log.info("Accepting connections on port "+ serverSocket.getLocalPort());
            log.info("Data to be sent:");
            log.info("content = :"+new String (this.content,encoding));
            while (true){
                try {
                    Socket socket = serverSocket.accept();
                    pool.submit(new HTTPHandler(socket));

                }catch (IOException e){
                    log.log(Level.WARNING,"Exeption accepting connection",e);
                }catch (RuntimeException e){
                    log.log(Level.SEVERE,"Unexpected error",e);
                }
            }
        }catch (IOException e){
            log.log(Level.SEVERE,"Could not start server",e);
        }
    }

    private class HTTPHandler implements Callable<Void>{
        private final Socket socket;

        private HTTPHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public Void call() throws IOException {
            OutputStream out = null;
            try {
                out = new BufferedOutputStream(socket.getOutputStream());
                InputStream in = new BufferedInputStream(socket.getInputStream());
                //只读取第一行，这是我们需要的全部内容
                StringBuilder sb = new StringBuilder(80);
                while (true){
                    int c = in.read();
                    if(c == '\r' || c =='\n' || c == -1){
                        break;
                    }
                    sb.append((char)c);
                }
                //如果是HTTP/1.0或以后的版本，则发送一个MIME头部
                if(sb.toString().indexOf("HTTP/") != -1){
                    out.write(header);
                }
                out.write(content);
                out.flush();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                log.log(Level.WARNING,"Error writing to client",e);
            }finally {
                socket.close();
            }
            return null;
        }
    }

    public static void main(String[] args) {
        int port;
        try {
            port = Integer.parseInt(args[1]);
            if(port < 1 || port > 65535){
                port = 80;
            }
        }catch (RuntimeException e){
            port = 80;
        }
        String encoding = "UTF-8";
        if(args.length > 2){
            encoding = args[2];
        }
        try {
            Path path = Paths.get(args[0]);
            byte[] data = Files.readAllBytes(path);
            String contentType  = URLConnection.getFileNameMap().getContentTypeFor(args[0]);
            SingleFileHTTPServer server = new SingleFileHTTPServer(data,encoding,contentType,port);
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

























