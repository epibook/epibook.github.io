package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

public class BSTToSortedDoublyList {
  // @include
  // Transform a BST into a circular sorted doubly linked list in-place,
  // return the head of the list.
  public static BinaryTree<Integer> bstToDoublyLinkedList(
      BinaryTree<Integer> T) {
    // Empty subtree.
    if (T == null) {
      return null;
    }

    // Recursively build the list from left and right subtrees.
    BinaryTree<Integer> lHead = bstToDoublyLinkedList(T.getLeft());
    BinaryTree<Integer> rHead = bstToDoublyLinkedList(T.getRight());

    // Append T to the list from left subtree.
    BinaryTree<Integer> lTail = null;
    if (lHead != null) {
      lTail = lHead.getLeft();
      lTail.setRight(T);
      T.setLeft(lTail);
      lTail = T;
    } else {
      lHead = lTail = T;
    }

    // Append the list from right subtree to T.
    BinaryTree<Integer> rTail = null;
    if (rHead != null) {
      rTail = rHead.getLeft();
      lTail.setRight(rHead);
      rHead.setLeft(lTail);
    } else {
      rTail = lTail;
    }
    rTail.setRight(lHead);
    lHead.setLeft(rTail);

    return lHead;
  }
  // @exclude

  public static void main(String[] args) {
    // 3
    // 2 5
    // 1 4 6
    BinaryTree<Integer> root = new BinaryTree<>(3);
    root.setLeft(new BinaryTree<>(2));
    root.getLeft().setLeft(new BinaryTree<>(1));
    root.setRight(new BinaryTree<>(5));
    root.getRight().setLeft(new BinaryTree<>(4));
    root.getRight().setRight(new BinaryTree<>(6));
    BinaryTree<Integer> L = bstToDoublyLinkedList(root);
    BinaryTree<Integer> curr = L;
    int pre = Integer.MIN_VALUE;
    do {
      assert (pre <= curr.getData());
      System.out.println(curr.getData());
      pre = curr.getData();
      curr = curr.getRight();
    } while (curr != L);
  }
}
