package chapter2;

public class DeamonDemo implements Runnable {
    @Override
    public void run() {
        while(true){
            System.out.println("I am alive");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        Thread deamon =new Thread(new DeamonDemo(),"demo");
        deamon.setDaemon(true);
        deamon.start();
        Thread.sleep(5000);
    }
}
