package com.gai.tree;

import java.util.Arrays;

public class Problem {

    /*
    *   问题1
    *   输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
    *   假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
    *   例如输入前序遍历序列 {1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
    * */
    public static TreeNode problem1(int[] pre,int[] in){
        if(pre == null || in == null || pre.length == 0 || in.length == 0){
            return null;
        }

        if(pre.length != in.length){
            return null;
        }

        TreeNode root = new TreeNode(pre[0]);
        for(int i = 0; i < in.length; i++){
            if(pre[0] == in[i]){
                root.left = problem1(Arrays.copyOfRange(pre,1,i+1),
                        Arrays.copyOfRange(in,0,i));
                root.right = problem1(Arrays.copyOfRange(pre,i+1,pre.length),
                        Arrays.copyOfRange(in,i+1,in.length));
            }
        }

        return root;
    }

    /*
    *   问题一 原生解法
    * */
    public static TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        // 递归出口
        if (pre.length == 0 || in.length == 0 || pre.length != in.length) {
            return null;
        }

        // 拿到根结点
        TreeNode root = new TreeNode(pre[0]);
        // 找的根结点在中序遍历中的位置
        int i = 0;

        while (in[i] != root.value) {
            i++;
        }

        //确定左子树前序遍历长度
        int[] preLeft = new int[i];
        //确定左子树中序遍历长度
        int[] inLeft = new int[i];


        //确定右子树前序遍历长度
        int[] preRight = new int[in.length - i - 1];
        //确定右子树中序遍历长度
        int[] inRight = new int[in.length - i - 1];


        // 遍历 依次拿到左右子树  前中序遍历的值
        for (int j = 0; j < in.length; j++) {
            if (j < i) {
                preLeft[j] = pre[j + 1];
                inLeft[j] = in[j];
            } else if (j > i) {
                preRight[j - i - 1] = pre[j];
                inRight[j - i - 1] = in[j];
            }
        }

        // 递归
        root.left = reConstructBinaryTree(preLeft, inLeft);
        root.right = reConstructBinaryTree(preRight, inRight);

        return root;
    }

    /*
    *   问题2
    *   给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。
    *   注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
    * */
    public static TreeNode problem2(TreeNode node){
        if(node == null){
            return null;
        }
        TreeNode nextNode = null;
        if(node.right != null){
            TreeNode right = node.right;
            while (right.left != null)
                right = right.left;
            nextNode = right;
        } else if(node.parent != null){
            TreeNode current = node;
            TreeNode parentNode = node.parent;
            while (parentNode != null && current == parentNode.right){
                current = parentNode;
                parentNode = parentNode.parent;
            }
            nextNode = parentNode;
        }
        return nextNode;
    }

    public static void main(String[] args) {
        int[] pre = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] in = {4, 7, 2, 1, 5, 3, 8, 6};
        int[] pos = {7, 4, 2, 5, 8, 6, 3, 1};
        TreeNode treeNode = Problem.problem1(pre,in);
        TreeNode.last(treeNode);
    }
}
