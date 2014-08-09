package com.epi;

public class CyclicRightShift {
  // @include
  public static NodeT<Integer> cyclicallyRightShiftList(NodeT<Integer> L,
                                                        int k) {
    if (L == null || k == 0) {
      return L;
    }

    // Computes n, the length of L.
    NodeT<Integer> tail = L;
    int n = 1;
    while (tail.next != null) {
      ++n;
      tail = tail.next;
    }

    tail.next = L; // Makes a cycle by connecting the tail to the head.
    int steps = n - k % n;
    NodeT<Integer> prev = tail;
    while (steps-- > 0) {
      prev = prev.next;
    }
    L = prev.next;
    prev.next = null;
    return L;
  }
  // @exclude

  public static void main(String[] args) {
    NodeT<Integer> L;
    L = new NodeT<>(1, new NodeT<>(2, new NodeT<>(3, null)));
    NodeT<Integer> result = cyclicallyRightShiftList(L, 2);
    assert(result.data.equals(2) && result.next.data.equals(3) &&
           result.next.next.data.equals(1) && result.next.next.next == null);
    while (result != null) {
      System.out.println(result.data);
      result = result.next;
    }
  }
}
