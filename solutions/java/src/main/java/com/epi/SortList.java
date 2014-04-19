package com.epi;

import java.util.Random;

import com.epi.utils.Ref;

public class SortList {
  private static <T> void appendNode(Ref<NodeT<T>> head, Ref<NodeT<T>> tail,
      Ref<NodeT<T>> n) {
    if (head.value != null) {
      tail.value.next = n.value;
    } else {
      head.value = n.value;
    }
    tail.value = n.value; // reset tail to the last node.
  }

  private static <T> void appendNodeAndAdvance(Ref<NodeT<T>> head,
      Ref<NodeT<T>> tail, Ref<NodeT<T>> n) {
    appendNode(head, tail, n);
    n.value = n.value.next; // advance n.
  }

  private static <T extends Comparable<T>> NodeT<T> mergeSortedLinkedLists(
      NodeT<T> F, NodeT<T> L) {
    Ref<NodeT<T>> sortedHead = new Ref<NodeT<T>>(null);
    Ref<NodeT<T>> tail = new Ref<NodeT<T>>(null);
    Ref<NodeT<T>> rf = new Ref<NodeT<T>>(F);
    Ref<NodeT<T>> rl = new Ref<NodeT<T>>(L);

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

  // @include
  public static NodeT<Integer> sortList(NodeT<Integer> L) {
    // Base case. L has 0 or 1 node.
    if (L == null || L.next == null) {
      return L;
    }

    // Finds the middle point of L.
    NodeT<Integer> slow = L, fast = L, preSlow = null;
    while (fast != null) {
      fast = fast.next;
      if (fast != null) {
        preSlow = slow;
        fast = fast.next;
        slow = slow.next;
      }
    }

    preSlow.next = null; // splits the list into two lists.
    fast = sortList(L);
    slow = sortList(slow);
    return mergeSortedLinkedLists(fast, slow);
  }

  // @exclude

  public static void main(String[] args) {
    Random r = new Random();
    for (int times = 0; times < 10000; ++times) {
      NodeT<Integer> L = null;
      int n;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = r.nextInt(100);
      }
      for (int i = n; i > 0; --i) {
        NodeT<Integer> temp = new NodeT<Integer>(r.nextInt(100), null);
        temp.next = L;
        L = temp;
      }

      NodeT<Integer> sortedHead = sortList(L);
      int count = 0;
      int pre = Integer.MIN_VALUE;
      while (sortedHead != null) {
        assert (pre <= sortedHead.data);
        pre = sortedHead.data;
        sortedHead = sortedHead.next;
        ++count;
      }
      assert (count == n);
    }
  }
}
