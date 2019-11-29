package chapter5.pcModel;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

public class ProducerPlus  {
    private final RingBuffer<PCData> buffer;

    public ProducerPlus(RingBuffer<PCData> buffer) {
        this.buffer = buffer;
    }

    public void pushData(ByteBuffer bb){
        long sequence=buffer.next();
        try {
            PCData event=buffer.get(sequence);
            event.set(bb.getLong(0));
        }finally{
            //System.out.println("published");
            buffer.publish(sequence);
        }
    }


}
