package chapter5.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket client=null;
        PrintWriter write=null;
        BufferedReader reader=null;
        try{
            client=new Socket();
            client.connect(new InetSocketAddress("localhost",8000));
            write =new PrintWriter(client.getOutputStream(),true);
            write.println("hello");
            Thread.sleep(20000);
            write.println("motherFucker");
            write.flush();

            reader=new BufferedReader(new InputStreamReader(client.getInputStream()));
            System.out.println("from server:"+reader.readLine()+reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            write.close();
            reader.close();
            client.close();
        }
    }
}
