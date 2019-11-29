package chapter4;

public class TestThis implements Runnable {
    @Override
    public void run() {
        synchronized (this){//this.getClass()
            System.out.println("this id"+Thread.currentThread().getId());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("Test this is gc");
    }

    public static void main(String[] args) {
        Thread t1=new Thread(new TestThis());
        Thread t2=new Thread(new TestThis());
        t1.start();
        t2.start();
    }
}
