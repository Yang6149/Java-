package chapter5.jdkFuture;

import java.util.concurrent.Callable;

public class RealData implements Callable {
    private final String data;

    public RealData(String data) {
        this.data = data;
    }

    @Override
    public String call() throws Exception {
        StringBuffer s=new StringBuffer();
        for(int i=0;i<10;i++){
            s.append(data);
            Thread.sleep(300);
        }
        return s.toString();
    }
}
