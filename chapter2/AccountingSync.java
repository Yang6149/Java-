package chapter2;

public class AccountingSync implements Runnable {
    int num=0;
    @Override
    public void run() {

        for(int i=0;i<10000000;i++){
            synchronized (this){
                num++;
            }

        }
        System.out.println(num);
    }

    public static void main(String[] args) throws InterruptedException {
        AccountingSync a=new AccountingSync();
        Thread t1=new Thread(a);
        Thread t2=new Thread(a);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("over");

    }
}
