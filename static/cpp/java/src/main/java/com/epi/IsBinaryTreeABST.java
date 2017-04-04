/*
    @slug
    is-binary-tree-a-bst

    @title
    Test if a binary tree satisfies the BST property

    @problem
    Write a program that takes as input a binary tree
    and checks if the tree satisfies the BST property.
    See the figure for an example of a BST.
    <p>
    <img src="/bst-example.png" width="400px"></img>

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


    @hint
    Is it correct to check for each node that its key is greater
   than or equal to the key at its left child and less than or equal to the key
   at its right child?

 */

package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTreeNode;

public class IsBinaryTreeABST {
  // @include
  // @judge-include-display
  public static boolean isBinaryTreeBST(BinaryTreeNode<Integer> tree) {
    // @judge-exclude-display
    return areKeysInRange(tree, Integer.MIN_VALUE, Integer.MAX_VALUE);
    // @judge-include-display
  }
  // @judge-exclude-display

  private static boolean areKeysInRange(BinaryTreeNode<Integer> tree,
                                        Integer lower, Integer upper) {
    if (tree == null) {
      return true;
    } else if (Integer.compare(tree.data, lower) < 0
               || Integer.compare(tree.data, upper) > 0) {
      return false;
    }

    return areKeysInRange(tree.left, lower, tree.data)
        && areKeysInRange(tree.right, tree.data, upper);
  }
  // @exclude

  static void unitTest(BinaryTreeNode<Integer> root, boolean expectedValue) {
    if (isBinaryTreeBST(root) != expectedValue) {
      System.err.println("Wrong output, got " + (!expectedValue) + ", expected "
                         + expectedValue);
      System.err.println("Tree is " + root);
      System.exit(-1);
    }
  }

  public static void main(String[] args) {
    // 3
    // 2 5
    // 1 4 6
    BinaryTreeNode<Integer> tree = new BinaryTreeNode<>(3);
    tree.left = new BinaryTreeNode<>(2);
    tree.left.left = new BinaryTreeNode<>(1);
    tree.right = new BinaryTreeNode<>(5);
    tree.right.left = new BinaryTreeNode<>(4);
    tree.right.right = new BinaryTreeNode<>(6);
    unitTest(tree, true);
    // 10
    // 2 5
    // 1 4 6
    tree.data = 10;
    // should output false.
    unitTest(tree, false);
    // should output true.
    unitTest(null, true);
  }
}
