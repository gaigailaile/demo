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

    /*
    *   问题3
    *   给定一个 double 类型的浮点数 base 和 int 类型的整数 exponent。
    *   求 base 的 exponent 次方。不得使用库函数，不需要考虑大数问题.
    *
    *   该方案没有考虑exponent为0或者负数的情况
    * */
    public static double problem3(double base,int exponent){
        double result = 1.0;
        for(int i = 1; i <= exponent; i++){
            result *= base;
        }
        return result;
    }

    /*
     *   问题3
     *
     *   该方案考虑exponent为0或者负数的情况
     * */
    public static double problem3(double base,int exponent,int a){
        double result = 1.0;
        if(doubleEqual(base,0.0)){
            return 0.0;
        }

        if(exponent == 0){
            return 1.0;
        }
        int absExponent = exponent;
        if(exponent < 0){
            absExponent = -exponent;
        }
        for(int i = 1; i <= absExponent; i++){
            result *= base;
        }

        return exponent > 0 ? result:1.0/result;
    }

    public static boolean doubleEqual(double a, double b) {
        if (a - b < 0.000001 && a - b > -0.000001) {
            return true;
        }
        return false;
    }

    /*
     *   问题3
     *
     *   比较好的解法推荐
     * */
    public static double problem3(double base,int exponent,String a){
        if(exponent < 0){
            base = 1/base;
            exponent = -exponent;
        }
        if(exponent == 0){
            return 1;
        }
        if(exponent == 1){
            return base;
        }
        double result = problem3(base,exponent >> 1,"");
        result = result * result;
        if((exponent & 1) == 1){
            result = result * base;
        }

        return result;
    }

    /*
    *   输入数字 n，按顺序打印从 1 到最大的 n 位数十进制数，比如：输入 3，打印出 1 到 999.
    * */
    public static void problem4(int n){
        int[] array=new int[n];
        if(n <= 0)
            return;
        printArray(array, 0);
    }

    public static void printArray(int[] array, int n){
        for(int i = 0; i < 10; i++) {
            if(n != array.length) {
                array[n] = i;
                printArray(array, n+1);
            } else {
                boolean isFirstNo0 = false;
                for(int j = 0; j < array.length; j++) {
                    if(array[j] != 0) {
                        System.out.print(array[j]);
                        if(!isFirstNo0) isFirstNo0 = true;
                    } else {
                        if(isFirstNo0)
                            System.out.print(array[j]);
                    }
                }
                System.out.println();
                return ;
            }
        }
    }

    public static void main(String[] args) {
//        System.out.println(problem3(-2.0,2));
//        System.out.println(problem3(3,-2,1));
//        System.out.println(problem3(2.0,-2,1));
//        System.out.println(problem3(2.0,-2,"1"));
        problem4(3);
    }
}
