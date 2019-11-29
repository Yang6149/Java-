package chapter5.pcModel;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        BlockingQueue queue=new ArrayBlockingQueue(10);
        Producer p1=new Producer(queue);
        Producer p2=new Producer(queue);
        Producer p3=new Producer(queue);
        Consumer c1=new Consumer(queue);
        Consumer c2=new Consumer(queue);
        Consumer c3=new Consumer(queue);
        ExecutorService exe=Executors.newFixedThreadPool(6);
        long begin=System.currentTimeMillis();
        Thread pp1=new Thread(p1);
        Thread pp2=new Thread(p2);
        Thread pp3=new Thread(p3);
        pp1.start();
        pp2.start();
        pp3.start();

        exe.execute(c1);
        exe.execute(c2);
        exe.execute(c3);
        pp1.join();
        pp2.join();
        pp3.join();
        //Producer.cdl.await();
        System.out.println("***********************************************************/n" +
                "*****************************************************************************************************************************");
        long end=System.currentTimeMillis();

        System.out.println(end-begin);

        Thread.sleep(3000);
        exe.shutdown();
    }
}
