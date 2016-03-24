package com.epi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CloseSearch {
  // @include
  public static int closeSearch(List<Integer> A, int k) {
    int idx = 0;
    while (idx < A.size() && A.get(idx) != k) {
      idx += StrictMath.abs(A.get(idx) - k);
    }
    return idx < A.size() ? idx : -1; // -1 means no result.
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
      List<Integer> A = new ArrayList<>();
      A.add(r.nextInt(10));
      for (int i = 1; i < n; ++i) {
        int shift = r.nextInt(3) - 1;
        A.add(A.get(i - 1) + shift);
      }
      int k = r.nextInt(100);
      int ans = closeSearch(A, k);
      System.out.println(ans);
      if (ans != -1) {
        assert(A.get(ans) == k);
      } else {
        boolean found = false;
        for (Integer anA : A) {
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
