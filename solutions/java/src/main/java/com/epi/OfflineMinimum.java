// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.
package com.epi;

import java.util.Arrays;
import java.util.Random;

import static com.epi.utils.Utils.iota;
import static com.epi.utils.Utils.shuffle;
import static java.lang.Math.max;
import static java.lang.Math.min;

public class OfflineMinimum {
  // @include
  public static int[] offlineMinimum(int[] A, int[] E) {
    int[] R = new int[A.length];
    Arrays.fill(R, E.length);
    int pre = 0;

    // Initialize the collection of subsets.
    for (int i = 0; i < E.length; ++i) {
      for (int j = pre; j <= E[i]; ++j) {
        R[A[j]] = i;
      }
      pre = E[i] + 1;
    }

    int[] ret = new int[E.length];
    Arrays.fill(ret, -1); // stores the answer
    int[] set = new int[E.length + 1]; // the disjoint-set
    iota(set, 0, set.length, 0); // initializes the disjoint-set
    for (int i = 0; i < A.length; ++i) {
      if (findSet(set, R[i]) != E.length && ret[findSet(set, R[i])] == -1) {
        ret[set[R[i]]] = i;
        unionSet(set, set[R[i]], set[R[i]] + 1);
      }
    }
    return ret;
  }

  private static int findSet(int[] set, int x) {
    if (set[x] != x) {
      set[x] = findSet(set, set[x]); // path compression.
    }
    return set[x];
  }

  private static void unionSet(int[] set, int x, int y) {
    int xRoot = findSet(set, x), yRoot = findSet(set, y);
    set[min(xRoot, yRoot)] = max(xRoot, yRoot);
  }
  // @exclude

  // O(nm) checking method
  static int[] checkAnswer(int[] A, int[] E) {
    boolean[] exist = new boolean[A.length];
    int[] ans = new int[E.length];

    for (int i = 0; i < E.length; ++i) {
      int minVal = Integer.MAX_VALUE;
      for (int j = 0; j <= E[i]; ++j) {
        if (A[j] < minVal && !exist[A[j]]) {
          minVal = min(A[j], minVal);
        }
      }
      exist[minVal] = true;
      ans[i] = minVal;
    }
    /*
     * System.out.print("ans2 = "); System.out.println(Arrays.toString(ans));
     */
    return ans;
  }

  public static void main(String[] args) {
    Random gen = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n, m;
      if (args.length == 1) {
        n = Integer.valueOf(args[0]);
        m = gen.nextInt(n) + 1;
      } else if (args.length == 2) {
        n = Integer.valueOf(args[0]);
        m = Integer.valueOf(args[1]);
      } else {
        n = gen.nextInt(1000) + 1;
        m = gen.nextInt(n) + 1;
      }
      System.out.println("n = " + n + ", m = " + m);
      int[] A = new int[n];
      iota(A, 0, A.length, 0);
      shuffle(A);

      /*
       * System.out.print("A = "); System.out.println(Arrays.toString(A));
       */
      int[] E = new int[m];
      for (int i = 0; i < m; ++i) {
        E[i] = gen.nextInt(n - i) + i;
      }
      Arrays.sort(E);
      /*
       * System.out.print("E = "); System.out.println(Arrays.toString(E));
       */
      int[] ans = offlineMinimum(A, E);
      /*
       * System.out.print("ans1 = "); System.out.println(Arrays.toString(ans));
       */
      int[] tmp = checkAnswer(A, E);
      assert(Arrays.equals(ans, tmp));
    }
  }
}
