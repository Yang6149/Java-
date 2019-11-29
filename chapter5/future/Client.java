package chapter5.future;

public class Client {
    public Data request(final String para){
        FutureData futureData=new FutureData();
        new Thread(new Runnable() {
            @Override
            public void run() {
                RealData real=new RealData(para);
                futureData.setRealData(real);

            }
        }).start();
        return futureData;
    }

}
