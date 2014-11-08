package com.epi;

import java.util.*;

import static java.lang.Math.max;

public class BinarySearchUnknownLength {
  // @include
  public static int binarySearchUnknownLength(int[] A, int k) {
    // Find the possible range where k exists.
    int p = 0;
    while (true) {
      try {
        int val = A[(1 << p) - 1];
        if (val == k) {
          return (1 << p) - 1;
        } else if (val > k) {
          break;
        }
      } catch (Exception e) {
        break;
      }
      ++p;
    }

    // Binary search between indices 2^(p - 1) and 2^p - 2.
    // Need max below in case k is smaller than all entries
    // in A, since p becomes 0.
    int left = max(0, 1 << (p - 1)), right = (1 << p) - 2;
    while (left <= right) {
      int mid = left + ((right - left) / 2);
      try {
        int val = A[mid];
        if (val == k) {
          return mid;
        } else if (val > k) {
          right = mid - 1;
        } else { // A[mid] < k
          left = mid + 1;
        }
      } catch (Exception e) {
        right = mid - 1; // Search the left part if out of boundary.
      }
    }
    return -1; // Nothing matched k.
  }
  // @exclude

  private static void smallTest() {
    int[] A = new int[]{1, 2, 3};
    assert (binarySearchUnknownLength(A, 3) == 2);
    assert (binarySearchUnknownLength(A, 1) == 0);
    assert (binarySearchUnknownLength(A, 2) == 1);
    assert (binarySearchUnknownLength(A, 4) == -1);
  }

  public static void main(String[] args) {
    smallTest();
    int n, k;
    Random r = new Random();
    // try-catch array walk is staggeringly slow,
    // Effective Java, 2nd edition, item 57
    for (int times = 0; times < 1000; ++times) {
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
        k = r.nextInt(n * 2);
      } else if (args.length == 2) {
        n = Integer.parseInt(args[0]);
        k = Integer.parseInt(args[1]);
      } else {
        n = r.nextInt(1000000) + 1;
        k = r.nextInt(n * 2);
      }
      int[] A = new int[n];
      for (int i = 0; i < n; ++i) {
        A[i] = r.nextInt(n * 2);
      }
      Arrays.sort(A);
      System.out.println(n + " " + k);
      int idx = binarySearchUnknownLength(A, k);
      System.out.println(idx);
      assert (idx != -1 && A[idx] == k || Arrays.binarySearch(A, k) < 0);
    }
  }
}
