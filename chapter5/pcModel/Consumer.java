package chapter5.pcModel;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Consumer implements Runnable {
    private BlockingQueue<PCData> queue;
    private static final int SLEEPTIME=1000;

    public Consumer(BlockingQueue<PCData> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        PCData data=null;
        Random r=new Random();
        System.out.println("start output "+ Thread.currentThread().getId()+" at queue");
        try {
            while(true){
                data= queue.take();
                if(data!=null){
                    System.out.println(data.get()+"*"+data.get()+"="+data.get()*data.get());
                }
                //Thread.sleep(r.nextInt(SLEEPTIME));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }


    }
}
