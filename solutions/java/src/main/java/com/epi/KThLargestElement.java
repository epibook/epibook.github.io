package com.epi;

import java.util.Arrays;
import java.util.Random;

public class KThLargestElement {
  // @include
  public static int findKthLargest(int[] A, int k) {
    int left = 0, right = A.length - 1;
    Random r = new Random();
    while (left <= right) {
      // Generates a random int in [left, right].
      int pivotIdx = r.nextInt(right - left + 1) + left;
      int newPivotIdx = partitionAroundPivot(left, right, pivotIdx, A);
      if (newPivotIdx == k - 1) {
        return A[newPivotIdx];
      } else if (newPivotIdx > k - 1) {
        right = newPivotIdx - 1;
      } else { // newPivotIdx < k - 1.
        left = newPivotIdx + 1;
      }
    }
    // @exclude
    throw new RuntimeException("no k-th node in array A");
    // @include
  }

  // Partition A[left : right] around pivotIdx, returns the new index of the
  // pivot, newPivotIdx, after partition. After partitioning,
  // A[left : newPivotIdx - 1] contains elements that are greater than the
  // pivot, and A[newPivotIdx + 1 : right] contains elements that are less
  // than the pivot.
  private static int partitionAroundPivot(int left, int right, int pivotIdx,
                                          int[] A) {
    int pivotValue = A[pivotIdx];
    int newPivotIdx = left;

    swap(A, pivotIdx, right);
    for (int i = left; i < right; ++i) {
      if (A[i] > pivotValue) {
        swap(A, i, newPivotIdx++);
      }
    }
    swap(A, right, newPivotIdx);
    return newPivotIdx;
  }

  private static void swap(int[] A, int a, int b) {
    int temp = A[a];
    A[a] = A[b];
    A[b] = temp;
  }
  // @exclude

  public static void main(String[] args) {
    Random r = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n, k;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
        k = r.nextInt(n) + 1;
      } else if (args.length == 2) {
        n = Integer.parseInt(args[0]);
        k = Integer.parseInt(args[1]);
      } else {
        n = r.nextInt(100000) + 1;
        k = r.nextInt(n - 1) + 1;
      }
      int[] A = new int[n];
      for (int i = 0; i < n; ++i) {
        A[i] = r.nextInt(10000000);
      }
      int result = findKthLargest(A, k);
      Arrays.sort(A);
      assert(result == A[A.length - k]);
    }
  }
}
