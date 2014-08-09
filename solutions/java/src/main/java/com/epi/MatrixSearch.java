package com.epi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class MatrixSearch {
  // @include
  public static boolean matrixSearch(List<List<Integer>> A, int x) {
    int r = 0, c = A.get(0).size() - 1;
    while (r < A.size() && c >= 0) {
      if (A.get(r).get(c).equals(x)) {
        return true;
      } else if (A.get(r).get(c) < x) {
        ++r;
      } else { // A.get(r).get(c) > x.
        --c;
      }
    }
    return false;
  }
  // @exclude

  // O(n^2) solution for verifying answer.
  private static boolean bruteForceSearch(List<List<Integer>> A, int x) {
    for (List<Integer> aA : A) {
      for (Integer anAA : aA) {
        if (anAA.equals(x)) {
          return true;
        }
      }
    }
    return false;
  }

  public static void main(String[] args) {
    Random r = new Random();
    for (int times = 0; times < 10000; ++times) {
      int n, m;
      if (args.length == 2) {
        n = Integer.parseInt(args[0]);
        m = Integer.parseInt(args[1]);
      } else {
        n = r.nextInt(100) + 1;
        m = r.nextInt(100) + 1;
      }
      List<List<Integer>> A = new ArrayList<>(n);
      for (int i = 0; i < n; i++) {
        List<Integer> list = new ArrayList<>(m);
        for (int j = 0; j < m; j++) {
          list.add(0);
        }
        A.add(list);
      }
      A.get(0).set(0, r.nextInt(100));
      for (int i = 0; i < n; ++i) {
        for (int j = 0; j < m; ++j) {
          int up = (i == 0) ? 0 : A.get(i - 1).get(j);
          int left = (j == 0) ? 0 : A.get(i).get(j - 1);
          A.get(i).set(j, Math.max(up, left) + r.nextInt(20) + 1);
        }
      }
      int x = r.nextInt(1000);
      assert (bruteForceSearch(A, x) == matrixSearch(A, x));
    }
  }
}
