package com.gai.interview.tree;

import java.util.*;

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
    *   问题6
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
    *   问题6 递归方式
    * */
    public static ArrayList<Integer> problem6(TreeNode root,int a) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        if (root == null) {
            return list;
        }
        list.add(root.value);
        levelOrder(root, list);
        return list;
    }

    public static void levelOrder(TreeNode root, ArrayList<Integer> list) {
        if (root == null) {
            return;
        }if (root.left != null) {
            list.add(root.left.value);
        }
        if (root.right != null) {
            list.add(root.right.value);
        }
        levelOrder(root.left, list);
        levelOrder(root.right, list);
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

    /*
    *   请实现一个函数按照之字形打印二叉树.
    *   即第一行按照从左到右的顺序打印.
    *   第二层按照从右至左的顺序打印.
    *   第三行按照从左到右的顺序打印，依此类推。
    * */
    public static void problem8(TreeNode root){
        if(root == null){
            return;
        }
        //奇数行
        Stack<TreeNode> stack1 = new Stack<>();
        stack1.push(root);
        //偶数行
        Stack<TreeNode> stack2 = new Stack<>();
        int level = 1;
        while (!stack1.isEmpty() || !stack2.isEmpty()){
            if((level & 1) == 1){
                while (!stack1.isEmpty()){
                    TreeNode temp = stack1.pop();
                    if(temp.left != null){
                        stack2.push(temp.left);
                    }
                    if(temp.right != null){
                        stack2.push(temp.right);
                    }
                    System.out.print(temp.value + " ");
                }
                level++;
                System.out.println();
            }else {
                while (!stack2.isEmpty()){
                    TreeNode temp = stack2.pop();
                    if(temp.right != null){
                        stack1.push(temp.right);
                    }
                    if(temp.left != null){
                        stack1.push(temp.left);
                    }
                    System.out.print(temp.value + " ");
                }
                level++;
                System.out.println();
            }
        }
    }

    /*
    *   输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
    *   如果是则输出 Yes,否则输出 No。假设输入的数组的任意两个数字都互不相同。
    * */
    public static boolean problem9(int[] array){
        if(array == null || array.length == 0){
            return false;
        }
        int len = array.length - 1;
        int root = array[len];

        int i = 0;
        for (; i < len; i++){
            if(array[i] > root){
                break;
            }
        }

        int j = i;
        for (; j < len; j++){
            if(array[j] < root){
                return false;
            }
        }

        boolean left = true;
        if(i > 0){
            left = problem9(Arrays.copyOfRange(array,0,i));
        }

        boolean right = true;
        if(i < len){
            right = problem9(Arrays.copyOfRange(array,i,len));
        }

        return left && right;
    }

    /*
    *   输入一颗二叉树和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。
    *   路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
    * */
    public static ArrayList<ArrayList<Integer>> problem10(TreeNode root, int target){
        if(root == null){
            return null;
        }
        ArrayList<ArrayList<Integer>> listAll = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        findPath(root,target,listAll,list);

        return listAll;
    }

    public static void findPath(TreeNode node,int target,ArrayList<ArrayList<Integer>> all,ArrayList<Integer> list){
        if(node == null){
            return;
        }
        list.add(node.value);
        target -= node.value;
        if(target == 0 && node.right == null && node.left == null){
            all.add(new ArrayList<>(list));
        }
        findPath(node.right,target,all,list);
        findPath(node.left,target,all,list);
        list.remove(list.size() - 1);
    }

    /*
    *   问题11
    *   输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。
    *   要求不能创建任何新的结点，只能调整树中结点指针的指向。\
    *
    *   递归
    * */
    static TreeNode head = null;
    static TreeNode realHead = null;

    public static TreeNode problem11(TreeNode treeNode) {
        if (treeNode == null) {
            return null;
        }
        problem11(treeNode.left); //左

        if (head == null) { //根
            head = treeNode;
            realHead = treeNode;
        } else {
            head.right = treeNode;
            treeNode.left = head;
            head = treeNode;
        }

        problem11(treeNode.right); //右
        return realHead;
    }

    /*
    *   问题11
    *
    *   递归2
    * */
    public static TreeNode problem11(TreeNode treeNode,String i) {
        TreeNode lastNode = covert(treeNode,null);
        TreeNode head = lastNode;
        while (head != null && head.left != null){
            head = head.left;
        }
        return head;
    }

    public static TreeNode covert(TreeNode current,TreeNode lastNode){
        if(current == null){
            return null;
        }
        if(current.left != null){
            lastNode = covert(current.left,lastNode);
        }
        current.left = lastNode;
        if(lastNode != null){
            lastNode.right = current;
        }
        lastNode = current;
        if(current.right != null){
            lastNode = covert(current.right,lastNode);
        }
        return lastNode;
    }


    /*
    *   问题11
    *   非递归使用栈实现
    * */
    public static TreeNode problem11(TreeNode treeNode,int a) {
        if (treeNode == null) {
            return null;
        }
        TreeNode current = null;
        Stack<TreeNode> stack = new Stack<>();
        while (treeNode != null || !stack.isEmpty()){
            if(treeNode != null){
                stack.push(treeNode);
                treeNode = treeNode.right;
            }else {
                treeNode = stack.pop();
                if(current == null){
                    current = treeNode;
                }else {
                    current.left = treeNode;
                    treeNode.right = current;
                    current = treeNode;
                }
                treeNode = treeNode.left;
            }
        }
        return current;
    }

    /*
    *   请实现两个函数，分别用来序列化和反序列化二叉树
    * */
    public static void problem12(){
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        root.left = node1;
        root.right = node2;
        TreeNode node3 = new TreeNode(4);
        node1.left = node3;
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        node2.left = node5;
        node2.right = node6;

        String str = serialize(root);
        System.out.println(serialize(root));
        TreeNode result = deserialize(str,0);
        System.out.println();
    }

    //序列化
    public static String serialize(TreeNode root){
        StringBuffer str = new StringBuffer();
        if(root == null){
            str.append("#,");
            return str.toString();
        }
        str.append(root.value+",");
        str.append(serialize(root.left));
        str.append(serialize(root.right));
        return str.toString();
    }

    //反序列化
    public static int index = -1;
    public static TreeNode deserialize(String str){
        if(str == null || str.length() == 0){
            return null;
        }
        index++;
        String[] strings = str.split(",");
        TreeNode node = null;
        if(index >= str.length()){
            return null;
        }
        if(!"#".equals(strings[index])){
            node = new TreeNode(Integer.valueOf(strings[index]));
            node.left = deserialize(str);
            node.right = deserialize(str);
        }
        return node;
    }

    /*
    *   不需要index辅助的方法
    * */
    public static TreeNode deserialize(String str,int a){
        if(str == null || str.length() == 0){
            return null;
        }
        String[] strings = str.split(",");
        Queue<String> queue = new LinkedList<String>();
        for(int i = 0; i < strings.length; i++){
            queue.offer(strings[i]);
        }
        return deserialize(queue);
    }

    public static TreeNode deserialize(Queue<String> queue){
        String val = queue.poll();
        if("#".equals(val))
            return null;
        TreeNode node = new TreeNode(Integer.parseInt(val));
        node.left = deserialize(queue);
        node.right = deserialize(queue);
        return node;
    }

    public static void main(String[] args) {
        problem12();
    }
}
