package chapter4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalDate2 {
    static ThreadLocal<SimpleDateFormat> t1=new ThreadLocal<>(){
        @Override
        protected void finalize() throws Throwable {
            System.out.println(Thread.currentThread().getId()+" is gc       ThreadLocal");
            super.finalize();
        }
    };

    public static class ParseDate implements Runnable{
        int i=0;
        public ParseDate(int i){
            this.i=i;
        }

        @Override
        public void run() {
            try{
                if(t1.get()==null){
                    t1.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"){
                        @Override
                        protected void finalize() throws Throwable {
                            System.out.println(Thread.currentThread().getId()+" is gc         SimpleDateFormat");
                            super.finalize();
                        }
                    });
                }

                Date t=t1.get().parse("2019-11-25 20:35:"+i%60);
                System.out.println(i+";"+t);
            } catch (ParseException e) {
                e.printStackTrace();

            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exe=Executors.newFixedThreadPool(10);
        for(int i=0;i<1000;i++){
            exe.execute(new ParseDate(i));
        }
        exe.shutdown();
        Thread.sleep(5000);
        t1=null;
        Runtime.getRuntime().gc();
        Thread.sleep(2000);
    }
}
