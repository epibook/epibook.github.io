package com.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class KthLargestElementTemplate {
  // @include
  // Partition A according pivot, return its index after partition.
  public static int partition(List<Integer> A, int l, int r, int pivot) {
    int pivotValue = A.get(pivot);
    int largerIndex = l;

    Collections.swap(A, pivot, r);
    for (int i = l; i < r; ++i) {
      if (A.get(i) > pivotValue) {
        Collections.swap(A, i, largerIndex++);
      }
    }
    Collections.swap(A, r, largerIndex);
    return largerIndex;
  }

  public static int findKthLargest(List<Integer> A, int k) {
    int l = 0, r = A.size() - 1;

    while (l <= r) {
      Random rand = new Random();
      int p = partition(A, l, r, rand.nextInt(r - l + 1) + l);
      if (p == k - 1) {
        return A.get(p);
      } else if (p > k - 1) {
        r = p - 1;
      } else { // p < k - 1
        l = p + 1;
      }
    }
    // @exclude
    throw new RuntimeException("no k-th node in array A");
    // @include
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
        k = r.nextInt(n) + 1;
      }
      ArrayList<Integer> A = new ArrayList<Integer>();
      for (int i = 0; i < n; ++i) {
        A.add(r.nextInt(10000000));
      }
      int res = findKthLargest(A, k);
      Collections.sort(A);
      assert (res == A.get(A.size() - k));
    }
  }
}
