package chapter5.pcModel;

import com.lmax.disruptor.WorkHandler;

public class ConsumerPlus implements WorkHandler<PCData> {
    public void onEvent(PCData event) throws Exception {
        //System.out.println("take out");
        //System.out.println(Thread.currentThread().getId()+" event:--"+event.get()+"*"+event.get()+"="+event.get()*event.get());
    }
}
