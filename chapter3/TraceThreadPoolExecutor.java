package chapter3;

import java.util.concurrent.*;

public class TraceThreadPoolExecutor extends ThreadPoolExecutor {
    public TraceThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }
    public Runnable wrap(Runnable b,final Exception ClientTrace,String name){
        return new Runnable() {
            @Override
            public void run() {
                try{
                    b.run();
                }catch (Exception e){
                    ClientTrace.printStackTrace();
                    throw e;
                }
            }
        };

    }
    private Exception clientTrace(){
        return new Exception("client stack trace");
    }
    @Override
    public Future<?> submit(Runnable task) {
        return super.submit(wrap(task,clientTrace(),Thread.currentThread().getName()));
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService exe=new TraceThreadPoolExecutor(0, Integer.MAX_VALUE,0L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
        for(int i=0;i<5;i++){
            exe.submit(new DivTesk(100,i));

            //exe.execute(new DivTesk(100,i));
        }
        exe.shutdown();
    }
}
