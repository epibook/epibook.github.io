package com.epi;

import com.epi.BinarySearchTreePrototypeTemplate.BSTNode;

public class MergeTwoBSTs {
  private static BSTNode<Integer> head;

  private static BSTNode<Integer> buildSortedDoublyLinkedList(
      BSTNode<Integer> L, int n) {
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
    BSTNode<Integer> curr = new BSTNode<>(head.data);
    head = head.right;
    curr.left = left;
    curr.right = buildSortedDoublyLinkedListHelper(m + 1, e);
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
    BSTNode<T> lHead = bstToDoublyLinkedList(n.left);
    BSTNode<T> rHead = bstToDoublyLinkedList(n.right);

    // Append n to the list from left subtree.
    BSTNode<T> lTail = null;
    if (lHead != null) {
      lTail = lHead.left;
      lTail.right = n;
      n.left = lTail;
      lTail = n;
    } else {
      lHead = lTail = n;
    }

    // Append the list from right subtree to n.
    BSTNode<T> rTail = null;
    if (rHead != null) {
      rTail = rHead.left;
      lTail.right = rHead;
      rHead.left = lTail;
    } else {
      rTail = lTail;
    }
    rTail.right = lHead;
    lHead.left = rTail;

    return lHead;
  }

  // Count the list length till end.
  private static <T> int countLength(BSTNode<T> L) {
    int len = 0;
    while (L != null) {
      ++len;
      L = L.right;
    }
    return len;
  }

  // @include
  public static BSTNode<Integer> mergeTwoBSTs(BSTNode<Integer> A,
                                              BSTNode<Integer> B) {
    A = bstToDoublyLinkedList(A);
    B = bstToDoublyLinkedList(B);
    A.left.right = null;
    B.left.right = null;
    A.left = null;
    B.left = null;
    int ALength = countLength(A);
    int BLength = countLength(B);
    return buildSortedDoublyLinkedList(mergeTwoSortedLinkedLists(A, B),
                                       ALength + BLength);
  }
  // @exclude

  // Merge two sorted linked lists, return the head of list.
  private static BSTNode<Integer> mergeTwoSortedLinkedLists(
      BSTNode<Integer> A, BSTNode<Integer> B) {
    BSTNode<Integer> dummyHead = new BSTNode<>();
    BSTNode<Integer> current = dummyHead;
    BSTNode<Integer> p1 = A;
    BSTNode<Integer> p2 = B;

    while (p1 != null && p2 != null) {
      if (Integer.compare(p1.data, p2.data) < 0) {
        current.right = p1;
        p1 = p1.right;
      } else {
        current.right = p2;
        p2 = p2.right;
      }
      current = current.right;
    }

    if (p1 != null) {
      current.right = p1;
    }
    if (p2 != null) {
      current.right = p2;
    }
    return dummyHead.right;
  }

  private static void printBstInorder(BSTNode<Integer> n, Integer pre) {
    if (n != null) {
      printBstInorder(n.left, pre);
      assert(Integer.compare(pre, n.data) <= 0);
      System.out.print(n.data + " ");
      printBstInorder(n.right, n.data);
    }
  }

  public static void main(String[] args) {
    // 3
    // 2 5
    // 1 4 6
    BSTNode<Integer> L = new BSTNode<>(3);
    L.left = new BSTNode<>(2);
    L.left.left = new BSTNode<>(1);
    L.right = new BSTNode<>(5);
    L.right.left = new BSTNode<>(4);
    L.right.right = new BSTNode<>(6);
    // 7
    // 2 8
    // 0
    BSTNode<Integer> R = new BSTNode<>(7);
    R.left = new BSTNode<>(2);
    R.left.left = new BSTNode<>(0);
    R.right = new BSTNode<>(8);

    BSTNode<Integer> root = mergeTwoBSTs(L, R);
    // should output 0 1 2 2 3 4 5 6 7 8
    printBstInorder(root, Integer.MIN_VALUE);
  }
}
