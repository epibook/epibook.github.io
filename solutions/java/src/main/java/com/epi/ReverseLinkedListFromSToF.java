package com.epi;

public class ReverseLinkedListFromSToF {
  // @include
  public static ListNode<Integer> reverseSublist(ListNode<Integer> L, int start,
                                                 int finish) {
    if (start == finish) { // No need to reverse since start == finish.
      return L;
    }

    ListNode<Integer> dummyHead = new ListNode<>(0, L);
    ListNode<Integer> sublistHead = dummyHead;
    int k = 1;
    while (k++ < start) {
      sublistHead = sublistHead.next;
    }

    // Reverse sublist.
    ListNode<Integer> sublistIter = sublistHead.next;
    while (start++ < finish) {
      ListNode<Integer> temp = sublistIter.next;
      sublistIter.next = temp.next;
      temp.next = sublistHead.next;
      sublistHead.next = temp;
    }
    return dummyHead.next;
  }
  // @exclude

  private static void simpleTest() {
    ListNode<Integer> L = null;
    ListNode<Integer> result = reverseSublist(L, 0, 0);
    assert(result == null);

    L = new ListNode<>(1, null);
    result = reverseSublist(L, 0, 0);
    assert(result == L);

    L = new ListNode<>(1, new ListNode<>(2, new ListNode<>(3, null)));
    result = reverseSublist(L, 0, 1);
    assert(result.data == 2 && result.next.data == 1
           && result.next.next.data == 3);

    L = new ListNode<>(1, new ListNode<>(2, new ListNode<>(3, null)));
    result = reverseSublist(L, 0, 2);
    assert(result.data == 3 && result.next.data == 2
           && result.next.next.data == 1);
  }

  public static void main(String[] args) {
    simpleTest();
    ListNode<Integer> L;
    L = new ListNode<>(1, new ListNode<>(2, new ListNode<>(3, null)));
    ListNode<Integer> result = reverseSublist(L, 3, 3);
    assert(result.data.equals(1) && result.next.data.equals(2)
           && result.next.next.data.equals(3) && result.next.next.next == null);
    while (result != null) {
      System.out.println(result.data);
      result = result.next;
    }

    result = reverseSublist(L, 2, 3);
    assert(result.data.equals(1) && result.next.data.equals(3)
           && result.next.next.data.equals(2) && result.next.next.next == null);
    while (result != null) {
      System.out.println(result.data);
      result = result.next;
    }
  }
}
