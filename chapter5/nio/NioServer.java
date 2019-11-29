package chapter5.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NioServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel ssc=ServerSocketChannel.open();
        ssc.configureBlocking(false);
        Selector selector=Selector.open();
        ssc.bind(new InetSocketAddress(8000));
        ssc.register(selector,SelectionKey.OP_ACCEPT);

        while(selector.select()>0){
            Iterator i=selector.selectedKeys().iterator();
            while(i.hasNext()){
                SelectionKey sk= (SelectionKey) i.next();
                i.remove();
                if(sk.isAcceptable()){
                    SocketChannel sc= ssc.accept();
                    sc.configureBlocking(false);
                    sc.register(selector,SelectionKey.OP_READ);
                }else if(sk.isReadable()){
                    SocketChannel sc= (SocketChannel) sk.channel();
                    ByteBuffer bf=ByteBuffer.allocate(1024);
                    int len=0;
                    while((len=sc.read(bf))>0){
                        bf.flip();
                        System.out.println(new String(bf.array(),0,len));
                        bf.clear();
                    }
                }



            }


        }
    }
}