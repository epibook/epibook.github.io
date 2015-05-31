package com.epi;

import com.epi.BinaryTreeWithParentPrototype.BinaryTree;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class Successor {
  // @include
  public static BinaryTree<Integer> findSuccessor(BinaryTree<Integer> node) {
    BinaryTree<Integer> iter = node;
    if (iter.getRight() != null) {
      // Find the leftmost element in node's right subtree.
      iter = iter.getRight();
      while (iter.getLeft() != null) {
        iter = iter.getLeft();
      }
      return iter;
    }

    // Find the closest ancestor whose left subtree contains node.
    while (iter.getParent() != null && iter.getParent().getRight() == iter) {
      iter = iter.getParent();
    }
    // A return value of null means node does not have successor, i.e., it is
    // the rightmost node in the tree.
    return iter.getParent();
  }
  // @exclude

  public static void main(String[] args) {
    // 3
    // 2 5
    // 1 4 6
    BinaryTree<Integer> root = new BinaryTree<>(3, null, null);
    root.setLeft(new BinaryTree<>(2, null, null));
    root.getLeft().setParent(root);
    root.getLeft().setLeft(new BinaryTree<>(1, null, null));
    root.getLeft().getLeft().setParent(root.getLeft());
    root.setRight(new BinaryTree<>(5, null, null));
    root.getRight().setParent(root);
    root.getRight().setLeft(new BinaryTree<>(4, null, null));
    root.getRight().getLeft().setParent(root.getRight());
    root.getRight().setRight(new BinaryTree<>(6, null, null));
    root.getRight().getRight().setParent(root.getRight());
    // should output 6
    BinaryTree<Integer> node = findSuccessor(root.getRight());
    assert(node.getData().equals(6));
    System.out.println(node.getData());
    // should output "null"
    node = findSuccessor(root.getRight().getRight());
    assert(node == null);
    if (node != null) {
      System.out.println(node.getData());
    } else {
      System.out.println("null");
    }
  }
}
