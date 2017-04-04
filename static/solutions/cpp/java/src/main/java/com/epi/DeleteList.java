package com.epi;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class DeleteList {
  // @include
  // Delete the node immediately following aNode. Assumes aNode is not a tail.
  public static void deleteList(ListNode<Integer> aNode) {
    aNode.next = aNode.next.next;
  }
  // @exclude

  private static void checkAnswer(ListNode<Integer> L, List<Integer> vals) {
    for (int i = 0; i < vals.size(); ++i) {
      assert(L.data == vals.get(i));
      L = L.next;
    }
    assert(L == null);
  }

  public static void main(String[] args) {
    ListNode<Integer> L;
    L = new ListNode<>(2, new ListNode<>(4, new ListNode<>(3, null)));
    deleteList(L);
    checkAnswer(L, Arrays.asList(2, 3));
    deleteList(L);
    checkAnswer(L, Arrays.asList(2));
  }
}
