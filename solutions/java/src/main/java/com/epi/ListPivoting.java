package com.epi;

public class ListPivoting {
  private static class Ref<T> {
    T value;

    Ref(T v) {
      value = v;
    }
  }

  // @include
  public static NodeT<Integer> listPivoting(NodeT<Integer> L, int x) {
    NodeT<Integer> now = L;
    NodeT<Integer> lessHead = null;
    NodeT<Integer> lessTail = null;
    NodeT<Integer> equalHead = null;
    NodeT<Integer> equalTail = null;
    NodeT<Integer> largerHead = null;
    NodeT<Integer> largerTail = null;
    while (now != null) {
      if (now.data < x) {
        appendNode(new Ref<NodeT<Integer>>(lessHead), new Ref<NodeT<Integer>>(
            lessTail), now);
      } else if (now.data == x) {
        appendNode(new Ref<NodeT<Integer>>(equalHead), new Ref<NodeT<Integer>>(
            equalTail), now);
      } else { // now->data > x.
        appendNode(new Ref<NodeT<Integer>>(largerHead),
            new Ref<NodeT<Integer>>(largerTail), now);
      }
      now = now.next;
    }

    if (lessTail != null) {
      lessTail.next = null;
    }
    if (equalTail != null) {
      equalTail.next = null;
    }
    if (largerTail != null) {
      largerTail.next = null;
    }
    if (largerHead != null) {
      appendNode(new Ref<NodeT<Integer>>(equalHead), new Ref<NodeT<Integer>>(
          equalTail), largerTail);
    }
    if (equalHead != null) {
      appendNode(new Ref<NodeT<Integer>>(lessHead), new Ref<NodeT<Integer>>(
          lessTail), equalHead);
    }
    return lessHead;
  }

  // @exclude

  private static void appendNode(Ref<NodeT<Integer>> head,
      Ref<NodeT<Integer>> tail, NodeT<Integer> n) {
    if (head.value != null) {
      tail.value.next = n;
    } else {
      head.value = n;
    }
    tail.value = n; // reset tail to the last node.
  }

  public static void main(String[] args) {
    NodeT<Integer> L;
    L = new NodeT<Integer>(1, new NodeT<Integer>(4, new NodeT<Integer>(3,
        new NodeT<Integer>(2, new NodeT<Integer>(5, null)))));
    int x = 4;
    NodeT<Integer> res = listPivoting(L, x);
    boolean beforeX = true;
    while (res != null) {
      if (res.data >= x) {
        beforeX = false;
      }
      if (beforeX) {
        assert (res.data < x);
      } else {
        assert (res.data >= x);
      }
      System.out.println(res.data);
      res = res.next;
    }
  }
}
