package com.epi;

import java.util.LinkedList;
import java.util.Random;

public class StackSorting {
  // @include
  public static void sort(LinkedList<Integer> S) {
    if (!S.isEmpty()) {
      Integer val = S.pop();
      sort(S);
      insert(val, S);
    }
  }

  private static void insert(Integer val, LinkedList<Integer> S) {
    if (S.isEmpty() || S.peek().compareTo(val) <= 0) {
      S.push(val);
    } else {
      Integer f = S.pop();
      insert(val, S);
      S.push(f);
    }
  }
  // @exclude

  private static void simpleTest() {
    LinkedList<Integer> S = new LinkedList<>();
    S.push(1);
    sort(S);
    assert(S.peek() == 1);
    S.push(0);
    sort(S);
    assert(S.peek() == 1);
    S.pop();
    assert(S.peek() == 0);
    S.pop();
    assert(S.isEmpty());
    S.push(-1);
    S.push(1);
    S.push(0);
    sort(S);
    assert(S.peek() == 1);
    S.pop();
    assert(S.peek() == 0);
    S.pop();
    assert(S.peek() == -1);
    S.pop();
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

      LinkedList<Integer> S = new LinkedList<>();
      for (int i = 0; i < n; i++) {
        S.push(r.nextInt(1000000));
      }
      sort(S);
      int pre = Integer.MAX_VALUE;
      while (!S.isEmpty()) {
        assert(pre >= S.peek());
        System.out.println(S.peek());
        pre = S.pop();
      }
    }
  }
}
