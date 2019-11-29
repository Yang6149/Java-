package chapter5.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class AioClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        final AsynchronousSocketChannel client = AsynchronousSocketChannel.open();
        System.out.println("begin to connect");
        client.connect(new InetSocketAddress("localhost", 8000), null, new CompletionHandler<Void, Object>() {
            @Override
            public void completed(Void result, Object attachment) {
                System.out.println("connect success");
                client.write(ByteBuffer.wrap("hello!".getBytes()), null, new CompletionHandler<Integer, Object>() {
                    @Override
                    public void completed(Integer result, Object attachment) {
                        try{
                            System.out.println("write success");
                            ByteBuffer buffer = ByteBuffer.allocate(1024);
                            client.read(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
                                @Override
                                public void completed(Integer result, ByteBuffer attachment) {
                                    System.out.println("read success");
                                    buffer.flip();
                                    System.out.println(new String(buffer.array()));
                                    try{
                                        client.close();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void failed(Throwable exc, ByteBuffer attachment) {
                                    System.out.println("read fail");
                                }
                            });
                        }catch (Exception e){
                            e.printStackTrace();;
                        }
                    }

                    @Override
                    public void failed(Throwable exc, Object attachment) {
                        System.out.println("write fail");
                    }
                });
            }

            @Override
            public void failed(Throwable exc, Object attachment) {
                System.out.println("connect fail");
            }
        });
        Thread.sleep(1000);
    }
}
