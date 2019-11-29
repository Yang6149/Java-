package chapter5.pcModel;

public final class PCData {
    private long data;

    public PCData() {
    }

    public void set(long a){
        data=a;
    }
    public PCData(long date) {
        this.data = date;
    }
    public long get(){
        return data;
    }
    @Override
    public String toString() {
        return "PCData{" +
                "date=" + data +
                '}';
    }
}
