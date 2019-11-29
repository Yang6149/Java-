package chapter3;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo  {

    public static class Soldier implements Runnable{
        private String soldier;
        private CyclicBarrier cb;
        public Soldier(CyclicBarrier cb,String name){
            this.cb=cb;
            soldier=name;
        }
        @Override
        public void run() {
            try{
                //等待所有士兵到齐
                cb.await();
                doWork();
                //等待所有士兵完成工作
                cb.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
        private void doWork() throws InterruptedException {
            Thread.sleep(new Random().nextInt(10)*1000);
            System.out.println("soldier finished work");
        }
    }
    public static class BarrierRun implements Runnable{
        boolean flag;
        int N;
        public BarrierRun(boolean flag,int N){
            this.flag=flag;
            this.N=N;
        }
        @Override
        public void run() {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(flag){
                System.out.println("Soldier NO."+N+" mission accommplished");
            }else{
                System.out.println("Soldier NO."+N+" gather ");
                flag=true;
            }
        }
    }

    public static void main(String[] args) {
        final int N=10;
        Thread []allSoldier=new Thread[N];
        boolean flag=false;
        CyclicBarrier cyclic=new CyclicBarrier(10,new BarrierRun(flag,N));
        System.out.println("corps gather!!");
        for(int i=0;i<N;i++){
            System.out.println("soldier "+N+" is here");
            allSoldier[i]=new Thread(new Soldier(cyclic,"soldier"+N));
            allSoldier[i].start();
        }
    }
}
