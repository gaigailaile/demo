package com.gai.interview.tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

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

    /*
    *   输入两棵二叉树 A，B，判断 B 是不是 A 的子结构。
    * */
    public static boolean problem3(TreeNode node,TreeNode target){
        if(target == null){
            return true;
        }
        if(node == null){
            return false;
        }
        if(hasChild(node,target)){
            return true;
        }

        return problem3(node.left,target) || problem3(node.right,target);
    }

    public static boolean hasChild(TreeNode node,TreeNode target){
        if (target == null) {
            return true;
        }
        if (node == null && target != null) {
            return false;
        }
        if(node.value != target.value){
            return false;
        }
        return hasChild(node.right,target.right) && hasChild(node.left,target.left);
    }

    /*
    *   请完成一个函数，输入一棵二叉树，该函数输出它的镜像。
    */
    public static void problem4(TreeNode root){
        if(root == null){
            return;
        }
        if(root.left == null && root.right == null){
            return;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        problem4(root.left);
        problem4(root.right);
    }

    /*
    *   请实现一个函数，用来判断一颗二叉树是不是对称的。注意：如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
    * */
    public static boolean problem5(TreeNode root){
        return root == null || isImage(root.right,root.left);
    }

    public static boolean isImage(TreeNode root,TreeNode root2){
        if(root == null && root2 == null){
            return true;
        }
        if(root == null || root2 == null){
            return false;
        }
        if(root.value != root2.value){
            return false;
        }
        return isImage(root.right,root2.left) && isImage(root.left,root2.right);
    }

    /*
    *   从上往下打印二叉树的每个节点，同一层的节点按照从左到右的顺序打印。
    *   例如输入下图的二叉树，则一次打印出8，6，10，5，7，9，11。
    * */
    public static void problem6(TreeNode root){
        if(root == null){
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            System.out.print(node.value + " ");
            if(node.left != null){
                queue.add(node.left);
            }
            if(node.right != null){
                queue.add(node.right);
            }
        }
    }

    /*
    *   从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印 ，每一层打印一行。
    * */
    public static void problem7(TreeNode root){
        if(root == null){
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int nextLevelNum = 0;
        int toBePrinted = 1;
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            System.out.print(node.value + " ");
            if(node.left != null){
                queue.add(node.left);
                nextLevelNum++;
            }
            if(node.right != null){
                queue.add(node.right);
                nextLevelNum++;
            }
            toBePrinted--;
            if(toBePrinted == 0){
                toBePrinted = nextLevelNum;
                nextLevelNum = 0;
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
//        int[] pre = {1, 2, 4, 7, 3, 5, 6, 8};
//        int[] in = {4, 7, 2, 1, 5, 3, 8, 6};
//        int[] pos = {7, 4, 2, 5, 8, 6, 3, 1};
//        TreeNode treeNode = Problem.problem1(pre,in);
//        TreeNode.last(treeNode);

        TreeNode nodeRoot = new TreeNode(8);
        TreeNode node1 = new TreeNode(7);
        TreeNode node2 = new TreeNode(6);
        nodeRoot.left = node1;
        nodeRoot.right = node2;
        TreeNode node3 = new TreeNode(5);
        TreeNode node4 = new TreeNode(4);
        node1.left = node3;
        node1.right = node4;
        TreeNode node5 = new TreeNode(3);
        TreeNode node6 = new TreeNode(2);
        node2.left = node5;
        node2.right = node6;

        problem7(nodeRoot);

    }
}
