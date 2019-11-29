package chapter5.pcModel;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer implements Runnable {
    //static CountDownLatch cdl=new CountDownLatch(39000);
    private static AtomicInteger i=new AtomicInteger(0);
    private static final int SLEEPTIME=1000;
    private volatile boolean isRunning=true;
    private BlockingQueue<PCData> queue;

    public Producer(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        PCData data=null;
        Random r=new Random();
        while(isRunning){
            //System.out.println("start "+Thread.currentThread().getId()+" put in queue");
            try {
                //Thread.sleep(r.nextInt(SLEEPTIME));
                data=new PCData(i.getAndIncrement());
                if(!queue.offer(data,2, TimeUnit.SECONDS)){
                    System.out.println("input "+Thread.currentThread().getId()+" is failed");
                }
                if(i.get()>=100000){
                    stop();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void stop(){
        isRunning=false;
    }
}
