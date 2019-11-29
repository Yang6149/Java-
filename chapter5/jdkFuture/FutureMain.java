package chapter5.jdkFuture;

import java.util.concurrent.*;

public class FutureMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long begin=System.currentTimeMillis();
        FutureTask future=new FutureTask<String>(new RealData("a"));
        ExecutorService exe=Executors.newSingleThreadExecutor();
        exe.execute(future);

        System.out.println("请求完毕");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("output"+future.get());
        System.out.println(System.currentTimeMillis()-begin);
    }
}
