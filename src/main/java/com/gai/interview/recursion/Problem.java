package com.gai.interview.recursion;

public class Problem {

    /*
    *   现在要求输入一个整数 n，请你输出斐波那契数列的第 n 项。
    *   递归方式
    * */
    public static int problem1(int num){
        if(num <= 0){
            return 0;
        }
        if(num == 1){
            return 1;
        }
        return problem1(num-1) + problem1(num-2);
    }

    /*
    *   问题1
    *   循环方式
    * */
    public static int problem1(int num, int i){
        if(num == 0){
            return 0;
        }
        if(num == 1){
            return 1;
        }
        //上一次计算的结果
        int result = 0;
        //f(n)的值,默认f(1)的值
        int preOne = 1;
        //f(n-1)的值，默认为0
        int preTwo = 0;
        for (int j = 2; j <= num; j++){
            //f(2)=f(1)+f(0)=f(1)
            result = preOne + preTwo;
            //preTwo存放f(n-1)
            preTwo = preOne;
            //preTwo存放f(n)
            preOne = result;
        }
        return result;
    }

    /*
    *   一只青蛙一次可以跳上1级台阶,也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
    * */
    public static int problem2(int n){
        if(n < 3){
            return n;
        }
        int result = 0;
        int preOne = 2;
        int preTwo = 1;
        for (int i = 3; i <= n; i++){
            result = preOne + preTwo;
            preTwo = preOne;
            preOne = result;
        }
        return result;
    }

    public static void main(String[] args) {
        int a = Problem.problem1(5);
        System.out.println(a);
        int a1 = Problem.problem1(5,1);
        System.out.println(a1);
        int a3 = Problem.problem2(4);
        System.out.println(a3);
    }
}
