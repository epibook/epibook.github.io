package com.epi;

public class ReverseListInKGroup {
  private static void reverseLinkedList(NodeT<Integer> sPtr) {
    if (sPtr.next != null) {
      reverseLinkedList(sPtr.next);
      sPtr.next.next = sPtr;
    }
  }

  // @include
  public static NodeT<Integer> reverseK(NodeT<Integer> L, int k) {
    boolean firstTime = true;
    NodeT<Integer> beforePre = null, pre = L, beforePost = null, post = L;
    while (pre != null) {
      int i = k;
      while (i != 0) {
        beforePost = post;
        post = post.next;
        --i;
        if (post == null) {
          break;
        }
      }
      if (i != 0) {
        return L;
      }

      beforePost.next = null;
      reverseLinkedList(pre);
      if (beforePre != null) {
        beforePre.next = beforePost;
      }
      if (firstTime) {
        L = beforePost;
        firstTime = false;
      }
      beforePre = pre;
      pre.next = post;
      pre = post;
      beforePost = null;
    }
    return L;
  }

  // @exclude

  public static void main(String[] args) {
    NodeT<Integer> L;
    L = new NodeT<Integer>(1, new NodeT<Integer>(2, new NodeT<Integer>(3,
        new NodeT<Integer>(4, new NodeT<Integer>(5, null)))));
    int k;
    if (args.length == 1) {
      k = Integer.parseInt(args[0]);
    } else {
      k = 2;
    }
    NodeT<Integer> res = reverseK(L, k);
    assert (res.data.equals(2) && res.next.data.equals(1)
        && res.next.next.data.equals(4) && res.next.next.next.data.equals(3)
        && res.next.next.next.next.data.equals(5) && res.next.next.next.next.next == null);
    while (res != null) {
      System.out.println(res.data);
      res = res.next;
    }
  }
}
