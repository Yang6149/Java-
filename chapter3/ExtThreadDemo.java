package chapter3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExtThreadDemo {
    public static class Mytask implements Runnable{
        String name;
        public Mytask(String name){
            this.name=name;
        }
        @Override
        public void run() {
            try {
                System.out.println("执行当前任务"+Thread.currentThread().getId()+"name"+name);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService a=new ThreadPoolExecutor(5,5,0L, TimeUnit.SECONDS,new LinkedBlockingDeque<Runnable>()){
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println("before execution"+Thread.currentThread().getId());

            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println("after execution"+Thread.currentThread().getId());
            }

            @Override
            protected void terminated() {
                System.out.println("线程池退出");
            }
        };
        for(int i=0;i<5;i++){
            Mytask c=new Mytask("tesk:"+i);
            a.execute(c);
            Thread.sleep(500);
        }
        a.shutdown();
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
