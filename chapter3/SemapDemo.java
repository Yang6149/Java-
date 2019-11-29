package chapter3;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemapDemo implements Runnable {
    Semaphore semaphore=new Semaphore(5);
    @Override
    public void run() {
        try {
            semaphore.acquire();
            System.out.println(Thread.currentThread().getId());
            Thread.sleep(2000);
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService= Executors.newFixedThreadPool(20);
        final SemapDemo semapDemo=new SemapDemo();
        for(int i=0;i<20;i++){
            executorService.submit(semapDemo);
        }
    }
}
