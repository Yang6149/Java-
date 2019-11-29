package chapter3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadDemo implements Runnable {
    @Override
    public void run() {
        System.out.println(System.currentTimeMillis()+" "+Thread.currentThread().getId());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService exe= Executors.newFixedThreadPool(5);
        ExecutorService exeCache=Executors.newCachedThreadPool();
        ThreadDemo t=new ThreadDemo();
        for(int i=0;i<10;i++){
            exeCache.submit(t);
        }
    }
}
