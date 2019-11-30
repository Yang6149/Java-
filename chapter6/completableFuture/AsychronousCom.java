package chapter6.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class AsychronousCom {
    public static Integer get(int a)  {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return a*a;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> a= CompletableFuture.supplyAsync(()->get(50));
        System.out.println(a.get());
    }
}
