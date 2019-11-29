package chapter5.addAndDiv;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

public class Multiply implements Runnable {

    static BlockingQueue<Msg> bq=new LinkedBlockingQueue<>();
    @Override
    public void run() {
        while(true){
            try {
                Msg msg=bq.take();
                msg.i=msg.i+msg.j;
                Div.bq.add(msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
