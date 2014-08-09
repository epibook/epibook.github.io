package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class MergeTwoBSTs {
  private static BinaryTree<Integer> head;

  private static BinaryTree<Integer> buildSortedDoublyLinkedList(
      BinaryTree<Integer> L, int n) {
    head = L;
    return buildSortedDoublyLinkedListHelper(0, n);
  }

  // Build a BST from the (s + 1)-th to the e-th node in L.
  private static BinaryTree<Integer> buildSortedDoublyLinkedListHelper(int s,
                                                                       int e) {
    if (s >= e) {
      return null;
    }

    int m = s + ((e - s) / 2);
    BinaryTree<Integer> left = buildSortedDoublyLinkedListHelper(s, m);
    BinaryTree<Integer> curr = new BinaryTree<>(head.getData());
    head = head.getRight();
    curr.setLeft(left);
    curr.setRight(buildSortedDoublyLinkedListHelper(m + 1, e));
    return curr;
  }

  // Transform a BST into a circular sorted doubly linked list in-place,
  // return the head of the list.
  private static <T> BinaryTree<T> bstToDoublyLinkedList(BinaryTree<T> n) {
    // Empty subtree.
    if (n == null) {
      return null;
    }

    // Recursively build the list from left and right subtrees.
    BinaryTree<T> lHead = bstToDoublyLinkedList(n.getLeft());
    BinaryTree<T> rHead = bstToDoublyLinkedList(n.getRight());

    // Append n to the list from left subtree.
    BinaryTree<T> lTail = null;
    if (lHead != null) {
      lTail = lHead.getLeft();
      lTail.setRight(n);
      n.setLeft(lTail);
      lTail = n;
    } else {
      lHead = lTail = n;
    }

    // Append the list from right subtree to n.
    BinaryTree<T> rTail = null;
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

  // Count the list length till end.
  private static <T> int countLength(BinaryTree<T> L) {
    int len = 0;
    while (L != null) {
      ++len;
      L = L.getRight();
    }
    return len;
  }

  // @include
  public static BinaryTree<Integer> mergeTwoBSTs(BinaryTree<Integer> A,
                                                 BinaryTree<Integer> B) {
    A = bstToDoublyLinkedList(A);
    B = bstToDoublyLinkedList(B);
    A.getLeft().setRight(null);
    B.getLeft().setRight(null);
    A.setLeft(null);
    B.setLeft(null);
    int ALength = countLength(A);
    int BLength = countLength(B);
    return buildSortedDoublyLinkedList(mergeTwoSortedLinkedLists(A, B),
                                       ALength + BLength);
  }
  // @exclude

  // Merge two sorted linked lists, return the head of list.
  private static BinaryTree<Integer> mergeTwoSortedLinkedLists(
      BinaryTree<Integer> A, BinaryTree<Integer> B) {
    BinaryTree<Integer> dummyHead = new BinaryTree<>();
    BinaryTree<Integer> current = dummyHead;
    BinaryTree<Integer> p1 = A;
    BinaryTree<Integer> p2 = B;

    while (p1 != null && p2 != null) {
      if (p1.getData().compareTo(p2.getData()) < 0) {
        current.setRight(p1);
        p1 = p1.getRight();
      } else {
        current.setRight(p2);
        p2 = p2.getRight();
      }
      current = current.getRight();
    }

    if (p1 != null) {
      current.setRight(p1);
    }
    if (p2 != null) {
      current.setRight(p2);
    }
    return dummyHead.getRight();
  }

  private static <T extends Comparable<T>> void printBstInOrder(
      BinaryTree<T> n, T pre) {
    if (n != null) {
      printBstInOrder(n.getLeft(), pre);
      assert (pre.compareTo(n.getData()) <= 0);
      System.out.print(n.getData() + " ");
      printBstInOrder(n.getRight(), n.getData());
    }
  }

  public static void main(String[] args) {
    // 3
    // 2 5
    // 1 4 6
    BinaryTree<Integer> L = new BinaryTree<>(3);
    L.setLeft(new BinaryTree<>(2));
    L.getLeft().setLeft(new BinaryTree<>(1));
    L.setRight(new BinaryTree<>(5));
    L.getRight().setLeft(new BinaryTree<>(4));
    L.getRight().setRight(new BinaryTree<>(6));
    // 7
    // 2 8
    // 0
    BinaryTree<Integer> R = new BinaryTree<>(7);
    R.setLeft(new BinaryTree<>(2));
    R.getLeft().setLeft(new BinaryTree<>(0));
    R.setRight(new BinaryTree<>(8));

    BinaryTree<Integer> root = mergeTwoBSTs(L, R);
    // should output 0 1 2 2 3 4 5 6 7 8
    printBstInOrder(root, Integer.MIN_VALUE);
  }
}
