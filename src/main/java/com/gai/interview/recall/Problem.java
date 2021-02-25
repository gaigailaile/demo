package com.gai.interview.recall;

public class Problem {

    /*
    *   问题一
    *   请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
    *   路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向左，向右，向上，向下移动一个格子。
    *   如果一条路径经过了矩阵中的某一个格子， 则该路径不能再进入该格子。
    * */
    public static boolean problem1(char[][] matrix,char[] str){
        if(matrix == null || str == null){
            return false;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        //标记相应位置是否走过
        int[][] flag = new int[rows][cols];
        //str数组的下角标当内容全部匹配上时应与str长度一致
        int pathLength = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++){
                if(hasPath(matrix,str,flag,i,j,pathLength)){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean hasPath(char[][] matrix,char[] str,int[][] flag,int row,int col,int pathLength){
        if(pathLength == str.length){
            return true;
        }
        if(row >= 0 && row < matrix.length
                && col >= 0 && col < matrix[0].length
                && matrix[row][col] == str[pathLength]
                && flag[row][col] == 0){
            flag[row][col] = 1;
            if(hasPath(matrix,str,flag,row,col-1,pathLength+1)
                    || hasPath(matrix,str,flag,row+1,col,pathLength+1)
                    || hasPath(matrix,str,flag,row,col+1,pathLength+1)
                    || hasPath(matrix,str,flag,row-1,col,pathLength+1)){
                return true;
            }
            flag[row][col] = 0;
        }
        return false;
    }

    /*
     *   问题一
     *   剑指offer的解法
     * */
    public static boolean problem1(char[] matrix,int rows,int cols,char[] str){
        int flag[] = new int[matrix.length];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (helper(matrix, rows, cols, i, j, str, 0, flag))
                    return true;
            }
        }
        return false;
    }

    private static boolean helper(char[] matrix,int rows,int cols,int i,int j,char[] str,int k,int[] flag) {
        int index = i * cols + j;
        if (i < 0 || i >= rows || j < 0 || j >= cols || matrix[index] != str[k] || flag[index] == 1)
            return false;
        if(k == str.length - 1)
            return true;
        flag[index] = 1;
        if (helper(matrix, rows, cols, i - 1, j, str, k + 1, flag)
                || helper(matrix, rows, cols, i + 1, j, str, k + 1, flag)
                || helper(matrix, rows, cols, i, j - 1, str, k + 1, flag)
                || helper(matrix, rows, cols, i, j + 1, str, k + 1, flag)) {
            return true;
        }
        flag[index] = 0;
        return false;
    }

    /*
    *   问题2
    *   地上有一个 m 行和 n 列的方格。
    *   一个机器人从坐标 0,0 的格子开始移动。
    *   每一次只能向左，右，上，下四个方向移动一格。
    *   但是不能进入行坐标和列坐标的数位之和大于 k 的格子。请问该机器人能够达到多少个格子？
    *   例如k=18时,m1 (35,37)可以进入，但是m2 (35,39)不能进入。
    *   因为m1 3+5+3+7=18 而m2 3+5+3+9=20
    * */
    public static int problem2(int m,int n,int k) throws Exception {
        int count = 0;
        if(m <= 0 || n <= 0){
            throw new Exception("参数不合法");
        }
        if(k < 0){
            return count;
        }
        int[][] flag = new int[m][n];
        return go(0,0,m,n,flag,k);
    }

    /*
    *   i 当前行坐标
    *   j 当前列坐标
    *   m 矩阵行数
    *   n 矩阵列数
    *   flag 标识矩阵 0未到达过 1已到达果
    *   k 限制能否进入的标志
    * */
    public static int go(int i,int j,int m,int n,int[][] flag,int k){
        if(i < 0 || i >= m
                || j < 0 || j >= n
                || flag[i][j] == 1
                || getDigitSum(i) + getDigitSum(j) > k){
            return 0;
        }
        flag[i][j] = 1;
        return 1 + go(i,j-1,m,n,flag,k)
                + go(i+1,j,m,n,flag,k)
                + go(i,j+1,m,n,flag,k)
                + go(i-1,j,m,n,flag,k);
    }

    public static int getDigitSum(int m){
        int sum = 0;
        while (m > 0){
            sum += m%10;
            m /= 10;
        }
        return sum;
    }



    public static void main(String[] args) throws Exception {
//        char[][] matrix = new char[3][4];
//        char ch1[] = { 'a', 'b', 'c', 'd' };
//        char ch2[] = { 'b', 'c', 'd', 'a' };
//        char ch3[] = { 'c', 'd', 'a', 'b' };
//        matrix[0] = ch1;
//        matrix[2] = ch3;
//        matrix[1] = ch2;
//        char str[] = { 'a', 'b', 'c', 'd' };
//        boolean b = problem1(matrix, str);
//        System.out.println(b);

//        char[] matrix = {'a', 'b', 'c', 'd' ,'b', 'c', 'd', 'a','c', 'd', 'a', 'b'};
//        char str[] = { 'a', 'b', 'c', 'd' };
//        boolean b = problem1(matrix,3,4,str);
//        System.out.println(b);

        System.out.println(problem2(23, 35, 4));
    }
}
