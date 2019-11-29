package chapter5.addAndDiv;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Add implements Runnable {
    public BlockingQueue<Msg> bq=new LinkedBlockingQueue<>();

    @Override
    public void run() {
        while(true){
            try {
                Msg msg=bq.take();
                Multiply.bq.add(msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
