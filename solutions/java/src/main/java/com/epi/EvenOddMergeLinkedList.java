// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.
// @author Andrey Pavlov

package com.epi;

import java.util.Random;

class EvenOddMergeLinkedList {
  // @include
  public static ListNode<Integer> evenOddMerge(ListNode<Integer> L) {
    if (L == null) {
      return L;
    }

    ListNode<Integer> evenListHead = L, evenListIter = evenListHead;
    ListNode<Integer> predecessorEvenListIter = null;
    ListNode<Integer> oddListHead = L.next, oddListIter = oddListHead;

    while (evenListIter != null && oddListIter != null) {
      evenListIter.next = oddListIter.next;
      predecessorEvenListIter = evenListIter;
      evenListIter = evenListIter.next;
      if (evenListIter != null) {
        oddListIter.next = evenListIter.next;
        oddListIter = oddListIter.next;
      }
    }

    if (evenListIter != null) {
      evenListIter.next = oddListHead;
    } else {
      predecessorEvenListIter.next = oddListHead;
    }
    return evenListHead;
  }
  // @exclude

  public static void main(String[] args) {
    Random gen = new Random();
    ListNode<Integer> head = null;
    int n = 0;
    if (args.length > 1) {
      for (String element : args) {
        ListNode<Integer> curr = new ListNode<>(Integer.parseInt(element), null);
        curr.next = head;
        head = curr;
      }
    } else {
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = gen.nextInt(1000) + 1;
      }
      for (int i = n - 1; i >= 0; --i) {
        ListNode<Integer> curr = new ListNode<>(i, null);
        curr.next = head;
        head = curr;
      }
    }
    ListNode<Integer> answer = evenOddMerge(head);
    int x = 0, count = 0;
    while (answer != null) {
      ++count;
      assert(x == answer.data);
      x += 2;
      if (x >= n) {
        x = 1;
      }
      System.out.println(answer.data);
      answer = answer.next;
    }
    assert(count == n);
  }
}
