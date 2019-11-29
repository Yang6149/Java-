package chapter5.pcModel;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SleepingWaitStrategy;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class MainPlus {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exe= Executors.newCachedThreadPool();
        PCDataFactory factory=new PCDataFactory();
        int bufferSize=1024;
        Disruptor<PCData> disruptor=new Disruptor<PCData>(factory,
                bufferSize,exe, ProducerType.MULTI,new BlockingWaitStrategy());
        disruptor.handleEventsWithWorkerPool(new ConsumerPlus(),new ConsumerPlus(),new ConsumerPlus(),new ConsumerPlus());
        disruptor.start();


        AtomicLong l=new AtomicLong(0);
        Runnable p=new Runnable() {
            @Override
            public void run() {
                RingBuffer<PCData> buffer=disruptor.getRingBuffer();
                ProducerPlus p=new ProducerPlus(buffer);
                ByteBuffer bb=ByteBuffer.allocate(8);
                for(;l.get()<100000;l.incrementAndGet()){
                    bb.putLong(0,l.get());
                    p.pushData(bb);
                    //Thread.sleep(1000);
                    //System.out.println("add Data "+l);

                }
            }
        };
        Thread pp1=new Thread(p);
        Thread pp2=new Thread(p);
        Thread pp3=new Thread(p);

        long begin=System.currentTimeMillis();
        pp1.start();
        pp2.start();
        pp3.start();
        pp1.join();
        pp2.join();

        pp3.join();

        long endtime=System.currentTimeMillis();
        Thread.sleep(200);
        System.out.println(endtime-begin);
        
    }
}
