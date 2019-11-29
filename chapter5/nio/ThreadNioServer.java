package chapter5.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadNioServer {
    private Selector selector;
    private ExecutorService tp= Executors.newCachedThreadPool();
    public static Map<Socket,Long> time_stat=new HashMap<>(10240);

    private void startServer() throws IOException {
        selector = Selector.open();
        ServerSocketChannel ssc=ServerSocketChannel.open();
        ssc.configureBlocking(false);
        ssc.socket().bind(new InetSocketAddress("localhost",8000));
        ssc.register(selector, SelectionKey.OP_ACCEPT);
        for(;;){
            selector.select();

            Set readKey = selector.selectedKeys();
            Iterator i =readKey.iterator();
            long e=0;
            while (i.hasNext()) {
                SelectionKey sk= (SelectionKey) i.next();
                i.remove();

                if (sk.isAcceptable()) {
                    doAccept(sk);
                }
                else if(sk.isValid()&&sk.isReadable()){
                    if(!time_stat.containsKey(((SocketChannel)sk.channel()).socket()))
                        time_stat.put(((SocketChannel)sk.channel()).socket(),System.currentTimeMillis());//初始载入时间
                    doRead(sk);

                } else if (sk.isValid() && sk.isWritable()) {

                    doWrite(sk);

                    e=System.currentTimeMillis();//结束时间
                    long b=time_stat.remove(((SocketChannel)sk.channel()).socket());
                    System.out.println("speed = "+(e-b)+"ms");

                }
            }
        }
    }
    private void doAccept(SelectionKey sk){
        ServerSocketChannel ssc= (ServerSocketChannel) sk.channel();
        SocketChannel sc;
        try{
            sc=ssc.accept();
            sc.configureBlocking(false);

            SelectionKey clientKey=sc.register(selector,SelectionKey.OP_READ);
            EchoClient echoClient = new EchoClient();
            clientKey.attach(echoClient);

            System.out.println("from"+sc.socket().getInetAddress().getHostAddress());
        } catch (IOException e) {
            System.out.println("Failed to accept new client");
            e.printStackTrace();
        }
    }
    private void doRead(SelectionKey sk){
        ByteBuffer buf=ByteBuffer.allocate(8192);
        SocketChannel sc= (SocketChannel) sk.channel();
        int len;

        try{
            len=sc.read(buf);
            if(len<0){
                disconnect(sk);
                return;
            }
        } catch (IOException e) {
            System.out.println("Failed to read from client");
            e.printStackTrace();
            disconnect(sk);
            return;
        }
        buf.flip();
        tp.execute(new HandlerMsg(sk,buf));
    }
    private void doWrite(SelectionKey sk){
        SocketChannel channel = (SocketChannel) sk.channel();
        EchoClient echoClient = (EchoClient) sk.attachment();
        LinkedList<ByteBuffer> outq = echoClient.getOutputQueue();

        ByteBuffer bb=outq.getLast();
        try{



            System.out.println(new String(bb.array()));
            int len = channel.write(bb);
            if(len==-1){
                disconnect(sk);
                return;

            }
            if (bb.remaining() == 0) {
                System.out.println("byteBuffer is zero  removeLast outq");
                outq.removeLast();
            }
        } catch (IOException e) {
            System.out.println("Failed to write client");
            e.printStackTrace();
            disconnect(sk);
        }

        if(outq.size()==0){
            sk.interestOps(SelectionKey.OP_READ);
        }
    }
    public void disconnect(SelectionKey sk){
        //sk.interestOps(0);
    }

    public static void main(String[] args) throws IOException {
        ThreadNioServer s=new ThreadNioServer();
        s.startServer();
    }

    class HandlerMsg implements Runnable{
        SelectionKey sk;
        ByteBuffer bb;
        public HandlerMsg(SelectionKey sk,ByteBuffer bb){
            this.sk=sk;
            this.bb=bb;
        }
        @Override
        public void run() {
            EchoClient echoClient = (EchoClient) sk.attachment();
            echoClient.enqueue(bb);
            sk.interestOps(SelectionKey.OP_READ|SelectionKey.OP_WRITE);
            selector.wakeup();
        }
    }
}

class EchoClient{
    private LinkedList<ByteBuffer> outq;
    EchoClient(){
        outq=new LinkedList<>();
    }
    public LinkedList<ByteBuffer> getOutputQueue(){
        return outq;
    }
    public void enqueue(ByteBuffer bb){
        outq.addFirst(bb);
    }
}