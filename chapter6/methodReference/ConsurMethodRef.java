package chapter6.methodReference;

import java.util.ArrayList;

public class ConsurMethodRef {
    interface UserFactory<U extends User>{
        U create(int i,String name,String ID);
    }

    static UserFactory uf=User::new;

    public static void main(String[] args) {
        ArrayList<User> array=new ArrayList<>();
        for(int i=0;i<10;i++){
            array.add(uf.create(i,"a","b"));
        }
        array.stream().map(User::getId).forEach(System.out::println);
    }
}
