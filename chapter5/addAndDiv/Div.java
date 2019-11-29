package chapter5.addAndDiv;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Div implements Runnable {
    static BlockingQueue<Msg> bq=new LinkedBlockingQueue();

    @Override
    public void run() {
        while(true){
            try {
                Msg msg=bq.take();
                msg.i=msg.i/2;
                System.out.println("result:"+msg.i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
