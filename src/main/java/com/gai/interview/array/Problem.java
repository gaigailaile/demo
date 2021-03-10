package com.gai.interview.array;

import org.apache.spark.sql.sources.In;

import java.util.*;

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

    /*
    *   输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，例如，如果输入如下 4X4 矩阵
    *    1  2  3  4
    *    5  6  7  8
    *    9 10 11 12
    *   13 14 15 16
    *   则依次打印出数字 1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10
    * */
    public static List<Integer> problem7(int[][] array){
        List<Integer> result = new ArrayList<>();
        if(array == null || array.length <= 0 || array[0].length <= 0){
            return result;
        }
        int leftTopX = 0;
        int leftTopY = 0;
        int rightBottomX = array.length-1;
        int rightBottomY = array[0].length-1;

        while (rightBottomX >= leftTopX
                && rightBottomY >= leftTopY){
            //从左向右
            for(int col = leftTopY; col <= rightBottomY; col++){
                result.add(array[leftTopX][col]);
            }
            //从上向下
            for (int row = leftTopX + 1; row <= rightBottomX; row++){
                result.add(array[row][rightBottomY]);
            }
            if(rightBottomX > leftTopX
                    && rightBottomY > leftTopY){
                //从右向左
                for (int col = rightBottomY - 1; col > leftTopY; col--){
                    result.add(array[rightBottomX][col]);
                }
                //从下向上
                for (int row = rightBottomX; row > leftTopX; row--){
                    result.add(array[row][leftTopY]);
                }
            }
            leftTopX++;
            leftTopY++;
            rightBottomX--;
            rightBottomY--;
        }
        return result;
    }

    /*
    *   问题8
    *   数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。如果不存在则输出 0。
    *
    *   hashMap 解法
    * */
    public static int problem8(int[] array){
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        for (int num:array) {
            if(hashMap.containsKey(num)){
                hashMap.put(num,hashMap.getOrDefault(num,0) + 1);
            }else {
                hashMap.put(num,1);
            }

            if(hashMap.get(num) > array.length/2 ){
                return num;
            }
        }
        return 0;
    }

    /*
    *   问题9
    *
    *   输入 n 个整数，找出其中最小的 K 个数。
    *   例如输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
    *
    *   使K左边的数值比K小右边比K值大
    * */
    public static ArrayList<Integer> problem9(int[] array,int k){
        if(array == null || array.length == 0 || k <= 0){
            return null;
        }
        ArrayList<Integer> result = new ArrayList<>();
        int low = 0;
        int high = array.length - 1;
        int index = partition(array,low,high);

        while (index != k-1){
            if(index > k-1){
                high = index - 1;
                index = partition(array,low,high);
            }else {
                low = index + 1;
                index = partition(array,low,high);
            }
        }

        for (int i = 0; i < k; i++)
            result.add(array[i]);

        return result;
    }

    public static int partition(int[] array,int low,int high){
        int temp = array[low];
        while (low != high){
            while (low < high && array[high] >= temp)
                high--;
            array[low] = array[high];
            while (low < high && array[low] <= temp)
                low++;
            array[high] = array[low];
        }
        array[low] = temp;
        return low;
    }

    /*
    *   问题9
    *
    *   使用大根堆解决
    * */
    public static int[] problem9(int[] array,int k,String queueType){
        if(array == null || array.length == 0 || k <= 0){
            return null;
        }
        Queue<Integer> queue = new PriorityQueue<>(k,(v1,v2) -> v2 - v1);
        for (int i = 0; i < array.length; i++){
            if(queue.size() < k){
                queue.offer(array[i]);
            }else if(queue.size() == k && array[i] < queue.peek()){
                queue.poll();
                queue.offer(array[i]);
            }
        }
        int[] result = new int[queue.size()];
        int index = 0;
        for (int i:queue) {
            result[index++] = i;
        }

        return result;
    }

    /*
    *   输入一个整型数组，数组中有正数也有负数，数组中一个或连续的多个整数组成一个子数组，求连续子数组的最大和
    * */
    public static int problem10(int[] array){
        if(array == null || array.length == 0){
            return 0;
        }
        int sum = 0;
        int result = array[0];
        for (int num:array){
            sum = sum > 0 ? sum + num : num;
            result = Math.max(sum,result);
        }
        return result;
    }

    /*
    *   输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
    * */
    public static String problem11(int[] array){
        String[] str = new String[array.length];
        for (int i = 0; i < array.length; i++)
            str[i] = String.valueOf(array[i]);
        Arrays.sort(str,(x,y) -> (x+y).compareTo(y+x));
        StringBuffer stringBuffer = new StringBuffer();
        for (String s:str) {
            stringBuffer.append(s);
        }
        return stringBuffer.toString();
    }

    /*
    *   问题12
    *
    *   在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。
    *   你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。
    *   给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
    *
    * */
    public static int problem12(int[][] array){
        int row = array.length;
        int col = array[0].length;

        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){
                if(i == 0 && j == 0)
                    continue;
                if(i == 0)
                    array[i][j] += array[i][j - 1];
                else if(j == 0)
                    array[i][j] += array[i - 1][j];
                else
                    array[i][j] += Math.max(array[i][j - 1],array[i - 1][j]);
            }
        }

        return array[row-1][col-1];
    }

    /*
    *   问题12
    *
    *   升级版
    * */
    public static int problem12(int[][] array,int a){
        int row = array.length;
        int col = array[0].length;

        for (int i = 1; i < col; i++)
            array[0][i] += array[0][i - 1];

        for (int i = 1; i < row; i++)
            array[i][0] += array[i-1][0];

        for (int i = 1; i < row; i++)
            for (int j = 1; j < col; j++)
                array[i][j] += Math.max(array[i - 1][j],array[i][j - 1]);

        return array[row-1][col-1];
    }

    /*
    *   在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
    *   输入一个数组，求出这个数组中的逆序对的总数。
    * */
    public static int problem13(int[] nums){
        int len = nums.length;
        if(len < 2){
            return 0;
        }
        int[] copy = new int[len];
        for (int i = 0; i < len; i++){
            copy[i] = nums[i];
        }
        int[] temp = new int[len];
        return reversePairs(copy,0,len-1,temp);
    }

    private static int reversePairs(int[] nums, int left, int right, int[] temp){
        if(left == right){
            return 0;
        }
        //计算中间值，可以防止溢出
        int mid = left + (right - left) / 2;
        int leftPairs = reversePairs(nums,left,mid,temp);
        int rightPairs = reversePairs(nums,mid+1,right,temp);

        if(nums[mid] <= nums[mid + 1]){
            return leftPairs + rightPairs;
        }

        int countPairs = mergeAndCount(nums,left,mid,right,temp);
        return leftPairs + rightPairs + countPairs;
    }

    private static int mergeAndCount(int[] nums,int left,int mid,int right,int[] temp){
        for (int i = left; i <= right; i++){
            temp[i] = nums[i];
        }
        int i = left;
        int j = mid + 1;

        int count = 0;
        for (int k = left; k <= right; k++){
            if(i == mid + 1){
                nums[k] = temp[j];
                j++;
            }else if(j == right + 1){
                nums[k] = temp[i];
                i++;
            }else if(temp[i] <= temp[j]){
                nums[k] = temp[i];
                i++;
            }else {
                nums[k] = temp[j];
                j++;
                count += (mid - i + 1);
            }
        }
        return count;
    }

    /*
    *   统计一个数字在排序数组中出现的次数。
    * */
    public static int problem14(int[] nums,int target){
        //探索右边界
        int i = 0,j = nums.length - 1;
        while (i <= j){
            int m = (i + j) / 2;
            if(nums[m] <= target){
                i = m + 1;
            }else {
                j = m - 1;
            }
        }
        //没有target直接返回
        if(j >= 0 && nums[j] != target) return 0;
        int right = i;

        //探索左边界
        i = 0;
        j = nums.length - 1;
        while (i <= j){
            int m = (i + j) / 2;
            if(nums[m] < target){
                i = m + 1;
            }else {
                j = m - 1;
            }
        }
        int left = j;
        return right - left - 1;
    }

    /*
    *   问题14 优化版
    * */
    public static int problem14(int[] nums,int target,int a){
        return helper(nums,target) - helper(nums,target-1);
    }

    private static int helper(int[] nums,int target){
        int i = 0,j = nums.length - 1;
        while (i <= j){
            int m = (i + j)/2;
            if(nums[m] <= target) i = m + 1;
            else j = m - 1;
        }
        return i;
    }

    public static void main(String[] args) {
    }
}
