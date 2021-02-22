package com.gai.array;

public class ArrayDemo {
//    public static void main(String[] args) {
//        char[] charArr1 = {'a','b','c'};
//        char[] charArr2 = new char[]{'图','解','J','a','v','a'};
//        char[] charArr3 = new char[5];
//        charArr3[0] = '1';
//        charArr3[1] = '0';
//        charArr3[2] = '0';
//        charArr3[3] = '8';
//        charArr3[4] = '6';
//        System.out.println(charArr1);
//        System.out.println(charArr2);
//        System.out.println(charArr3);
//    }

    public static void main(String[] args) {
        char[][] charArr1 = {{'a','b','c'},{'c','d','e'},{'f','g','h','i'}};
        char[][] charArr2 = new char[][]{{'1','2','3'},{'3','4','5'},{'7','8','9','a'}};
        char[][] charArr3 = new char[2][3];
        charArr3[0][0] = 97;
        charArr3[0][1] = 98;
        charArr3[0][2] = 49;
        charArr3[1][0] = 50;
        charArr3[1][1] = 51;
        System.out.println(charArr1.toString());
        System.out.println(charArr2.toString());
        System.out.println(charArr3.toString());
    }
}
