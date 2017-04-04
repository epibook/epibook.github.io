package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTreeNode;

public class TreeTraversal {
  // @include
  public static void treeTraversal(BinaryTreeNode<Integer> root) {
    if (root != null) {
      // Preorder: Processes the root before the traversals of left and right
      // children.
      System.out.println("Preorder: " + root.data);
      treeTraversal(root.left);
      // Inorder: Processes the root after the traversal of left child and
      // before the traversal of right child.
      System.out.println("Inorder: " + root.data);
      treeTraversal(root.right);
      // Postorder: Processes the root after the traversals of left and right
      // children.
      System.out.println("Postorder: " + root.data);
    }
  }
  // @exclude

  public static void main(String[] args) {
    //      3
    //    2   5
    //  1    4 6
    BinaryTreeNode<Integer> tree = new BinaryTreeNode<>(3);
    tree.left = new BinaryTreeNode<>(2);
    tree.left.left = new BinaryTreeNode<>(1);
    tree.right = new BinaryTreeNode<>(5);
    tree.right.left = new BinaryTreeNode<>(4);
    tree.right.right = new BinaryTreeNode<>(6);
    treeTraversal(tree);
  }
}
