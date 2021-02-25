package com.gai.interview.tree;

public class TreeNode {
    int value;
    TreeNode left;
    TreeNode right;
    TreeNode parent;

    public TreeNode(int value){
        this.value = value;
    }

    public static void last(TreeNode tr) {
        if (tr == null) {
            return;
        }

        last(tr.left);
        last(tr.right);
        System.out.print(tr.value + " ");
    }
}
