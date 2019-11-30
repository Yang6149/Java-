package chapter6.interfacE;

public interface IAnimal {
    default void breath(){
        System.out.println("animal breath");
    }
}
