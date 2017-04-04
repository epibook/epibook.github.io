package com.epi;

public class AddTwoNumberList {
  // @include
  public static ListNode<Integer> addTwoNumbers(ListNode<Integer> L1,
                                                ListNode<Integer> L2) {
    ListNode<Integer> dummyHead = new ListNode<>(0, null);
    ListNode<Integer> placeIter = dummyHead;
    int carry = 0;
    while (L1 != null || L2 != null) {
      int sum = carry;
      if (L1 != null) {
        sum += L1.data;
        L1 = L1.next;
      }
      if (L2 != null) {
        sum += L2.data;
        L2 = L2.next;
      }
      placeIter.next = new ListNode<>(sum % 10, null);
      carry = sum / 10;
      placeIter = placeIter.next;
    }
    // carry cannot exceed 1, so we never need to add more than one node.
    if (carry > 0) {
      placeIter.next = new ListNode<>(carry, null);
    }
    return dummyHead.next;
  }
  // @exclude

  private static void simpleTest() {
    ListNode<Integer> L;
    L = new ListNode<>(2, new ListNode<>(4, new ListNode<>(3, null)));
    ListNode<Integer> R;
    R = new ListNode<>(0, null);
    ListNode<Integer> S = addTwoNumbers(L, R);
    assert(S.data.equals(2) && S.next.data.equals(4)
           && S.next.next.data.equals(3));

    L = new ListNode<>(3, new ListNode<>(4, new ListNode<>(2, null)));
    R = new ListNode<>(7, new ListNode<>(5, new ListNode<>(7, null)));
    S = addTwoNumbers(L, R);
    assert(S.data.equals(0) && S.next.data.equals(0)
           && S.next.next.data.equals(0) && S.next.next.next.data.equals(1));

    L = new ListNode<>(1, null);
    R = new ListNode<>(1, null);
    S = addTwoNumbers(L, R);
    assert(S.data.equals(2) && S.next == null);

    L = new ListNode<>(5, null);
    R = new ListNode<>(5, null);
    S = addTwoNumbers(L, R);
    assert(S.data.equals(0) && S.next.data == 1 && S.next.next == null);
  }

  public static void main(String[] args) {
    simpleTest();
    ListNode<Integer> L;
    L = new ListNode<>(2, new ListNode<>(4, new ListNode<>(3, null)));
    ListNode<Integer> R;
    R = new ListNode<>(5, new ListNode<>(6, new ListNode<>(7, null)));
    ListNode<Integer> S = addTwoNumbers(L, R);
    assert(S.data.equals(7) && S.next.data.equals(0)
           && S.next.next.data.equals(1) && S.next.next.next.data.equals(1));
    L = new ListNode<>(9, new ListNode<>(9, null));
    R = new ListNode<>(9, null);
    S = addTwoNumbers(L, R);
    assert(S.data.equals(8) && S.next.data.equals(0)
           && S.next.next.data.equals(1));
  }
}
