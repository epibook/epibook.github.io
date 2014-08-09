package com.epi;

public class AddTwoNumberList {
  // @include
  public static NodeT<Integer> addTwoNumbers(NodeT<Integer> L1,
                                             NodeT<Integer> L2) {
    NodeT<Integer> dummyHead = new NodeT<>(0, null);
    NodeT<Integer> digit = dummyHead;
    int sum = 0;
    while (L1 != null || L2 != null) {
      if (L1 != null) {
        sum += L1.data;
        L1 = L1.next;
      }
      if (L2 != null) {
        sum += L2.data;
        L2 = L2.next;
      }
      digit.next = new NodeT<>(sum % 10, null);
      sum /= 10;
      digit = digit.next;
    }
    if (sum > 0) {
      digit.next = new NodeT<>(sum, null);
    }
    return dummyHead.next;
  }
  // @exclude

  public static void main(String[] args) {
    NodeT<Integer> L;
    L = new NodeT<>(2,
        new NodeT<>(4, new NodeT<>(3, null)));
    NodeT<Integer> R;
    R = new NodeT<>(5,
        new NodeT<>(6, new NodeT<>(7, null)));
    NodeT<Integer> S = addTwoNumbers(L, R);
    assert (S.data.equals(7) && S.next.data.equals(0)
            && S.next.next.data.equals(1) && S.next.next.next.data.equals(1));
    L = new NodeT<>(9, new NodeT<>(9, null));
    R = new NodeT<>(9, null);
    S = addTwoNumbers(L, R);
    assert (S.data.equals(8) && S.next.data.equals(0)
            && S.next.next.data.equals(1));
  }
}
