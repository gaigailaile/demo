package com.gai.interview.dynamic;

import java.util.Arrays;

public class Problem {

    /*
    *   问题1 动态规划
    *   给你一根长度为n的绳子，请把绳子剪成m段(m和n都是整数，n>1并且m>1)每段绳子的长度记为k[0],k[1],...,k[m].
    *   请问k[0]*k[1]*...*k[m]可能的最大乘积是多少？
    *   例如，当绳子的长度为8时，我们把它剪成长度分别为2,3,3的三段，此时得到的最大乘积是18.
    *
    * */
    public static int problem1(int n){
        if(n < 2){
            return 0;
        }
        if(n == 2){
            return 1;
        }
        if(n == 3){
            return 2;
        }
        int[] products = new int[n+1];
        products[1] = 1;
        products[2] = 2;
        products[3] = 3;
        for (int i = 4; i <= n; i++){
            int max = 0;
            for (int j = 1; j <= i / 2; j++){
                int product = products[j] * products[i-j];
                if(max < product){
                    max = product;
                }
                products[i] = max;
            }
        }
        return products[n];
    }

    /*
     *   问题1 贪心算法
     * */
    public static int problem1(int n,int a){
        // 长度为1时不满足题意，返回0
        if (n < 2) {
            return 0;
        }
        // f(2)
        if (n == 2) {
            return 1;
        }
        // f(3)
        if (n == 3) {
            return 2;
        }
        //     统计能分出多少段长度为3的绳子
        int timesOf3 = n / 3;
        // 如果最有只剩下长度为1的绳子，需要退一步，得到长度为4的绳子，重新分成2*2的
        if (n - timesOf3 * 3 == 1) {
            timesOf3--;
        }
        // 到这步length - timesOf3 * 3的值只可能是0,2,4，所以timesOf2只可能是0, 1, 2
        int timesOf2 = (n - timesOf3 * 3) / 2;
        return (int) Math.pow(3, timesOf3) * (int) Math.pow(2, timesOf2);
    }

    /*
    *   问题2
    *
    *   把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。
    *   输入n，打印出s的所有可能的值出现的概率。
    *   你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。
    *
    * */
    public static double[] problem2(int n){
        double[] dp = new double[6];
        Arrays.fill(dp, 1.0 / 6.0);
        for (int i = 2; i <= n; i++) {
            double[] tmp = new double[5 * i + 1];
            for (int j = 0; j < dp.length; j++) {
                for (int k = 0; k < 6; k++) {
                    tmp[j + k] += dp[j] / 6.0;
                }
            }
            dp = tmp;
        }
        return dp;
    }

    public static void main(String[] args) {
        System.out.println(problem1(10));
        System.out.println(problem1(10,1));
    }
}
