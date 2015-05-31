package com.epi;

import java.util.Arrays;
import java.util.Random;

public class BinarySearchCircularArrayWithDuplicates {
  // @include
  public static int searchSmallest(int[] A) {
    return searchSmallestHelper(A, 0, A.length - 1);
  }

  private static int searchSmallestHelper(int[] A, int left, int right) {
    if (left == right) {
      return left;
    }

    int mid = left + ((right - left) / 2);
    if (A[mid] > A[right]) {
      return searchSmallestHelper(A, mid + 1, right);
    } else if (A[mid] < A[right]) {
      return searchSmallestHelper(A, left, mid);
    } else { // A[mid] == A[right]
      // We cannot eliminate either side so we compare the results from both
      // sides.
      int leftResult = searchSmallestHelper(A, left, mid);
      int rightResult = searchSmallestHelper(A, mid + 1, right);
      return A[rightResult] < A[leftResult] ? rightResult : leftResult;
    }
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
    for (int times = 0; times < 10000; ++times) {
      int n;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = r.nextInt(10000) + 1;
      }
      int[] A = new int[n];
      for (int i = 0; i < n; i++) {
        A[i] = r.nextInt(1000000);
      }
      Arrays.sort(A);
      int shift = r.nextInt(n);
      reverse(A, 0, A.length - 1);
      reverse(A, 0, shift);
      reverse(A, shift + 1, A.length - 1);
      // System.out.println(A);
      assert((shift + 1) % n == searchSmallest(A));
    }
    // hand-made tests
    int[] A = new int[] {2, 2, 2};
    assert(0 == searchSmallest(A));
    A = new int[] {100, 2, 5, 5};
    assert(1 == searchSmallest(A));
    A = new int[] {1, 2, 3, 3, 3};
    assert(0 == searchSmallest(A));
    A = new int[] {5, 2, 3, 3, 3};
    assert(1 == searchSmallest(A));
  }
}
