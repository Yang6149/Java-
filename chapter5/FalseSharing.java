package chapter5;

public class FalseSharing implements Runnable {
    private static final int NUM_THREAD=2;
    private static final long ITERATIONS=500L*1000L*1000L;
    private final int arrayIndex;
    public static VolatileLong[] longs=new VolatileLong[NUM_THREAD];
    static{
        for(int i=0;i<longs.length;i++){
            longs[i]=new VolatileLong();
        }
    }

    public FalseSharing(int arrayIndex) {
        this.arrayIndex = arrayIndex;
    }

    public static void runTest() throws InterruptedException {
        Thread [] threads=new Thread[NUM_THREAD];
        for(int i=0;i<NUM_THREAD;i++){
            threads[i]=new Thread(new FalseSharing(i));

        }
        for(Thread t:threads){
            t.start();
        }
        for(Thread t:threads){
            t.join();
        }

    }
    @Override
    public void run() {
        long i=ITERATIONS+1;
        while (0!=--i){
            longs[arrayIndex].value=i;
        }

    }

    public static void main(String[] args) throws InterruptedException {
        long begin=System.currentTimeMillis();
        runTest();
        System.out.println(System.currentTimeMillis()-begin);
    }
    public static class VolatileLong{
        public volatile long value=0l;
        public long p1,p2,p3,p4,p5,p6,p7;
    }
}
