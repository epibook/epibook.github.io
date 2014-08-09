package com.epi;

public class ListPivoting {
  // @include
  public static NodeT<Integer> listPivoting(NodeT<Integer> L, int x) {
    NodeT<Integer> now = L;
    NodeT<Integer> lessHead = new NodeT<>(0, null);
    NodeT<Integer> equalHead = new NodeT<>(0, null);
    NodeT<Integer> largerHead = new NodeT<>(0, null);
    NodeT<Integer> lessTail = lessHead;
    NodeT<Integer> equalTail = equalHead;
    NodeT<Integer> largerTail = largerHead;
    while (now != null) {
      if (now.data < x) {
        lessTail.next = now;
        lessTail = now;
      } else if (now.data == x) {
        equalTail.next = now;
        equalTail = now;
      } else { // now->data > x.
        largerTail.next = now;
        largerTail = now;
      }
      now = now.next;
    }

    lessTail.next = equalTail.next = largerTail.next = null;
    if (largerHead.next != null) {
      equalTail.next = largerHead.next;
    }
    if (equalHead.next != null) {
      lessTail.next = equalHead.next;
    }
    return lessHead.next;
  }
  // @exclude

  public static void main(String[] args) {
    NodeT<Integer> L;
    L = new NodeT<>(1, new NodeT<>(4, new NodeT<>(3,
        new NodeT<>(2, new NodeT<>(5, null)))));
    int x = 4;
    NodeT<Integer> result = listPivoting(L, x);
    boolean beforeX = true;
    while (result != null) {
      if (result.data >= x) {
        beforeX = false;
      }
      if (beforeX) {
        assert (result.data < x);
      } else {
        assert (result.data >= x);
      }
      System.out.println(result.data);
      result = result.next;
    }
  }
}
