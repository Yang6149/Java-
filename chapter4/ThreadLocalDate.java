package chapter4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalDate {

    public static final SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static class ParseDate implements Runnable{
        int i=0;
        public ParseDate(int i){
            this.i=i;
        }

        @Override
        public void run() {
            try{
                Date t=sdf.parse("2019-11-25 20:35:"+i%60);
                System.out.println(i+";"+t);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService exe=Executors.newFixedThreadPool(10);
        for(int i=0;i<50;i++){
            exe.execute(new ParseDate(i));
        }

    }
}
