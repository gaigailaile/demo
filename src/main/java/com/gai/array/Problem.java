package com.gai.array;

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

    public static void main(String[] args) {
//        int[] array = {2,3,1,0,2,5,3};
//        int num = Problem.problem1(array);
        int[] array = {2,3,5,4,1,2,6,7};
        int num = Problem.problem2(array);
        System.out.println(num);
        System.out.println("----------------");
        for (int a:array) {
            System.out.print(a + " ");
        }
    }
}
