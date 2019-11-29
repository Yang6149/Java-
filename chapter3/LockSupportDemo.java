package chapter3;

import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo {
    public static Object u=new Object();
    static Thread t1=new Thread(new ChangeObjectThread());
    static Thread t2=new Thread(new ChangeObjectThread());

    public static class ChangeObjectThread implements Runnable{


        public void run(){

            System.out.println("Object has been changed    in "+Thread.currentThread().getId());
            //LockSupport.park();


        }
    }
    public static class Counting implements Runnable{

        @Override
        public void run() {
            while(true){
                System.out.println("counting");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread count=new Thread(new Counting());
        count.start();
        Thread.sleep(3000);
        System.out.println("start");
        t1.start();//!!!!!!!!!!!!!!!是start 不是run
        System.out.println("a running");
        Thread.sleep(100);
        t2.start();
        System.out.println("b running");
        LockSupport.unpark(t1);
        LockSupport.unpark(t2);
        t1.join();
        t2.join();



    }
}
