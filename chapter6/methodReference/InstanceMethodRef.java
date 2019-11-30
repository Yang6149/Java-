package chapter6.methodReference;

import java.util.ArrayList;

public class InstanceMethodRef {
    public static void main(String[] args) {
        ArrayList<User> array=new ArrayList<>();
        for(int i=0;i<10;i++){
            array.add(new User(i,"a","b"));
        }
        array.stream().map(User::getId).forEach(System.out::println);
    }
}
