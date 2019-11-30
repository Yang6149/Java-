package chapter6;

@FunctionalInterface
public interface AnnoFunction {
    void handler();
    //void a();只能包含一个抽象方法 ，写连个抽象方法报错
    boolean equals(Object o);//该方法不是抽象方法，它被Object实现了
}
