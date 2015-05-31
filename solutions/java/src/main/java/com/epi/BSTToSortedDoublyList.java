package com.epi;

import com.epi.BinarySearchTreePrototypeTemplate.BSTNode;

public class BSTToSortedDoublyList {
  // @include
  // Transform a BST into a circular sorted doubly linked list in-place,
  // return the head of the list.
  public static BSTNode<Integer> bstToDoublyLinkedList(BSTNode<Integer> tree) {
    // Empty subtree.
    if (tree == null) {
      return null;
    }

    // Recursively build the list from left and right subtrees.
    BSTNode<Integer> lHead = bstToDoublyLinkedList(tree.getLeft());
    BSTNode<Integer> rHead = bstToDoublyLinkedList(tree.getRight());

    // Append tree to the list from left subtree.
    BSTNode<Integer> lTail = null;
    if (lHead != null) {
      lTail = lHead.getLeft();
      lTail.setRight(tree);
      tree.setLeft(lTail);
      lTail = tree;
    } else {
      lHead = lTail = tree;
    }

    // Append the list from right subtree to tree.
    BSTNode<Integer> rTail = null;
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
    BSTNode<Integer> tree = new BSTNode<>(3);
    tree.setLeft(new BSTNode<>(2));
    tree.getLeft().setLeft(new BSTNode<>(1));
    tree.setRight(new BSTNode<>(5));
    tree.getRight().setLeft(new BSTNode<>(4));
    tree.getRight().setRight(new BSTNode<>(6));
    BSTNode<Integer> L = bstToDoublyLinkedList(tree);
    BSTNode<Integer> curr = L;
    int pre = Integer.MIN_VALUE;
    do {
      assert(pre <= curr.getData());
      System.out.println(curr.getData());
      pre = curr.getData();
      curr = curr.getRight();
    } while (curr != L);
  }
}
