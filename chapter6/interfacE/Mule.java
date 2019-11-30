package chapter6.interfacE;

public class Mule implements IAnimal,IHorse,IDonkey {
    @Override
    public void eat() {
        System.out.println(" Mule eat");
    }
    @Override
    public void run(){
        System.out.println("Mule run");//接口中有重复的同名默认方法，必须override 否则报错
    }

    public static void main(String[] args) {
        Mule m=new Mule();
        m.eat();
        m.breath();
        m.run();
    }
}
