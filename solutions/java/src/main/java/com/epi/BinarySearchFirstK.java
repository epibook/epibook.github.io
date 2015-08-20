package com.epi;

import java.util.Arrays;
import java.util.Random;

public class BinarySearchFirstK {
  // @include
  public static int searchFirstOfK(int[] A, int k) {
    int left = 0, right = A.length - 1, result = -1;
    // [left : right] is the candidate set.
    while (left <= right) {
      int mid = left + ((right - left) / 2);
      if (A[mid] > k) {
        right = mid - 1;
      } else if (A[mid] == k) {
        result = mid;
        // Nothing to the right of mid can be the first occurrence of k.
        right = mid - 1;
      } else { // A[mid] < k
        left = mid + 1;
      }
    }
    return result;
  }
  // @exclude

  private static int checkAns(int[] A, int k) {
    for (int i = 0; i < A.length && A[i] <= k; ++i) {
      if (A[i] == k) {
        return i;
      }
    }
    return -1;
  }

  private static void SimpleTest() {
    int[] A = new int[] {0, 1, 2, 3, 4, 5, 6, 7};
    int k = 4;
    assert(0 == searchFirstOfK(A, 0));
    assert(1 == searchFirstOfK(A, 1));
    assert(4 == searchFirstOfK(A, 4));
    assert(6 == searchFirstOfK(A, 6));
    assert(7 == searchFirstOfK(A, 7));
    assert(-1 == searchFirstOfK(A, 8));
    assert(-1 == searchFirstOfK(A, Integer.MIN_VALUE));
    A[0] = 1;
    assert(0 == searchFirstOfK(A, 1));
    A[5] = 4;
    A[6] = 4;
    assert(4 == searchFirstOfK(A, 4));
    A = new int[] {1, 1, 1, 1, 1, 2};
    assert(-1 == searchFirstOfK(A, 0));
    assert(0 == searchFirstOfK(A, 1));
    assert(5 == searchFirstOfK(A, 2));
    A[4] = 2;
    assert(4 == searchFirstOfK(A, 2));
  }

  public static void main(String[] args) {
    SimpleTest();
    Random r = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = r.nextInt(100000) + 1;
      }
      int[] A = new int[n];
      int k = r.nextInt(n);
      for (int i = 0; i < n; ++i) {
        A[i] = r.nextInt(n);
      }
      Arrays.sort(A);
      int ans = searchFirstOfK(A, k);
      System.out.println("k = " + k + " locates at " + ans);
      if (ans != -1) {
        System.out.println("A[k] = " + A[ans]);
      }
      assert(checkAns(A, k) == ans);
    }
  }
}
