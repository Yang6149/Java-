package chapter5.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.Date;

public class NioClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        SocketChannel sc=SocketChannel.open(new InetSocketAddress("localhost",8000));

        sc.configureBlocking(false);

        ByteBuffer byteBuffer=ByteBuffer.allocate(10240);

        byteBuffer.put(new Date().toString().getBytes());
        System.out.println(new Date().toString());
        byteBuffer.flip();

        sc.write(byteBuffer);

        Thread.sleep(2000);
        byteBuffer.clear();
        byteBuffer.put(new Date().toString().getBytes());
        System.out.println(new Date().toString());
        sc.write(byteBuffer);
        sc.close();

    }
}
