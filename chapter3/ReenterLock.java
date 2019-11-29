package chapter3;

import java.util.concurrent.locks.ReentrantLock;

public class ReenterLock implements Runnable {
    public static ReentrantLock lock=new ReentrantLock();
    public static int i=0;
    @Override
    public void run() {
        for(int a=0;a<10000000;a++){
            lock.lock();
            try{
                i++;
            }finally {
                lock.unlock();
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1=new Thread(new ReenterLock());
        Thread t2=new Thread(new ReenterLock());
        t1.start();t2.start();
        t1.join();t2.join();
        System.out.println(i);

    }
}
