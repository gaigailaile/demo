package com.gai.interview.bit;

public class Problem {

    /*
    *   问题1
    *   输入一个整数n，输出该数二进制表示中 1 的个数。其中负数用补码表示。
    *
    *   判断最右边是否为1 不安全负数会出现死循环
    * */
    public static int problem1(int n){
        int count = 0;
        while (n != 0){
            if((n & 1) == 1){
                count++;
            }
            n = n >> 1;
        }
        return count;
    }

    /*
     *   问题1 标识符左移
     * */
    public static int problem1(int n,int a){
        int count = 0;
        int flag = 1;
        while (flag != 0){
            int i = n & flag;
            if((n & flag) != 0){
                count++;
            }
            flag = flag << 1;
        }
        return count;
    }

    /*
     *   问题1 a&(a-1)
     * */
    public static int problem1(int n,String b){
        int count = 0;
        while (n != 0){
            count++;
            n = n & (n-1);
        }
        return count;
    }

    /*
    *   问题2 不用加减乘除做加法
    *
    *   写一个函数，求两个整数之和，要求在函数体内不得使用 “+”、“-”、“*”、“/” 四则运算符号。
    * */
    public static int problem2(int a, int b){
        while(b != 0) { // 当进位为 0 时跳出
            int c = (a & b) << 1;  // c = 进位
            a ^= b; // a = 非进位和
            b = c; // b = 进位
        }
        return a;
    }

    public static void main(String[] args) {
        System.out.println(problem1(7));
        System.out.println(problem1(4,0));
        System.out.println(problem1(7,"0"));
    }
}
