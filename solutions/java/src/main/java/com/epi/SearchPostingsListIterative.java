package com.epi;

import java.util.Deque;
import java.util.LinkedList;

public class SearchPostingsListIterative {
  // @include
  public static void setJumpOrder(PostingListNode L) {
    Deque<PostingListNode> s = new LinkedList<>();
    int order = 0;
    s.addFirst(L);
    while (!s.isEmpty()) {
      PostingListNode curr = s.removeFirst();
      if (curr != null && curr.order == -1) {
        curr.order = order++;
        // Stack is last-in, first-out, and we want to process
        // the jump node first, so push next, then push jump.
        s.addFirst(curr.next);
        s.addFirst(curr.jump);
      }
    }
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
