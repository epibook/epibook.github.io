package com.epi;

import java.util.LinkedList;
import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class StackSorting {
  // @include
  public static void sort(LinkedList<Integer> S) {
    if (!S.isEmpty()) {
      Integer e = S.pop();
      sort(S);
      insert(S, e);
    }
  }

  private static void insert(LinkedList<Integer> S, Integer e) {
    if (S.isEmpty() || S.peek().compareTo(e) <= 0) {
      S.push(e);
    } else {
      Integer f = S.pop();
      insert(S, e);
      S.push(f);
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
