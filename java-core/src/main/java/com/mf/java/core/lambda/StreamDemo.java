package com.mf.java.core.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StreamDemo {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(4,3,2,1,2,4,5,6);
        Optional<Integer> optional= list.stream().findFirst();
        int abc = optional.get();
        System.out.println(abc);
        List<Integer> ab = list.stream().map(a -> a +1).collect(Collectors.toList());
        System.out.println(ab.toString());
        list.forEach(a -> System.out.println(a));
//        list.stream().anyMatch(a -> );

    }
}
