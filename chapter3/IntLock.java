package chapter3;

import java.util.concurrent.locks.ReentrantLock;

public class IntLock implements Runnable {
    public static ReentrantLock lock1=new ReentrantLock();
    public static ReentrantLock lock2=new ReentrantLock();
    public int lock;
    public IntLock(int lock){
        this.lock=lock;
    }
    @Override
    public void run() {
        try{
            if(lock==1){
                lock1.lockInterruptibly();
                try{
                    Thread.sleep(500);
                }catch (Exception e){
                    e.printStackTrace();
                }
                lock2.lockInterruptibly();
                System.out.println("t1 get lock2");
            }else{
                lock2.lockInterruptibly();
                try {
                    Thread.sleep(500);
                }catch (Exception e){
                    e.printStackTrace();
                }
                lock1.lockInterruptibly();
                System.out.println("t2 get lock1");
            }
            System.out.println(Thread.currentThread().getName());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(lock1.isHeldByCurrentThread()){
                lock1.unlock();
            }
            if(lock2.isHeldByCurrentThread()){
                lock2.unlock();
            }
            System.out.println(Thread.currentThread().getName()+" out of Thread");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1=new Thread(new IntLock(1),"t1");
        Thread t2=new Thread(new IntLock(2),"t2");
        t1.start();t2.start();

        Thread.sleep(1000);
        t2.interrupt();

    }
}
