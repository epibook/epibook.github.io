// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.*;

public class BinarySearchAiEqI {
  // @include
  public static int searchEntryEqualToItsIndex(int[] A) {
    int left = 0, right = A.length - 1;
    while (left <= right) {
      int mid = left + ((right - left) / 2);
      int difference = A[mid] - mid;
      // A[mid] == mid if and only if difference == 0.
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
  private static int checkAns(int[] A) {
    int ret = -1;
    for (int i = 0; i < A.length; ++i) {
      if (A[i] == i) {
        return i;
      }
    }
    return ret;
  }

  private static void SimpleTest() {
    int[] A = new int[] {0, 1, 2, 3};
    assert(-1 != searchEntryEqualToItsIndex(A));
    assert(0 <= searchEntryEqualToItsIndex(A) &&
           searchEntryEqualToItsIndex(A) <= 3);
    A[0] = -1;
    A[2] = 4;
    A[3] = 5;
    assert(1 == searchEntryEqualToItsIndex(A));
    A = new int[] {0};
    assert(-1 != searchEntryEqualToItsIndex(A));
    A[0] = -1;
    assert(-1 == searchEntryEqualToItsIndex(A));
  }

  public static void main(String[] args) {
    SimpleTest();
    Random r = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n;
      Set<Integer> table = new HashSet<>();
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = r.nextInt(1000) + 1;
      }
      int[] A = new int[n];
      for (int i = 0; i < n; ++i) {
        int x;
        do {
          x = r.nextInt(1999) - 999;
        } while (table.contains(x));
        table.add(x);
        A[i] = x;
      }
      Arrays.sort(A);
      int ans = searchEntryEqualToItsIndex(A);
      if (ans != -1) {
        System.out.println("A[" + ans + "] = " + A[ans]);
        assert(A[ans] == ans);
      } else {
        System.out.println("no entry where A[k] = k");
        assert(checkAns(A) == -1);
      }
    }
  }
}
