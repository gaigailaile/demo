package com.gai.interview.findandsort;

public class Problem {

    /*
    *   把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
    *   输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
    *   例如 数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为 1。
    *   NOTE：给出的所有元素都大于 0，若数组大小为 0，请返回-1。假设数组中不存在重复元素。
    * */
    public static int problem1(int[] array){
        if(array == null || array.length == 0){
            return -1;
        }
        if(array.length == 1 || array[array.length-1] > array[0]){
            return array[0];
        }
        int low = 0;
        int high = array.length-1;
        while (high >= low){
            int middle = (high + low)/2;
            if(array[middle] > array[middle+1]){
                return array[middle+1];
            }
            if(array[middle-1] > array[middle]){
                return array[middle];
            }
            if(array[middle] > array[0]){
                low = middle + 1;
            }else {
                high = middle - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] a = {3,4,5,1,2};
        int min = problem1(a);
        System.out.println(min);
    }
}
