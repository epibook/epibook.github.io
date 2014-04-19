package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;
import com.epi.utils.Ref;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class MergeTwoBSTs {
  // Build a BST from the (s + 1)-th to the e-th node in L.
  private static <T> BinaryTree<T> fromSortedDoublyLinkedListHelper(
      Ref<BinaryTree<T>> L, int s, int e) {
    BinaryTree<T> curr = null;
    if (s < e) {
      int m = s + ((e - s) >> 1);
      BinaryTree<T> temp = fromSortedDoublyLinkedListHelper(L, s, m);
      curr = L.value;
      curr.setLeft(temp);
      L.value = L.value.getRight();
      curr.setRight(fromSortedDoublyLinkedListHelper(L, m + 1, e));
    }
    return curr;
  }

  private static <T> BinaryTree<T> fromSortedDoublyLinkedList(BinaryTree<T> L,
      int n) {
    return fromSortedDoublyLinkedListHelper(new Ref<BinaryTree<T>>(L), 0, n);
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
  private static <T> int countLen(BinaryTree<T> L) {
    int len = 0;
    while (L != null) {
      ++len;
      L = L.getRight();
    }
    return len;
  }

  // @include
  public static <T extends Comparable<T>> BinaryTree<T> mergeBSTs(
      BinaryTree<T> A, BinaryTree<T> B) {
    // Transform BSTs A and B into sorted doubly lists.
    A = bstToDoublyLinkedList(A);
    B = bstToDoublyLinkedList(B);
    A.getLeft().setRight(null);
    B.getLeft().setRight(null);
    A.setLeft(null);
    B.setLeft(null);
    int lenA = countLen(A);
    int lenB = countLen(B);
    return fromSortedDoublyLinkedList(mergeSortedLinkedLists(A, B), lenA + lenB);
  }

  // Merge two sorted linked lists, return the head of list.
  private static <T extends Comparable<T>> BinaryTree<T> mergeSortedLinkedLists(
      BinaryTree<T> A, BinaryTree<T> B) {
    Ref<BinaryTree<T>> sortedListW = new Ref<BinaryTree<T>>(null);
    Ref<BinaryTree<T>> tailW = new Ref<BinaryTree<T>>(null);
    Ref<BinaryTree<T>> aw = new Ref<BinaryTree<T>>(A);
    Ref<BinaryTree<T>> bw = new Ref<BinaryTree<T>>(B);

    while (aw.value != null && bw.value != null) {
      appendNodeAndAdvance(sortedListW, tailW,
          aw.value.getData().compareTo(bw.value.getData()) < 0 ? aw : bw);
    }

    // Append the remaining of A.
    if (aw.value != null) {
      appendNode(sortedListW, tailW, aw);
    }
    // Append the remaining of B.
    if (bw.value != null) {
      appendNode(sortedListW, tailW, bw);
    }
    return sortedListW.value;
  }

  private static <T> void appendNodeAndAdvance(Ref<BinaryTree<T>> head,
      Ref<BinaryTree<T>> tail, Ref<BinaryTree<T>> n) {
    appendNode(head, tail, n);
    n.value = n.value.getRight(); // advance n.
  }

  private static <T> void appendNode(Ref<BinaryTree<T>> head,
      Ref<BinaryTree<T>> tail, Ref<BinaryTree<T>> n) {
    if (head.value != null) {
      tail.value.setRight(n.value);
      n.value.setLeft(tail.value);
    } else {
      head.value = n.value;
    }
    tail.value = n.value;
  }

  // @exclude

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
    BinaryTree<Integer> L = new BinaryTree<Integer>(3);
    L.setLeft(new BinaryTree<Integer>(2));
    L.getLeft().setLeft(new BinaryTree<Integer>(1));
    L.setRight(new BinaryTree<Integer>(5));
    L.getRight().setLeft(new BinaryTree<Integer>(4));
    L.getRight().setRight(new BinaryTree<Integer>(6));
    // 7
    // 2 8
    // 0
    BinaryTree<Integer> R = new BinaryTree<Integer>(7);
    R.setLeft(new BinaryTree<Integer>(2));
    R.getLeft().setLeft(new BinaryTree<Integer>(0));
    R.setRight(new BinaryTree<Integer>(8));

    BinaryTree<Integer> root = mergeBSTs(L, R);
    // should output 0 1 2 2 3 4 5 6 7 8
    printBstInOrder(root, Integer.MIN_VALUE);
  }
}
