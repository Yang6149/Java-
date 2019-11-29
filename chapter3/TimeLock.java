package chapter3;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.ReentrantLock;

public class TimeLock implements Runnable {
    public static ReentrantLock lock=new ReentrantLock();

    @Override
    public void run() {
        try {
            if(lock.tryLock(4, TimeUnit.SECONDS)){
                System.out.println(Thread.currentThread().getName()+" get lock");
                Thread.sleep(6000);

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            if(lock.isHeldByCurrentThread()){
                lock.unlock();
            }
            System.out.println(Thread.currentThread().getName()+" is finished");
        }
    }

    public static void main(String[] args) {
        Thread t1=new Thread(new TimeLock(),"t1");
        Thread t2=new Thread(new TimeLock(),"t2");
        t1.start();
        t2.start();
    }
}
