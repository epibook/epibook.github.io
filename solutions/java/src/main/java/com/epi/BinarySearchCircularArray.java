package com.epi;

import java.util.*;

public class BinarySearchCircularArray {
  // @include
  public static int searchSmallest(int[] A) {
    int left = 0, right = A.length - 1;
    while (left < right) {
      int mid = left + ((right - left) / 2);
      if (A[mid] > A[right]) {
        // Minimum must be in [mid + 1 : right].
        left = mid + 1;
      } else { // A[mid] < A[right].
        // Minimum cannot be in [mid + 1 : right] so it must be in [left : mid].
        right = mid;
      }
    }
    // Loop ends when left == right.
    return left;
  }
  // @exclude

  private static void reverse(int[] A, int a, int b) {
    for (int i = a, j = b; i < j; ++i, --j) {
      int temp = A[i];
      A[i] = A[j];
      A[j] = temp;
    }
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
      Set<Integer> table = new HashSet<>();
      for (int i = 0; i < n; ++i) {
        while (true) {
          int x = r.nextInt(100001);
          if (table.add(x)) {
            A[i] = x;
            break;
          }
        }
      }
      Arrays.sort(A);
      int shift = r.nextInt(n);
      reverse(A, 0, A.length - 1);
      reverse(A, 0, shift);
      reverse(A, shift + 1, A.length - 1);
      // System.out.println(A);
      assert((shift + 1) % n == searchSmallest(A));
    }
    // hand-made tests.
    int[] A = new int[] {2, 3, 4};
    assert(0 == searchSmallest(A));
    A = new int[] {100, 101, 102, 2, 5};
    assert(3 == searchSmallest(A));
    A = new int[] {10, 20, 30, 40, 5};
    assert(4 == searchSmallest(A));
  }
}
