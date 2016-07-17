package com.epi;

public class ReverseListInKGroup {
  // @include
  public static ListNode<Integer> reverseK(ListNode<Integer> L, int k) {
    ListNode<Integer> dummyHead = new ListNode<>(0, L);
    ListNode<Integer> sublistPredecessor = dummyHead;
    ListNode<Integer> sublistHead = dummyHead.next;
    ListNode<Integer> sublistSuccessor = dummyHead;
    ListNode<Integer> sublistTail = dummyHead.next;
    while (sublistHead != null) {
      int numRemaining = k;
      while (numRemaining > 0) {
        sublistSuccessor = sublistTail;
        sublistTail = sublistTail.next;
        --numRemaining;
        if (sublistTail == null) {
          break;
        }
      }
      if (numRemaining > 0) {
        return dummyHead.next;
      }

      sublistSuccessor.next = null;
      ReverseLinkedListIterative.reverseLinkedList(sublistHead);

      // Splice the reversed sublist.
      sublistPredecessor.next = sublistSuccessor;
      // Go on to the head of next sublist.
      sublistPredecessor = sublistHead;
      sublistHead.next = sublistTail;
      sublistHead = sublistTail;
      sublistSuccessor = null;
    }
    return dummyHead.next;
  }
  // @exclude

  public static void main(String[] args) {
    ListNode<Integer> L;
    L = new ListNode<>(
        1,
        new ListNode<>(
            2, new ListNode<>(3, new ListNode<>(4, new ListNode<>(5, null)))));
    int k;
    if (args.length == 1) {
      k = Integer.parseInt(args[0]);
    } else {
      k = 2;
    }
    ListNode<Integer> result = reverseK(L, k);
    assert(result.data.equals(2) && result.next.data.equals(1)
           && result.next.next.data.equals(4)
           && result.next.next.next.data.equals(3)
           && result.next.next.next.next.data.equals(5)
           && result.next.next.next.next.next == null);
    while (result != null) {
      System.out.println(result.data);
      result = result.next;
    }
  }
}
