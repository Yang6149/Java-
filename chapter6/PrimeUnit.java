package chapter6;

import chapter6.methodReference.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class PrimeUnit {
    public static boolean isPrime(int num){
        int temp=num;
        if(temp<2)return false;
        for(int i=3;i<=Math.sqrt(temp);i+=2){
            if(temp%i==0)return false;
        }
        return true;
    }

    public static void main(String[] args) {
        long begin=System.currentTimeMillis();
        System.out.println(IntStream.range(1, 10000000).parallel().filter(PrimeUnit::isPrime).count());
        System.out.println(System.currentTimeMillis()-begin);

        List<User> list=new ArrayList<>();
        double ave=list.parallelStream().mapToDouble(x->x.getId()).average().getAsDouble();
                        //Stream

    }
}
