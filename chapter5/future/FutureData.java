package chapter5.future;

public class FutureData implements Data {
    protected RealData realData;
    protected boolean isReady=false;

    public synchronized void setRealData(RealData data){
        if(isReady){
            return;
        }
        this.realData=data;
        isReady=true;
        notifyAll();
    }

    @Override
    public synchronized String getResult() {
        while(!isReady){
            try{
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return realData.result;

    }
}
