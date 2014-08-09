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
    NodeT<Integer> dummyHead = new NodeT<>(0, L);
    NodeT<Integer> beforePre = dummyHead, pre = dummyHead.next,
                   beforePost = dummyHead, post = dummyHead.next;
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
        return dummyHead.next;
      }

      beforePost.next = null;
      reverseLinkedList(pre);
      beforePre.next = beforePost;
      beforePre = pre;
      pre.next = post;
      pre = post;
      beforePost = null;
    }
    return dummyHead.next;
  }
  // @exclude

  public static void main(String[] args) {
    NodeT<Integer> L;
    L = new NodeT<>(1, new NodeT<>(2, new NodeT<>(3,
        new NodeT<>(4, new NodeT<>(5, null)))));
    int k;
    if (args.length == 1) {
      k = Integer.parseInt(args[0]);
    } else {
      k = 2;
    }
    NodeT<Integer> result = reverseK(L, k);
    assert (result.data.equals(2) && result.next.data.equals(1)
        && result.next.next.data.equals(4) && result.next.next.next.data.equals(3)
        && result.next.next.next.next.data.equals(5) && result.next.next.next.next.next == null);
    while (result != null) {
      System.out.println(result.data);
      result = result.next;
    }
  }
}
