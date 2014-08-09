package com.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class KThLargestElement {
  // @include
  public static int findKthLargest(List<Integer> A, int k) {
    int left = 0, right = A.size() - 1;

    Random r = new Random();
    while (left <= right) {
      // Generates a random int in [left, right].
      int p = partition(left, right, r.nextInt(right - left + 1) + left, A);
      if (p == k - 1) {
        return A.get(p);
      } else if (p > k - 1) {
        right = p - 1;
      } else { // p < k - 1.
        left = p + 1;
      }
    }
    // @exclude
    throw new RuntimeException("no k-th node in array A");
    // @include
  }

  // Partitions A according pivot, returns its index after partition.
  private static int partition(int left, int right, int pivot,
                               List<Integer> A) {
    int pivotValue = A.get(pivot);
    int largerIndex = left;

    Collections.swap(A, pivot, right);
    for (int i = left; i < right; ++i) {
      if (A.get(i) > pivotValue) {
        Collections.swap(A, i, largerIndex++);
      }
    }
    Collections.swap(A, right, largerIndex);
    return largerIndex;
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
      List<Integer> A = new ArrayList<>();
      for (int i = 0; i < n; ++i) {
        A.add(r.nextInt(10000000));
      }
      int result = findKthLargest(A, k);
      Collections.sort(A);
      assert (result == A.get(A.size() - k));
    }
  }
}
