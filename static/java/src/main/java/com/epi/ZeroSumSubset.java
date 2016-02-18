// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static com.epi.utils.Utils.iota;

public class ZeroSumSubset {
  // @include
  public static List<Integer> find0SumSubset(List<Integer> A) {
    List<Integer> prefixSum = new ArrayList<>(A);
    for (int i = 0; i < prefixSum.size(); ++i) {
      prefixSum.set(i, prefixSum.get(i) + (i > 0 ? prefixSum.get(i - 1) : 0));
      prefixSum.set(i, prefixSum.get(i) % A.size());
    }

    List<Integer> table = new ArrayList<>(A.size());
    for (int i = 1; i <= A.size(); ++i) {
      table.add(-1);
    }

    for (int i = 0; i < A.size(); ++i) {
      if (prefixSum.get(i) == 0) {
        List<Integer> ans = new ArrayList<>(i + 1);
        int val = 0;
        for (int j = 1; j <= i + 1; j++) {
          ans.add(val++);
        }
        return ans;
      } else if (table.get(prefixSum.get(i)) != -1) {
        List<Integer> ans = new ArrayList<>(i - table.get(prefixSum.get(i)));
        iota(ans, i - table.get(prefixSum.get(i)),
             table.get(prefixSum.get(i)) + 1);
        return ans;
      }
      table.set(prefixSum.get(i), i);
    }
    // @exclude
    return Collections.emptyList(); // it should not happen
    // @include
  }
  // @exclude

  static void checkAns(List<Integer> A, List<Integer> ans) {
    int sum = 0;
    for (int a : ans) {
      sum = (sum + A.get(a)) % A.size();
    }
    assert(sum == 0);
  }

  public static void main(String[] args) {
    Random gen = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n;
      List<Integer> A = new ArrayList<>();
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
        for (int i = 0; i < n; ++i) {
          A.add(gen.nextInt(10000));
        }
      } else if (args.length > 1) {
        for (int i = 1; i < args.length; ++i) {
          A.add(Integer.parseInt(args[i]));
        }
      } else {
        n = gen.nextInt(100) + 1;
        for (int i = 0; i < n; ++i) {
          A.add(gen.nextInt(10000));
        }
      }
      List<Integer> ans = find0SumSubset(A);
      checkAns(A, ans);
    }
  }
}
