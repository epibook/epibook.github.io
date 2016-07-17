package com.epi;

public class SearchPostingsListRecursive {
  // @include
  public static void setJumpOrder(PostingListNode L) {
    setJumpOrderHelper(L, 0);
  }

  private static int setJumpOrderHelper(PostingListNode L, int order) {
    if (L != null && L.order == -1) {
      L.order = order++;
      order = setJumpOrderHelper(L.jump, order);
      order = setJumpOrderHelper(L.next, order);
    }
    return order;
  }
  // @exclude

  public static void main(String[] args) {
    PostingListNode L = null, curr = null;
    // Build a linked list L->1->2->3->4->5->nullptr.
    for (int i = 0; i < 5; ++i) {
      PostingListNode temp = new PostingListNode(-1, null, null);
      if (curr != null) {
        curr.next = temp;
        curr = temp;
      } else {
        curr = L = temp;
      }
    }

    L.jump = null; // no jump from 1
    L.next.jump = L.next.next.next; // 2's jump points to
    // 4
    L.next.next.jump = L; // 3's jump points to 1
    L.next.next.next.jump = null; // no jump from 4
    L.next.next.next.next.jump = L.next.next.next.next; // 5's jump points
    // to 5
    PostingListNode temp = L;
    setJumpOrder(L);
    // output the jump-first order, it should be 0, 1, 4, 2, 3
    assert(temp.order == 0);
    temp = temp.next;
    assert(temp.order == 1);
    temp = temp.next;
    assert(temp.order == 4);
    temp = temp.next;
    assert(temp.order == 2);
    temp = temp.next;
    assert(temp.order == 3);
  }
}
