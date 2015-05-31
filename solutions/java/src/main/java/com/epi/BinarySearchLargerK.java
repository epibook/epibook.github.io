package com.epi;

import java.util.Arrays;
import java.util.Random;

public class BinarySearchLargerK {
  // @include
  public static int searchFirstLargerOfK(int[] A, int k) {
    int left = 0, right = A.length - 1, result = -1;
    // [left : right] is the candidate set.
    while (left <= right) {
      int mid = left + ((right - left) / 2);
      if (A[mid] > k) {
        result = mid;
        right = mid - 1; // Nothing to the right of mid can be solution.
      } else { // A[mid] <= k.
        left = mid + 1;
      }
    }
    return result;
  }
  // @exclude

  private static int checkAns(int[] A, int k) {
    for (int i = 0; i < A.length; ++i) {
      if (A[i] > k) {
        return i;
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    Random r = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = r.nextInt(10000) + 1;
      }
      int[] A = new int[n];
      for (int i = 0; i < n; ++i) {
        A[i] = r.nextInt(n);
      }
      Arrays.sort(A);
      int k = r.nextInt(n);
      int ans = searchFirstLargerOfK(A, k);
      System.out.println("k = " + k + " locates at " + ans);
      if (ans != -1) {
        System.out.println("A[k] = " + A[ans]);
      }
      assert(ans == checkAns(A, k));
    }
  }
}
