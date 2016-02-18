// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.
package com.epi;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class OfflineMinimum {
  // @include
  public static List<Integer> offlineMinimum(List<Integer> A, List<Integer> E) {
    List<Integer> R = new ArrayList<>(Collections.nCopies(A.size(), E.size()));
    int pre = 0;

    // Initialize the collection of subsets.
    for (int i = 0; i < E.size(); ++i) {
      for (int j = pre; j <= E.get(i); ++j) {
        R.set(A.get(j), i);
      }
      pre = E.get(i) + 1;
    }

    List<Integer> ret = new ArrayList<>(Collections.nCopies(E.size(), -1));
    List<Integer> set = new ArrayList<>(E.size() + 1); // the disjoint-set
    // initializes the disjoint-set
    for (int i = 0; i < E.size() + 1; ++i) {
      set.add(i);
    }
    for (int i = 0; i < A.size(); ++i) {
      if (findSet(set, R.get(i)) != E.size()
          && ret.get(findSet(set, R.get(i))) == -1) {
        ret.set(set.get(R.get(i)), i);
        unionSet(set, set.get(R.get(i)), set.get(R.get(i)) + 1);
      }
    }
    return ret;
  }

  private static int findSet(List<Integer> set, int x) {
    if (set.get(x) != x) {
      set.set(x, findSet(set, set.get(x))); // path compression.
    }
    return set.get(x);
  }

  private static void unionSet(List<Integer> set, int x, int y) {
    int xRoot = findSet(set, x), yRoot = findSet(set, y);
    set.set(Math.min(xRoot, yRoot), Math.max(xRoot, yRoot));
  }
  // @exclude

  // O(nm) checking method
  static List<Integer> checkAnswer(List<Integer> A, List<Integer> E) {
    boolean[] exist = new boolean[A.size()];
    List<Integer> ans = new ArrayList<>(E.size());

    for (int i = 0; i < E.size(); ++i) {
      int minVal = Integer.MAX_VALUE;
      for (int j = 0; j <= E.get(i); ++j) {
        if (A.get(j) < minVal && !exist[A.get(j)]) {
          minVal = Math.min(A.get(j), minVal);
        }
      }
      exist[minVal] = true;
      ans.add(minVal);
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
        n = Integer.parseInt(args[0]);
        m = gen.nextInt(n) + 1;
      } else if (args.length == 2) {
        n = Integer.parseInt(args[0]);
        m = Integer.parseInt(args[1]);
      } else {
        n = gen.nextInt(1000) + 1;
        m = gen.nextInt(n) + 1;
      }
      System.out.println("n = " + n + ", m = " + m);
      List<Integer> A = new ArrayList<>(n);
      for (int i = 0; i < n; ++i) {
        A.add(i);
      }
      Collections.shuffle(A);

      /*
       * System.out.print("A = "); System.out.println(Arrays.toString(A));
       */
      List<Integer> E = new ArrayList<>(m);
      for (int i = 0; i < m; ++i) {
        E.add(gen.nextInt(n - i) + i);
      }
      Collections.sort(E);
      /*
       * System.out.print("E = "); System.out.println(Arrays.toString(E));
       */
      List<Integer> ans = offlineMinimum(A, E);
      /*
       * System.out.print("ans1 = "); System.out.println(Arrays.toString(ans));
       */
      List<Integer> tmp = checkAnswer(A, E);
      assert(ans.equals(tmp));
    }
  }
}
