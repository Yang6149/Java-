package chapter3;

import java.util.concurrent.locks.ReentrantLock;

public class FairLock implements Runnable {
    public static ReentrantLock lock=new ReentrantLock(true);
    @Override
    public void run() {
        while(true){
            lock.lock();
            System.out.println(Thread.currentThread().getId());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
        }

    }

    public static void main(String[] args) {
        Thread [] t1 = new Thread[10];
        for(int i=0;i<10;i++){
            t1[i]=new Thread(new FairLock());
        }
        for(int i=0;i<10;i++){
            t1[i].start();
        }


    }
}
