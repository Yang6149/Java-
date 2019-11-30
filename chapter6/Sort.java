package chapter6;

import java.util.Arrays;
import java.util.Random;

public class Sort {
    public static void main(String[] args) {
        int [] nums=new int[1000000];
        Random r=new Random();
        Arrays.parallelSetAll(nums,(i)->r.nextInt(1000000));
        Arrays.parallelSort(nums);
        Arrays.stream(nums).forEach(System.out::println);
    }
}
