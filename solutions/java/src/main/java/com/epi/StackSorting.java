package com.epi;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;

public class StackSorting {
  // @include
  public static void sort(Deque<Integer> S) {
    if (!S.isEmpty()) {
      Integer val = S.removeFirst();
      sort(S);
      insert(val, S);
    }
  }

  private static void insert(Integer val, Deque<Integer> S) {
    if (S.isEmpty() || Integer.compare(S.peekFirst(), val) <= 0) {
      S.addFirst(val);
    } else {
      Integer f = S.removeFirst();
      insert(val, S);
      S.addFirst(f);
    }
  }
  // @exclude

  private static void simpleTest() {
    Deque<Integer> S = new LinkedList<>();
    S.addFirst(1);
    sort(S);
    assert(S.peekFirst() == 1);
    S.addFirst(0);
    sort(S);
    assert(S.peekFirst() == 1);
    S.removeFirst();
    assert(S.peekFirst() == 0);
    S.removeFirst();
    assert(S.isEmpty());
    S.addFirst(-1);
    S.addFirst(1);
    S.addFirst(0);
    sort(S);
    assert(S.peekFirst() == 1);
    S.removeFirst();
    assert(S.peekFirst() == 0);
    S.removeFirst();
    assert(S.peekFirst() == -1);
    S.removeFirst();
    assert(S.isEmpty());
  }

  public static void main(String[] args) {
    simpleTest();
    Random r = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = r.nextInt(10000) + 1;
      }

      Deque<Integer> S = new LinkedList<>();
      for (int i = 0; i < n; i++) {
        S.addFirst(r.nextInt(1000000));
      }
      sort(S);
      int pre = Integer.MAX_VALUE;
      while (!S.isEmpty()) {
        assert(pre >= S.peekFirst());
        System.out.println(S.peekFirst());
        pre = S.removeFirst();
      }
    }
  }
}
