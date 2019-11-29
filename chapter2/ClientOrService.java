package chapter2;

public class ClientOrService  {
    private static volatile boolean ready=false;
    private static volatile int num;
    private static class ReaderThread implements Runnable{
        static int nums=1;
        @Override
        public void run() {
            while(!ready){

            }
            System.out.println("循环结束"+num);
            nums+=1;
            System.out.println(nums);
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Thread t1=new Thread(new ReaderThread(),"test");
        t1.start();
        Thread t2=new Thread(new ReaderThread(),"test2");
        t2.start();
        System.out.println("start");
        Thread.sleep(3000);
        ready=true;
        num=42;
        Thread.sleep(3000);
        System.out.println("end");
    }
}
