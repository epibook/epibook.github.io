package com.epi;

public class RemoveDuplicatesSortedList {
  // @include
  public static NodeT<Integer> removeDuplicates(NodeT<Integer> L) {
    NodeT<Integer> iter = L;
    while (iter != null) {
      NodeT<Integer> runner = iter.next;
      while (runner != null && runner.data == iter.data) {
        runner = runner.next;
      }
      iter.next = runner;
      iter = runner;
    }
    return L;
  }
  // @exclude

  public static void main(String[] args) {
    NodeT<Integer> L;
    L = new NodeT<>(2, new NodeT<>(2, new NodeT<>(2,
        new NodeT<>(2, new NodeT<>(2, null)))));
    NodeT<Integer> pre = null;
    NodeT<Integer> result = removeDuplicates(L);
    int count = 0;
    while (result != null) {
      ++count;
      if (pre != null) {
        assert (!pre.data.equals(result.data));
      }
      System.out.println(result.data);
      pre = result;
      result = result.next;
    }
    assert (count == 1);
  }
}
