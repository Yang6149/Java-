package chapter3;

import java.util.concurrent.*;

public class DivTesk implements Runnable {
    double a;
    double b;
    int [] nums=new int[4];
    public DivTesk(int a,int b){
        this.a=a;
        this.b=b;
    }
    @Override
    public void run() {
        System.out.println(a/b);
        System.out.println(nums[(int)b]+" the B is "+b);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService exe=new ThreadPoolExecutor(0, Integer.MAX_VALUE,0L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
        for(int i=0;i<5;i++){
//            Future fu=exe.submit(new DivTesk(100,i));
//            fu.get();
            exe.execute(new DivTesk(100,i));
        }
        exe.shutdown();
    }
}
