package chapter5.addAndDiv;

public class Main {
    public static void main(String[] args) {
        Add a=new Add();
        Thread add=new Thread(a);
        Thread multiply=new Thread(new Multiply());
        Thread div=new Thread(new Div());
        add.start();;
        multiply.start();
        div.start();
        for(int i=0;i<1000;i++){
            for(int j=0;j<1000;j++){
                Msg now=new Msg();
                now.i=i;
                now.j=j;
                a.bq.add(now);
            }
        }

    }
}
