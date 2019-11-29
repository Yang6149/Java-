package chapter5.future;

public class Main {
    public static void main(String[] args) {
        Client c=new Client();
        System.out.println("开始请求");
        Data data=c.request("five hundred times ");
        try{
            System.out.println("处理业务");
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(data.getResult());
        System.out.println("over");
    }
}
