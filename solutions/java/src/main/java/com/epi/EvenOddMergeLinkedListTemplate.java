// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
// @author Andrey Pavlov

package com.epi;

import java.util.Random;

class EvenOddMergeLinkedListTemplate {
  // @include
  public static <T> Node<T> evenOddMerge(Node<T> L) {
    Node<T> odd = (L != null) ? L.next : null;
    Node<T> oddCurr = odd;
    Node<T> preEvenCurr = null, evenCurr = L;

    while (evenCurr != null && oddCurr != null) {
      evenCurr.next = oddCurr.next;
      preEvenCurr = evenCurr;
      evenCurr = evenCurr.next;
      if (evenCurr != null) {
        oddCurr.next = evenCurr.next;
        oddCurr = oddCurr.next;
      }
    }

    // Odd number of nodes.
    if (evenCurr != null) {
      preEvenCurr = evenCurr;
    }
    // Prevents empty list.
    if (preEvenCurr != null) {
      preEvenCurr.next = odd;
    }
    return L;
  }

  // @exclude

  public static void main(String[] args) {
    // input a list in reverse order.
    Random gen = new Random();
    Node<Integer> head = null;
    int n = 0;
    if (args.length > 1) {
      for (String element : args) {
        Node<Integer> curr = new Node<Integer>(Integer.parseInt(element), null);
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
        Node<Integer> curr = new Node<Integer>(i, null);
        curr.next = head;
        head = curr;
      }
    }
    Node<Integer> answer = evenOddMerge(head);
    int x = 0;
    while (answer != null) {
      if (args.length <= 1) {
        assert (x == answer.data);
        x += 2;
        if (x >= n) {
          x = 1;
        }
      }
      System.out.println(answer.data);
      answer = answer.next;
    }
  }
}
