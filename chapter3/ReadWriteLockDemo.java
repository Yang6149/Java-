package chapter3;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {
    public static ReentrantLock lock=new ReentrantLock();
    public static ReentrantReadWriteLock readWriteLock=new ReentrantReadWriteLock();
    public static Lock readLock=readWriteLock.readLock();
    public static Lock writeLock=readWriteLock.writeLock();

    private int value;
    public  Object handlerRead(Lock lock){
        try {
            lock.lock();
            Thread.sleep(1000);
            System.out.println("read");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return value;
    }

    public  void handlerWrite(Lock lock,int num){
        try{
            lock.lock();
            Thread.sleep(1000);
            System.out.println("write!");
            value=num;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();

        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReadWriteLockDemo demo=new ReadWriteLockDemo();
        Runnable read=new Runnable() {
            @Override
            public void run() {
                try {
                    demo.handlerRead(readLock);
                    //demo.handlerRead(lock);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        Runnable write=new Runnable() {
            @Override
            public void run() {
                try {
                    demo.handlerWrite(writeLock,new Random().nextInt());
                    //demo.handlerWrite(lock,new Random().nextInt());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };

        for(int i=0;i<18;i++){
            Thread a=new Thread(read);
            a.start();
            //a.join();
        }
        for(int i=0;i<18;i++){
            Thread a=new Thread(write);
            a.start();
            //a.join();
        }
    }
}
