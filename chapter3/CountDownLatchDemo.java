package chapter3;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo implements Runnable {
    public static CountDownLatch cdl=new CountDownLatch(10);
    @Override
    public void run() {

        //模拟检查任务
        try {
            Thread.sleep(new Random().nextInt(10)*1000);
            System.out.println("Check complete");
            cdl.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatchDemo a=new CountDownLatchDemo();

        ExecutorService exe=Executors.newFixedThreadPool(10);
        for(int i=0;i<10;i++){
            exe.submit(a);
        }
        cdl.await();
        System.out.println("Fire");
        exe.shutdown();
    }
}
