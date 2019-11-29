package chapter2;

public class PriorityDemo implements Runnable {
    @Override
    public void run() {
        int i=0;
        while(i<100000000){
            synchronized (PriorityDemo.class){
                i++;
            }

        }
        System.out.println(Thread.currentThread().getName()+"finished");
    }

    public static void main(String[] args) {
        ThreadGroup tg=new ThreadGroup("testPriority");
        Thread t1=new Thread(tg,new PriorityDemo(),"10");
        t1.setPriority(10);
        Thread t2=new Thread(tg,new PriorityDemo(),"9");
        t2.setPriority(9);
        Thread t3=new Thread(tg,new PriorityDemo(),"8");
        t3.setPriority(8);
        Thread t4=new Thread(tg,new PriorityDemo(),"7");
        t4.setPriority(7);
        Thread t5=new Thread(tg,new PriorityDemo(),"6");
        t5.setPriority(6);
        Thread t6=new Thread(tg,new PriorityDemo(),"5");
        t6.setPriority(5);
        Thread t7=new Thread(tg,new PriorityDemo(),"4");
        t7.setPriority(4);
        Thread t8=new Thread(tg,new PriorityDemo(),"3");
        t8.setPriority(3);
        Thread t9=new Thread(tg,new PriorityDemo(),"2");
        t9.setPriority(2);
        Thread t10=new Thread(tg,new PriorityDemo(),"1");
        t1.setPriority(1);

        t6.start();
        t7.start();
        t8.start();
        t9.start();
        t10.start();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

    }
}
