package chapter5.pcModel;

import com.lmax.disruptor.EventFactory;

public class PCDataFactory implements EventFactory {
    public Object newInstance() {
        return new PCData();
    }
}
