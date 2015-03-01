// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.Random;

public class NextPermutation {
  // @include
  public static int[] nextPermutation(int[] p) {
    int k = p.length - 2;
    while (k >= 0 && p[k] >= p[k + 1]) {
      --k;
    }
    if (k == -1) {
      return new int[0]; // p is the last permutation.
    }

    // Swap the smallest entry after index k that is greater than p[k].
    // We exploit the fact that p[k + 1 : p.size() - 1] is decreasing so if we
    // search in reverse order, the first entry that is greater than p[k] is
    // the smallest such entry.
    for (int i = p.length - 1; i > k; --i) {
      if (p[i] > p[k]) {
        swap(p, k, i);
        break;
      }
    }

    // Since p[k + 1 : p.size() - 1] is in decreasing order, we can build the
    // smallest dictionary ordering of this subarray by reversing it.
    reverse(p, k + 1, p.length - 1);
    return p;
  }

  private static void reverse(int[] p, int a, int b) {
    for (int i = a, j = b; i < j; ++i, --j) {
      swap(p, i, j);
    }
  }

  private static void swap(int[] p, int a, int b) {
    int temp = p[a];
    p[a] = p[b];
    p[b] = temp;
  }
  // @exclude

  private static void simplePrint(int[] A) {
    for (int i = 0; i < A.length; ++i) {
      System.out.print(A[i] + " ");
    }
  }

  public static void main(String[] args) {
    for (int times = 0; times < 1000; ++times) {
      Random gen = new Random();
      int n = (args.length == 1 ? Integer.valueOf(args[0]) : (gen.nextInt(10) + 1));
      int[] p = new int[n];
      for (int i = 0; i < n; ++i) {
        p[i] = gen.nextInt(n);
      }
      System.out.print("p = ");
      simplePrint(p);
      System.out.println();

      int[] ans = nextPermutation(p);
      System.out.print("ans = ");
      simplePrint(ans);
      System.out.println();
    }
  }
}
