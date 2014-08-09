// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
// @author Andrey Pavlov

package com.epi;

import java.util.Random;

class EvenOddMergeLinkedList {
  // @include
  public static Node<Integer> evenOddMerge(Node<Integer> L) {
    if (L == null) {
      return L;
    }

    Node<Integer> oddFirst = L.next;
    Node<Integer> oddCurr = oddFirst;
    Node<Integer> preEvenCurr = null, evenCurr = L;

    while (evenCurr != null && oddCurr != null) {
      evenCurr.next = oddCurr.next;
      preEvenCurr = evenCurr;
      evenCurr = evenCurr.next;
      if (evenCurr != null) {
        oddCurr.next = evenCurr.next;
        oddCurr = oddCurr.next;
      }
    }

    if (evenCurr != null) {
      evenCurr.next = oddFirst;
    } else {
      preEvenCurr.next = oddFirst;
    }
    return L;
  }
  // @exclude

  public static void main(String[] args) {
    Random gen = new Random();
    Node<Integer> head = null;
    int n = 0;
    if (args.length > 1) {
      for (String element : args) {
        Node<Integer> curr = new Node<>(Integer.parseInt(element), null);
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
        Node<Integer> curr = new Node<>(i, null);
        curr.next = head;
        head = curr;
      }
    }
    Node<Integer> answer = evenOddMerge(head);
    int x = 0, count = 0;
    while (answer != null) {
      ++count;
      assert (x == answer.data);
      x += 2;
      if (x >= n) {
        x = 1;
      }
      System.out.println(answer.data);
      answer = answer.next;
    }
    assert (count == n);
  }
}
