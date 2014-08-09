package com.epi;

public class ReverseLinkListFromSToF {
  // @include
  public static NodeT<Integer> reverseSublistSF(NodeT<Integer> L, int s,
                                                int f) {
    if (s == f) { // No need to reverse since s == f.
      return L;
    }

    NodeT<Integer> dummyHead = new NodeT<>(0, L);
    NodeT<Integer> p = dummyHead;
    int k = 1;
    while (k++ < s) {
      p = p.next;
    }

    // Reverse sublist.
    NodeT<Integer> q = p.next;
    while (s++ < f) {
      NodeT<Integer> temp = q.next;
      q.next = temp.next;
      temp.next = p.next;
      p.next = temp;
    }
    return dummyHead.next;
  }
  // @exclude

  public static void main(String[] args) {
    NodeT<Integer> L;
    L = new NodeT<>(1, new NodeT<>(2, new NodeT<>(3, null)));
    NodeT<Integer> result = reverseSublistSF(L, 3, 3);
    assert(result.data.equals(1) && result.next.data.equals(2) &&
           result.next.next.data.equals(3) && result.next.next.next == null);
    while (result != null) {
      System.out.println(result.data);
      result = result.next;
    }

    result = reverseSublistSF(L, 2, 3);
    assert(result.data.equals(1) && result.next.data.equals(3) &&
           result.next.next.data.equals(2) && result.next.next.next == null);
    while (result != null) {
      System.out.println(result.data);
      result = result.next;
    }
  }
}
