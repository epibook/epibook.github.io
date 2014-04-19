package com.epi;

public class AddTwoNumberList {
  // @include
  public static NodeT<Integer> addTwoNumbers(NodeT<Integer> L1,
      NodeT<Integer> L2) {
    final NodeT<Integer> S = L1;
    while (L2 != null) {
      L1.data += L2.data;
      handleCarry(L1);
      if (L1.next == null) {
        L1.next = L2.next;
        return S;
      }
      L1 = L1.next;
      L2 = L2.next;
    }

    // handleCarry returns boolean indicating if we can short circuit.
    while (L1 != null && handleCarry(L1)) {
      L1 = L1.next;
    }
    return S;
  }

  // Return true if L has a carry to the next digit; otherwise, return false.
  private static boolean handleCarry(NodeT<Integer> L) {
    if (L.data < 10) {
      return false;
    }

    L.data -= 10;
    if (L.next == null) {
      L.next = new NodeT<Integer>(0, null);
    }
    ++L.next.data;
    return true;
  }

  // @exclude

  public static void main(String[] args) {
    NodeT<Integer> L;
    L = new NodeT<Integer>(2,
        new NodeT<Integer>(4, new NodeT<Integer>(3, null)));
    NodeT<Integer> R;
    R = new NodeT<Integer>(5,
        new NodeT<Integer>(6, new NodeT<Integer>(7, null)));
    NodeT<Integer> S = addTwoNumbers(L, R);
    assert (S.data.equals(7) && S.next.data.equals(0)
        && S.next.next.data.equals(1) && S.next.next.next.data.equals(1));
    L = new NodeT<Integer>(9, new NodeT<Integer>(9, null));
    R = new NodeT<Integer>(9, null);
    S = addTwoNumbers(L, R);
    assert (S.data.equals(8) && S.next.data.equals(0) && S.next.next.data
        .equals(1));
  }
}
