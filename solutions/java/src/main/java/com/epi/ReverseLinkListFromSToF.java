package com.epi;

public class ReverseLinkListFromSToF {
  private static void reverseLinkedList(NodeT<Integer> sPtr) {
    if (sPtr.next != null) {
      reverseLinkedList(sPtr.next);
      sPtr.next.next = sPtr;
    }
  }

  // @include
  public static NodeT<Integer> reverseSublistSF(NodeT<Integer> L, int s, int f) {
    if (s == f) { // no need to reverse since s == f.
      return L;
    }

    NodeT<Integer> preSPtr = findKthPtr(L, s - 1), 
                   sPtr = findKthPtr(L, s), 
                   fPtr = findKthPtr( L, f), 
                   nextFPtr = findKthPtr(L, f + 1);
    // Breaks the links to form a list starting from sPtr to fPtr.
    if (preSPtr != null) {
      preSPtr.next = null;
    }
    fPtr.next = null;

    reverseLinkedList(sPtr); // reverses the sublist.
    sPtr.next = nextFPtr;

    if (preSPtr != null) {
      preSPtr.next = fPtr;
      return L;
    } else {
      return fPtr;
    }
  }

  private static NodeT<Integer> findKthPtr(NodeT<Integer> L, int k) {
    if (k == 0) {
      return null;
    }

    while (--k != 0) {
      L = L.next;
    }
    return L;
  }

  // @exclude

  public static void main(String[] args) {
    NodeT<Integer> L;
    L = new NodeT<Integer>(1,
        new NodeT<Integer>(2, new NodeT<Integer>(3, null)));
    NodeT<Integer> res = reverseSublistSF(L, 3, 3);
    assert (res.data.equals(1) && res.next.data.equals(2)
        && res.next.next.data.equals(3) && res.next.next.next == null);
    while (res != null) {
      System.out.println(res.data);
      res = res.next;
    }

    res = reverseSublistSF(L, 2, 3);
    assert (res.data.equals(1) && res.next.data.equals(3)
        && res.next.next.data.equals(2) && res.next.next.next == null);
    while (res != null) {
      System.out.println(res.data);
      res = res.next;
    }
  }
}
