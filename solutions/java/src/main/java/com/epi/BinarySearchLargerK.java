package com.epi;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class BinarySearchLargerK {
  // @include
  public static int searchFirstLargerOfK(List<Integer> A, int k) {
    int left = 0, right = A.size() - 1, result = -1;
    // A.subList(left, right + 1) is the candidate set.
    while (left <= right) {
      int mid = left + ((right - left) / 2);
      if (A.get(mid) > k) {
        result = mid;
        right = mid - 1; // Nothing to the right of mid can be solution.
      } else { // A.get(mid) <= k.
        left = mid + 1;
      }
    }
    return result;
  }
  // @exclude

  private static int checkAns(List<Integer> A, int k) {
    for (int i = 0; i < A.size(); ++i) {
      if (A.get(i) > k) {
        return i;
      }
    }
    return -1;
  }

  private static void simpleTest() {
    List<Integer> A = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7);
    int k = 4;
    assert(1 == searchFirstLargerOfK(A, 0));
    assert(2 == searchFirstLargerOfK(A, 1));
    assert(5 == searchFirstLargerOfK(A, 4));
    assert(7 == searchFirstLargerOfK(A, 6));
    assert(-1 == searchFirstLargerOfK(A, 7));
    assert(0 == searchFirstLargerOfK(A, -1));
    assert(0 == searchFirstLargerOfK(A, Integer.MIN_VALUE));
    assert(-1 == searchFirstLargerOfK(A, Integer.MAX_VALUE));
    A = Arrays.asList(1, 1, 2, 3, 4, 5, 6, 7);
    assert(2 == searchFirstLargerOfK(A, 1));
    A = Arrays.asList(1, 1, 2, 3, 4, 4, 4, 7);
    assert(7 == searchFirstLargerOfK(A, 4));
    A = Arrays.asList(1, 1, 1, 1, 1, 2);
    assert(5 == searchFirstLargerOfK(A, 1));
    assert(-1 == searchFirstLargerOfK(A, 5));
  }

  public static void main(String[] args) {
    simpleTest();
    Random r = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = r.nextInt(10000) + 1;
      }
      List<Integer> A = new ArrayList<>();
      for (int i = 0; i < n; ++i) {
        A.add(r.nextInt(n));
      }
      Collections.sort(A);
      int k = r.nextInt(n);
      int ans = searchFirstLargerOfK(A, k);
      System.out.println("k = " + k + " locates at " + ans);
      if (ans != -1) {
        System.out.println("A[k] = " + A.get(ans));
      }
      assert(ans == checkAns(A, k));
    }
  }
}
