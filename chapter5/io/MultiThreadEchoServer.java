package chapter5.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadEchoServer {
    static ExecutorService exe= Executors.newCachedThreadPool();
    public static class HandleMessage implements Runnable{
        Socket clientSocket;

        public HandleMessage(Socket socket){
            this.clientSocket=socket;
        }
        @Override
        public void run() {
            BufferedReader is=null;
            PrintWriter os=null;
            try{
                is=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                os=new PrintWriter(clientSocket.getOutputStream(),true);
                String inputLine=null;
                long begin=System.currentTimeMillis();
                while((inputLine=is.readLine())!=null){
                    os.println(inputLine);
                }
                long end = System.currentTimeMillis();
                System.out.println("speed"+(end-begin)+"ms");

            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    if(is!=null){
                        is.close();
                    }
                    if(os!=null){
                        os.close();
                    }
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();

                }

            }
        }
    }

    public static void main(String[] args) {
        ServerSocket echoServer=null;
        Socket clientSocket=null;
        try{
            echoServer=new ServerSocket(8000);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try{
            clientSocket=echoServer.accept();
            System.out.println(clientSocket.getRemoteSocketAddress()+"connect");
            exe.execute(new HandleMessage(clientSocket));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
