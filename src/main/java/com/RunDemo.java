package com;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RunDemo {
    public static void main(String[] args) {
        List<Long> list = Arrays.asList(1L,2L,3L,2L,128L,300L,-128L,300L,-128L);
        List<Long> longs = list.stream().distinct().collect(Collectors.toList());
        System.out.println("size: " + longs.size() + " " +longs.toString());
    }
}
