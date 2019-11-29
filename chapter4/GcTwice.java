package chapter4;

public class GcTwice {
    private TestThis a;
    private TestThis b;

    public TestThis getA() {
        return a;
    }

    public void setA(TestThis a) {
        this.a = a;
    }

    public TestThis getB() {
        return b;
    }

    public void setB(TestThis b) {
        this.b = b;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("GcTwice is GC");
        super.finalize();

    }

    public static void main(String[] args) throws InterruptedException {
        GcTwice gc=new GcTwice();
        gc.setA(new TestThis());
        gc.setB(new TestThis());
        Thread.sleep(1000);
        gc=null;
        System.out.println("sleep over");
        System.gc();
        Thread.sleep(5000);
    }
}
