package chapter5;

public class Singleton {
    private static Singleton instance;
    private static Object o=new Object();
    public static Singleton getInstance(){
        if(instance==null){
            synchronized (o){
                if(instance==null){
                    instance=new Singleton();
                }
            }

        }
        return instance;
    }

    private Singleton(){
        System.out.println("被创建");
    }
}
