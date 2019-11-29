package chapter5.aio;

import java.awt.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class AioServer {
    public final static int PORT = 8000;
    private AsynchronousServerSocketChannel server;
    public AioServer() throws IOException {
        server=AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(PORT));
    }
    public void start(){
        System.out.println("Server listen on "+PORT);
        //注册事件和事件完成后的处理器
        server.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
            final ByteBuffer buffer = ByteBuffer.allocate(1024);

            @Override
            public void completed(AsynchronousSocketChannel result, Object attachment) {
                System.out.println(Thread.currentThread().getName());
                Future<Integer> writeResult = null;
                try{
                    buffer.clear();
                    result.read(buffer).get(100, TimeUnit.SECONDS);
                    buffer.flip();
                    writeResult=result.write(buffer);
                    System.out.println("AioServer return");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }finally{
                    try{
                        server.accept(null,this);
                        writeResult.get();
                        result.close();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void failed(Throwable exc, Object attachment) {
                System.out.println("failed: "+exc);
            }
        });
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        new AioServer().start();
        while (true){
            Thread.sleep(1000);
        }
    }
}
