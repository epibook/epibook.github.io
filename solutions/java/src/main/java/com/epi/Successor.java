package com.epi;

import com.epi.BinaryTreeWithParentPrototype.BinaryTree;

public class Successor {
  // @include
  public static BinaryTree<Integer> findSuccessor(BinaryTree<Integer> node) {
    BinaryTree<Integer> iter = node;
    if (iter.right != null) {
      // Find the leftmost element in node's right subtree.
      iter = iter.right;
      while (iter.left != null) {
        iter = iter.left;
      }
      return iter;
    }

    // Find the closest ancestor whose left subtree contains node.
    while (iter.parent != null && iter.parent.right == iter) {
      iter = iter.parent;
    }
    // A return value of null means node does not have successor, i.e., it is
    // the rightmost node in the tree.
    return iter.parent;
  }
  // @exclude

  public static void main(String[] args) {
    //    3
    //  2   5
    // 1   4 6
    BinaryTree<Integer> root = new BinaryTree<>(3, null, null);
    assert(findSuccessor(root) == null);
    root.left = new BinaryTree<>(2, null, null);
    root.left.parent = root;
    assert(findSuccessor(root.left).data == 3);

    root.left.left = new BinaryTree<>(1, null, null);
    root.left.left.parent = root.left;
    assert(findSuccessor(root.left).data == 3);
    assert(findSuccessor(root.left.left).data == 2);

    root.right = new BinaryTree<>(5, null, null);
    root.right.parent = root;
    root.right.left = new BinaryTree<>(4, null, null);
    root.right.left.parent = root.right;
    root.right.right = new BinaryTree<>(6, null, null);
    root.right.right.parent = root.right;
    // should output 6
    BinaryTree<Integer> node = findSuccessor(root.right);
    assert(node.data.equals(6));
    System.out.println(node.data);
    // should output "null"
    node = findSuccessor(root.right.right);
    assert(node == null);
    if (node != null) {
      System.out.println(node.data);
    } else {
      System.out.println("null");
    }
  }
}
