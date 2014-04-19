package com.epi;

import java.util.LinkedList;
import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class StackSorting {
  // @include
  private static <T extends Comparable<T>> void insert(LinkedList<T> S, T e) {
    if (S.isEmpty() || S.peek().compareTo(e) <= 0) {
      S.push(e);
    } else {
      T f = S.pop();
      insert(S, e);
      S.push(f);
    }
  }

  public static <T extends Comparable<T>> void sort(LinkedList<T> S) {
    if (!S.isEmpty()) {
      T e = S.pop();
      sort(S);
      insert(S, e);
    }
  }

  // @exclude

  public static void main(String[] args) {
    Random r = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = r.nextInt(10000) + 1;
      }

      LinkedList<Integer> S = new LinkedList<Integer>();
      for (int i = 0; i < n; i++) {
        S.push(r.nextInt(1000000));
      }
      sort(S);
      int pre = Integer.MAX_VALUE;
      while (!S.isEmpty()) {
        assert (pre >= S.peek());
        System.out.println(S.peek());
        pre = S.pop();
      }
    }
  }
}
