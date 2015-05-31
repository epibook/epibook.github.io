package com.epi;

import com.epi.BinarySearchTreePrototypeTemplate.BSTNode;

public class MergeTwoBSTs {
  private static BSTNode<Integer> head;

  private static BSTNode<Integer> buildSortedDoublyLinkedList(BSTNode<Integer> L,
                                                              int n) {
    head = L;
    return buildSortedDoublyLinkedListHelper(0, n);
  }

  // Build a BST from the (s + 1)-th to the e-th node in L.
  private static BSTNode<Integer> buildSortedDoublyLinkedListHelper(int s,
                                                                    int e) {
    if (s >= e) {
      return null;
    }

    int m = s + ((e - s) / 2);
    BSTNode<Integer> left = buildSortedDoublyLinkedListHelper(s, m);
    BSTNode<Integer> curr = new BSTNode<>(head.getData());
    head = head.getRight();
    curr.setLeft(left);
    curr.setRight(buildSortedDoublyLinkedListHelper(m + 1, e));
    return curr;
  }

  // Transform a BST into a circular sorted doubly linked list in-place,
  // return the head of the list.
  private static <T> BSTNode<T> bstToDoublyLinkedList(BSTNode<T> n) {
    // Empty subtree.
    if (n == null) {
      return null;
    }

    // Recursively build the list from left and right subtrees.
    BSTNode<T> lHead = bstToDoublyLinkedList(n.getLeft());
    BSTNode<T> rHead = bstToDoublyLinkedList(n.getRight());

    // Append n to the list from left subtree.
    BSTNode<T> lTail = null;
    if (lHead != null) {
      lTail = lHead.getLeft();
      lTail.setRight(n);
      n.setLeft(lTail);
      lTail = n;
    } else {
      lHead = lTail = n;
    }

    // Append the list from right subtree to n.
    BSTNode<T> rTail = null;
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
  private static <T> int countLength(BSTNode<T> L) {
    int len = 0;
    while (L != null) {
      ++len;
      L = L.getRight();
    }
    return len;
  }

  // @include
  public static BSTNode<Integer> mergeTwoBSTs(BSTNode<Integer> A,
                                              BSTNode<Integer> B) {
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
  private static BSTNode<Integer> mergeTwoSortedLinkedLists(BSTNode<Integer> A,
                                                            BSTNode<Integer> B) {
    BSTNode<Integer> dummyHead = new BSTNode<>();
    BSTNode<Integer> current = dummyHead;
    BSTNode<Integer> p1 = A;
    BSTNode<Integer> p2 = B;

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

  private static <T extends Comparable<T>> void printBstInOrder(BSTNode<T> n,
                                                                T pre) {
    if (n != null) {
      printBstInOrder(n.getLeft(), pre);
      assert(pre.compareTo(n.getData()) <= 0);
      System.out.print(n.getData() + " ");
      printBstInOrder(n.getRight(), n.getData());
    }
  }

  public static void main(String[] args) {
    // 3
    // 2 5
    // 1 4 6
    BSTNode<Integer> L = new BSTNode<>(3);
    L.setLeft(new BSTNode<>(2));
    L.getLeft().setLeft(new BSTNode<>(1));
    L.setRight(new BSTNode<>(5));
    L.getRight().setLeft(new BSTNode<>(4));
    L.getRight().setRight(new BSTNode<>(6));
    // 7
    // 2 8
    // 0
    BSTNode<Integer> R = new BSTNode<>(7);
    R.setLeft(new BSTNode<>(2));
    R.getLeft().setLeft(new BSTNode<>(0));
    R.setRight(new BSTNode<>(8));

    BSTNode<Integer> root = mergeTwoBSTs(L, R);
    // should output 0 1 2 2 3 4 5 6 7 8
    printBstInOrder(root, Integer.MIN_VALUE);
  }
}
