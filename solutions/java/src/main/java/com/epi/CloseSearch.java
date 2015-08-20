package com.epi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class CloseSearch {
  // @include
  public static int closeSearch(List<Integer> a, int k) {
    int idx = 0;
    while (idx < a.size() && a.get(idx) != k) {
      idx += StrictMath.abs(a.get(idx) - k);
    }
    return idx < a.size() ? idx : -1; // -1 means no result.
  }
  // @exclude

  public static void main(String[] args) {
    Random r = new Random();
    for (int times = 0; times < 10000; ++times) {
      int n;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = r.nextInt(10000) + 1;
      }
      List<Integer> a = new ArrayList<>();
      a.add(r.nextInt(10));
      for (int i = 1; i < n; ++i) {
        int shift = r.nextInt(3) - 1;
        a.add(a.get(i - 1) + shift);
      }
      int k = r.nextInt(100);
      int ans = closeSearch(a, k);
      System.out.println(ans);
      if (ans != -1) {
        assert(a.get(ans) == k);
      } else {
        boolean found = false;
        for (Integer anA : a) {
          if (anA == k) {
            found = true;
            break;
          }
        }
        assert(!found);
      }
    }
  }
}
