// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
// @author Ivan Sharov

package com.epi;

import java.util.Random;

import com.epi.utils.Ref;

public class MergeSortedListsTemplate {
//@include
  public static <T> void appendNode(Ref<Node<T>> head, Ref<Node<T>> tail,
      Ref<Node<T>> n) {
    if (head.value != null) {
      tail.value.next = n.value;
    } else {
      head.value = n.value;
    }
    tail.value = n.value; // reset tail to the last node.
  }

  public static <T> void appendNodeAndAdvance(Ref<Node<T>> head,
      Ref<Node<T>> tail, Ref<Node<T>> n) {
    appendNode(head, tail, n);
    n.value = n.value.next; // advance n.
  }

  public static <T extends Comparable<T>> Node<T> mergeSortedLinkedLists(
      Node<T> F, Node<T> L) {
    Ref<Node<T>> sortedHead = new Ref<Node<T>>(null);
    Ref<Node<T>> tail = new Ref<Node<T>>(null);
    Ref<Node<T>> rf = new Ref<Node<T>>(F);
    Ref<Node<T>> rl = new Ref<Node<T>>(L);

    while (rf.value != null && rl.value != null) {
      appendNodeAndAdvance(sortedHead, tail,
          rf.value.data.compareTo(rl.value.data) < 0 ? rf : rl);
    }

    // Append the remaining nodes of F.
    if (rf.value != null) {
      appendNode(sortedHead, tail, rf);
    }

    // Append the remaining nodes of L.
    if (rl.value != null) {
      appendNode(sortedHead, tail, rl);
    }

    return sortedHead.value;
  }
  // @exclude

  public static void main(String[] args) {
    Random rnd = new Random();
    for (int times = 0; times < 10000; ++times) {
      Node<Integer> F = null;
      Node<Integer> L = null;
      int n, m;
      if (args.length == 2) {
        n = Integer.parseInt(args[0]);
        m = Integer.parseInt(args[1]);
      } else if (args.length == 1) {
        n = Integer.parseInt(args[0]);
        m = Integer.parseInt(args[0]);
      } else {
        n = rnd.nextInt(100);
        m = rnd.nextInt(100);
      }
      for (int i = n; i > 0; --i) {
        Node<Integer> temp = new Node<Integer>(i, null);
        temp.next = F;
        F = temp;
      }
      for (int j = m; j > 0; --j) {
        Node<Integer> temp = new Node<Integer>(j, null);
        temp.next = L;
        L = temp;
      }

      Node<Integer> sortedHead = mergeSortedLinkedLists(F, L);
      int count = 0;
      int pre = Integer.MIN_VALUE;
      while (sortedHead != null) {
        assert (pre <= sortedHead.data);
        pre = sortedHead.data;
        sortedHead = sortedHead.next;
        ++count;
      }
      // Make sure the merged list have the same number of nodes as F and L.
      assert (count == n + m);
    }
  }
}
