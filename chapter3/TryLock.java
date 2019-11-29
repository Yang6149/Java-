package chapter3;

import java.util.concurrent.locks.ReentrantLock;

public class TryLock implements Runnable {
    public static ReentrantLock lock1=new ReentrantLock();
    public static ReentrantLock lock2=new ReentrantLock();
    public int num;
    public TryLock(int a){
        num=a;
    }
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" start to run");
        if(num==1){
            while(true){
                if(lock1.tryLock()){
                    try{
                        try{
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if(lock2.tryLock()){
                            try{
                                System.out.println(Thread.currentThread().getName()+" done the job");
                            }catch (Exception e){

                            }
                            finally{
                                lock2.unlock();
                            }
                        }
                    }finally {
                        lock1.unlock();
                    }


                }

            }
        }else{
            while(true){
                if(lock2.tryLock()){
                    try{
                        try{
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if(lock1.tryLock()){
                            try{
                                System.out.println(Thread.currentThread().getName()+" done the job");
                            }finally{
                                lock1.unlock();
                            }
                        }
                    }finally {
                        lock2.unlock();
                    }
                }

            }
        }
    }

    public static void main(String[] args) {
        Thread t1=new Thread(new TryLock(1),"t1");
        Thread t2=new Thread(new TryLock(2),"t2");
        t1.start();
        t2.start();

    }
}
