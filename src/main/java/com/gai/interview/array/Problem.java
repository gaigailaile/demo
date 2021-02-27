package com.gai.interview.array;

import java.util.ArrayList;
import java.util.List;

public class Problem {
    //下列代码输出情况
//    public static void main(String[] args) {
//        char[] charArr1 = {'a','b','c'};
//        char[] charArr2 = charArr1;
//        charArr1[1] = 'g';
//        System.out.println(charArr2);
//    }

    /*
     *  在一个长度为n的数组里的所有数字都在0到n-1的范围内。
     *  数组中某些数字是重复的，但不知道有几个数字是重复的。
     *  也不知道每个数字重复几次。请找出数组中任意一个重复的数字。
     *  例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是数字2或者3。
     */
    public static int problem1(int[] array){
        //判断数组长度是否为0
        if(array == null || array.length==0){
            return -1;
        }

        //判断所有数据是否都合法
        for(int i = 0; i < array.length; i++){
            if(array[i] < 0 || array[i] >= array.length){
                return -1;
            }
        }

        //1.每个数字先和自己的下角标比较,相等不动
        //2.不相等在和对应的下角标数据比较,相等输出
        //3.不相等移动到对应角标位置
        for(int i = 0; i < array.length; i++){
            while (array[i] != i){
                int num = array[i];
                if(array[num] == num){
                    return num;
                }
                array[i] = array[num];
                array[num] = num;
            }
        }
        return -1;
    }

    /*
    *   在一个长度为n+1的数组里的所有数字都在1~n的范围内。
    *   所以数组至少有一个数字是重复的，请找出数组中任意一个重复的数字。
    *   但不能修改输入的数组。
    *   例如，如果输入长度为8的数组{2,3,5,4,3,2,6,7}，那么对应的输出是重复的数字2或者3。
    * */
    public static int problem2(int[] array){
        //判断数组长度是否为0
        if(array == null || array.length==0){
            return -1;
        }

        //判断所有数据是否都合法
        for(int i = 0; i < array.length; i++){
            if(array[i] <= 0 || array[i] >= array.length){
                return -1;
            }
        }

        //1.使用二分法取一个范围
        //2.遍历array看多少个数在范围区间内
        //3.count大于范围区间个数，则end = middle
        //4.count小于范围区间个数，则start = middle + 1
        //5.如果end=start且count>1则输出结果
        int start = 1;
        int end = array.length - 1;
        while (end >= start){
            int middle = (start + end)/2;
            int count = 0;

            for (int i = 0; i < array.length; i++){
                if(array[i] >= start && array[i] <= middle){
                    count++;
                }
            }

            if(end == start){
                if(count > 1){
                    return start;
                }else {
                    break;
                }
            }

            if(count > (middle - start + 1)){
                end = middle;
            }else {
                start = middle + 1;
            }

        }

        return -1;
    }

    /*
     *  在一个长度为n的数组里的所有数字都在0到n-1的范围内。
     *  数组中某些数字是重复的，但不知道有几个数字是重复的。
     *  也不知道每个数字重复几次。请找出数组中任意一个重复的数字。
     *  例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是数字2和3。
     */
    public static int[] problem3(int[] array){

        //判断数组长度是否为0
        if(array == null || array.length==0){
            return null;
        }

        //判断所有数据是否都合法
        for(int i = 0; i < array.length; i++){
            if(array[i] < 0 || array[i] >= array.length){
                return null;
            }
        }

        //1.每个数字先和自己的下角标比较,相等不动
        //2.不相等在和对应的下角标数据比较,相等存放到结果集合中
        //3.不相等移动到对应角标位置
        List<Integer> res = null;
        for(int i = 0; i < array.length; i++){
            while (array[i] != i){
                int num = array[i];
                if(array[num] == num){
                    if(res == null){
                        res = new ArrayList();
                    }
                    if(!res.contains(num)){
                        res.add(num);
                    }
                    break;
                }
                array[i] = array[num];
                array[num] = num;
            }
        }

        return res==null?null:res.stream().mapToInt(Integer::valueOf).toArray();
    }

    /*
    *   在一个二维数组中（每个一维数组的长度相同）.
    *   每一行都按照从左到右递增的顺序排序.
    *   每一列都按照从上到下递增的顺序排序.
    *   请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
    *   例如输入{{1,2,3},{4,5,6},{7,8,9}}和数字7 ，返回true
    *
    * */
    public static boolean problem4(int[][] array,int target){

        //判断数组长度是否为0
        if(array == null || array.length==0){
            return false;
        }

        //先判断target和右上角数据的大小
        //如果target < 右上角数据 说明该列不存在与target相等的数据 col-1
        //如果target > 右上角数据 说明改行不存在与target相等的数据 num+1
        int row = 0;
        int col = array[row].length-1;

        while (col >= 0 && row < array.length){
            if(target == array[row][col]){
                return true;
            } else if(target > array[row][col]){
                row++;
            } else if(target < array[row][col]){
                col--;
            }
        }

        return false;
    }

    /*
    *   输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，所有的偶数位于位于数组的后半部分。
    *
    *   简单解法 两个指针指向首尾 并交换奇偶数
    * */
    public static void problem5(int[] array){
        if(array == null || array.length == 0){
            return;
        }
        int start = 0;
        int end = array.length - 1;
        while (start < end){
            while (start < end && (array[start] & 1) == 1){
                start++;
            }
            while (start < end && (array[end] & 1) == 0){
                end--;
            }
            if(start < end){
                int temp = array[start];
                array[start] = array[end];
                array[end] = temp;
            }
        }
    }

    /*
    *   输入一个整数数组，实现一个函数来调整该数组中数字的顺序.
    *   使得所有的奇数位于数组的前半部分，所有的偶数位于位于数组的后半部分.
    *   并保证奇数和奇数，偶数和偶数之间的相对位置不变.
    * */
    public static void problem6(int[] array){
        if(array == null || array.length == 0){
            return;
        }
        int[] newArray = new int[array.length];
        int index = 0;
        for (int i = 0; i < array.length; i++){
            if((array[i] & 1) ==1){
                newArray[index] = array[i];
                index++;
            }
        }
        for(int i = 0; i < array.length; i++){
            if((array[i] & 1) == 0){
                newArray[index] = array[i];
                index++;
            }
        }
        for(int i = 0; i < array.length; i++){
            array[i] = newArray[i];
        }
    }

    public static void main(String[] args) {
//        int[][] array = {{2,8,9},{7,9,12}};
//        boolean res = Problem.problem4(array,8);
//        System.out.println(res);

        int[] array = {1,2,3,4,5};
        problem6(array);
//        problem5(array);
        for (int a: array) {
            System.out.print(a + " ");
        }
    }
}
