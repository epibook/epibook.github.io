package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class BinarySearchUnknownLength {
  // @include
  public static int binarySearchUnknownLength(List<Integer> A, int k) {
    // Find a range where k exists, if it's present.
    int p = 0;
    while (true) {
      try {
        int idx = (1 << p) - 1; // 2^p - 1.
        if (A.get(idx) == k) {
          return idx;
        } else if (A.get(idx) > k) {
          break;
        }
      } catch (IndexOutOfBoundsException e) {
        break;
      }
      ++p;
    }

    // Binary search between indices 2^(p - 1) and 2^p - 2, inclusive.
    int left = Math.max(0, 1 << (p - 1)), right = (1 << p) - 2;
    while (left <= right) {
      int mid = left + ((right - left) / 2);
      try {
        if (A.get(mid) == k) {
          return mid;
        } else if (A.get(mid) > k) {
          right = mid - 1;
        } else { // A.get(mid) < k
          left = mid + 1;
        }
      } catch (Exception e) {
        right = mid - 1; // Search the left part if out-of-bound.
      }
    }
    return -1; // Nothing matched k.
  }
  // @exclude

  private static void smallTest() {
    List<Integer> A = Arrays.asList(1, 2, 3);
    assert(binarySearchUnknownLength(A, 3) == 2);
    assert(binarySearchUnknownLength(A, 1) == 0);
    assert(binarySearchUnknownLength(A, 2) == 1);
    assert(binarySearchUnknownLength(A, 4) == -1);
    assert(binarySearchUnknownLength(A, -1) == -1);
  }

  public static void main(String[] args) {
    smallTest();
    int n, k;
    Random r = new Random();
    // try-catch array walk is slow,
    // Effective Java, 2nd edition, item 57
    for (int times = 0; times < 10000; ++times) {
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
        k = r.nextInt(n * 2);
      } else if (args.length == 2) {
        n = Integer.parseInt(args[0]);
        k = Integer.parseInt(args[1]);
      } else {
        n = r.nextInt(1000) + 1;
        k = r.nextInt(n * 2);
      }
      List<Integer> A = new ArrayList<>(n);
      for (int i = 0; i < n; ++i) {
        A.add(r.nextInt(n * 2));
      }
      Collections.sort(A);
      System.out.println(n + " " + k);
      int idx = binarySearchUnknownLength(A, k);
      System.out.println(idx);
      assert(idx != -1 && A.get(idx) == k
             || Collections.binarySearch(A, k) < 0);
    }
  }
}
