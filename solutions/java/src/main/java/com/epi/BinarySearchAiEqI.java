// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class BinarySearchAiEqI {
  // @include
  public static int searchEntryEqualToItsIndex(List<Integer> A) {
    int left = 0, right = A.size() - 1;
    while (left <= right) {
      int mid = left + ((right - left) / 2);
      int difference = A.get(mid) - mid;
      // A.get(mid) == mid if and only if difference == 0.
      if (difference == 0) {
        return mid;
      } else if (difference > 0) {
        right = mid - 1;
      } else { // difference < 0.
        left = mid + 1;
      }
    }
    return -1;
  }
  // @exclude

  // O(n) way to find ans.
  private static int checkAns(List<Integer> A) {
    int ret = -1;
    for (int i = 0; i < A.size(); ++i) {
      if (A.get(i) == i) {
        return i;
      }
    }
    return ret;
  }

  private static void simpleTest() {
    List<Integer> A = Arrays.asList(0, 1, 2, 3);
    assert(-1 != searchEntryEqualToItsIndex(A));
    assert(0 <= searchEntryEqualToItsIndex(A)
           && searchEntryEqualToItsIndex(A) <= 3);
    A = Arrays.asList(-1, 1, 4, 5);
    assert(1 == searchEntryEqualToItsIndex(A));
    A = Arrays.asList(0);
    assert(-1 != searchEntryEqualToItsIndex(A));
    A = Arrays.asList(-1);
    assert(-1 == searchEntryEqualToItsIndex(A));
  }

  public static void main(String[] args) {
    simpleTest();
    Random r = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n;
      Set<Integer> table = new HashSet<>();
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = r.nextInt(1000) + 1;
      }
      List<Integer> A = new ArrayList<>();
      for (int i = 0; i < n; ++i) {
        int x;
        do {
          x = r.nextInt(1999) - 999;
        } while (table.contains(x));
        table.add(x);
        A.add(x);
      }
      Collections.sort(A);
      int ans = searchEntryEqualToItsIndex(A);
      if (ans != -1) {
        System.out.println("A[" + ans + "] = " + A.get(ans));
        assert(A.get(ans) == ans);
      } else {
        System.out.println("no entry where A[k] = k");
        assert(checkAns(A) == -1);
      }
    }
  }
}
