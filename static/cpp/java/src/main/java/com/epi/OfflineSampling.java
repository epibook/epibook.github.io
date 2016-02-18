package com.epi;

import java.util.Arrays;
import java.util.Random;

import static com.epi.utils.Utils.swap;

public class OfflineSampling {
  // @include
  public static int[] offlineSampling(int[] A, int k) {
    Random gen = new Random();
    for (int i = 0; i < k; ++i) {
      // Generate a random int in [i, A.length - 1].
      swap(A, i, gen.nextInt(A.length - 1));
    }
    int[] ans = Arrays.copyOf(A, k);
    return ans;
  }
  // @exclude

  public static void main(String[] args) {
    int n, k;
    Random gen = new Random();
    int[] A;

    if (args.length == 1) {
      n = Integer.valueOf(args[0]);
      k = gen.nextInt(n) + 1;
    } else if (args.length == 2) {
      n = Integer.valueOf(args[0]);
      k = Integer.valueOf(args[1]);
    } else {
      n = gen.nextInt(1000000) + 1;
      k = gen.nextInt(n) + 1;
    }

    A = new int[n];
    for (int i = 0; i < n; ++i) {
      A[i] = i;
    }
    System.out.println(n + " " + k);

    int[] ans = offlineSampling(A, k);

    assert ans.length == k;
    for (int a : ans) {
      System.out.print(a + " ");
    }
    System.out.println();
  }
}
