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

    /*
    *   输入一个整数 n ，求1～n这n个整数的十进制表示中1出现的次数。
    *   例如，输入12，1～12这些整数中包含1 的数字有1、10、11和12，1一共出现了5次。
    * */
    public static int problem5(int num){
        int count = 0;
        long i = 1;        // 从个位开始遍历到最高位
        while(num / i != 0) {
            long high = num / (10 * i);  // 高位
            long cur = (num / i) % 10;   // 当前位
            long low = num - (num / i) * i;
            if(cur == 0) {
                count += high * i;
            }else if(cur == 1) {
                count += high * i + (low + 1);
            }else {
                count += (high + 1) * i;
            }
            i = i * 10;
        }
        return count;
    }

    /*
    *   数字以0123456789101112131415…的格式序列化到一个字符序列中。
    *   在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，等等。
    *   请写一个函数，求任意第n位对应的数字。
    * */
    public static int problem6(int n){
        int digit = 1;   // n所在数字的位数
        long start = 1;  // 数字范围开始的第一个数
        long count = 9;  // 占多少位
        while (n > count){
            n -= count;
            digit++;
            start *= 10;
            count = start * digit * 9;
        }
        long num = start + (n - 1) / digit;
        return String.valueOf(num).charAt((n - 1) % digit) - '0';
    }

    /*
    *   我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。
    *   求按从小到大的顺序的第 n 个丑数。
    * */
    public static int problem7(int n){
        int a = 0, b = 0, c = 0;
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++){
            int n2 = dp[a] * 2, n3 = dp[b] * 3, n5 = dp[c] * 5;
            dp[i] = Math.min(Math.min(n2,n3),n5);
            if(dp[i] == n2) a++;
            if(dp[i] == n3) b++;
            if(dp[i] == n5) c++;
        }
        return dp[n-1];
    }

    public static void main(String[] args) {
        System.out.println(problem6(0));
    }
}
