package chapter6.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class AskThread implements Runnable {
    CompletableFuture<Integer> cf;

    public AskThread(CompletableFuture<Integer> cf) {
        this.cf = cf;
    }

    @Override
    public void run() {
        int my;
        try{
            my=cf.get()*cf.get();
            System.out.println(my);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CompletableFuture<Integer> ask=new CompletableFuture<>();
        new Thread(new AskThread(ask)).start();
        Thread.sleep(500);
        ask.complete(60);

    }
}
