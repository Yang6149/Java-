package chapter6;

import java.util.Arrays;

public class FunctionalP1 {
    public static void main(String[] args) {
        int [] nums={1,2,3,4,5,6};
        Arrays.stream(nums).map((x)->x=x+1).forEach(System.out::println);
        Arrays.stream(nums).forEach(System.out::println);
    }
}
