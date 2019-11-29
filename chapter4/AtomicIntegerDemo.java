package chapter4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerDemo {
    static AtomicInteger value=new AtomicInteger();
    static int value1=0;
    public static class IncrementTest implements Runnable{

        @Override
        public void run() {
            for(int i=0;i<100000;i++){
                value.getAndIncrement();

                value1++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exe= Executors.newFixedThreadPool(80);
        IncrementTest a=new IncrementTest();
        for(int i=0;i<80;i++){
            exe.submit(a);
        }
        Thread.sleep(3000);
        System.out.println(value);
        System.out.println(value1);
        exe.shutdown();
    }
}
