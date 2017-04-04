/*
   @slug
   is-binary-tree-balanced

   @title
   Test if a binary tree is balanced.

   @problem
   A binary tree is said to be balanced if for each node in the tree,
   the difference in the height of its left and right subtrees is at most one.
   A perfect binary tree is balanced, as is a complete binary tree.
   A balanced binary tree does not have to be perfect or complete---see the
   figure for an example.
   <p>

   Write a program that takes as input the root of a binary tree
   and checks whether the tree is balanced.
   <p>

   The binary tree class is

<pre>
   class BinaryTreeNode<T> {
       public T data;
       public BinaryTreeNode<T> left, right;
   }

   public BinaryTreeNode(T data) { this.data = data; }

   public BinaryTreeNode(T data, BinaryTreeNode<T> left,
                          BinaryTreeNode<T> right) {
     this.data = data;
     this.left = left;
     this.right = right;
   }
</pre>
<p>


   <img src="/binary-tree.png"></img>

   @hint
   Think of a classic binary tree algorithm.

*/

package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTreeNode;

public class BalancedBinaryTree {
  // @include
  private static class BalanceStatusWithHeight {
    public boolean balanced;
    public int height;

    public BalanceStatusWithHeight(boolean balanced, int height) {
      this.balanced = balanced;
      this.height = height;
    }
  }

  // @judge-include-display
  public static boolean isBalanced(BinaryTreeNode<Integer> tree) {
    // @judge-exclude-display
    return checkBalanced(tree).balanced;
    // @judge-include-display
  }
  // @judge-exclude-display

  private static BalanceStatusWithHeight checkBalanced(
      BinaryTreeNode<Integer> tree) {
    if (tree == null) {
      return new BalanceStatusWithHeight(true, -1); // Base case.
    }

    BalanceStatusWithHeight leftResult = checkBalanced(tree.left);
    if (!leftResult.balanced) {
      return leftResult; // Left subtree is not balanced.
    }
    BalanceStatusWithHeight rightResult = checkBalanced(tree.right);
    if (!rightResult.balanced) {
      return rightResult; // Right subtree is not balanced.
    }

    boolean isBalanced = Math.abs(leftResult.height - rightResult.height) <= 1;
    int height = Math.max(leftResult.height, rightResult.height) + 1;
    return new BalanceStatusWithHeight(isBalanced, height);
  }
  // @exclude

  public static void main(String[] args) {
    // balanced binary tree test
    // 3
    // 2 5
    // 1 4 6
    BinaryTreeNode<Integer> tree = new BinaryTreeNode<>();
    tree.left = new BinaryTreeNode<Integer>();
    tree.left.left = new BinaryTreeNode<Integer>();
    tree.right = new BinaryTreeNode<Integer>();
    tree.right.left = new BinaryTreeNode<Integer>();
    tree.right.right = new BinaryTreeNode<Integer>();
    if (!isBalanced(tree)) {
      System.err.println("Incorrect result on balanced tree " + tree);
      System.exit(-1);
    }
    tree = new BinaryTreeNode<>();
    tree.left = new BinaryTreeNode<Integer>();
    tree.left.left = new BinaryTreeNode<Integer>();
    if (isBalanced(tree)) {
      System.err.println("Incorrect result on unbalanced tree: " + tree);
      System.exit(-1);
    }
  }
}
