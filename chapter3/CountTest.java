package chapter3;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class CountTest extends RecursiveTask {
    private final int THRESHOLD=10000;
    private long begin;
    private  long end;

    public CountTest(long pos, long lastOne) {
        begin=pos;
        end=lastOne;

    }

    @Override
    protected Long compute() {
        long sum=0;
        boolean canCompute=(end-begin)<THRESHOLD;
        if(canCompute){
            for(long i=begin;i<end;i++){
                sum+=1;
            }
        }else{
            long step=(end-begin)/100;
            ArrayList<CountTest> array=new ArrayList<>(100);
            long pos=begin;

            for(int i=0;i<100;i++){
                long lastOne=pos+step;
                if(lastOne>end)lastOne=end;
                CountTest c=new CountTest(pos,lastOne);
                pos+=1;
                array.add(c);
                c.fork();
            }
            for(CountTest u:array){
                sum+= (long)u.join();
            }


        }
        return sum;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool=new ForkJoinPool();
        CountTest count=new CountTest(0,27871);
        ForkJoinTask<Long> forkJoinTask=forkJoinPool.submit(count);
        long result=forkJoinTask.get();
        System.out.println("sum="+result);
    }
}
