package chapter6.interfacE;

public interface IDonkey {
    default void run(){
        System.out.println("donkey run");
    }
}
