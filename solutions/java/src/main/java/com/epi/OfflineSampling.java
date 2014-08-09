package com.epi;

import java.util.Random;

import static com.epi.utils.Utils.swap;

public class OfflineSampling {
  // @include
  public static int[] offlineSampling(int[] A, int k) {
    Random gen = new Random(); // Random num generator.
    for (int i = A.length - 1; i > A.length - 1 - k; i--) {
      // Generate random int in [i, A.size() - 1].
      swap(A, i, gen.nextInt(A.length - i) + i);
    }
    return A;
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

    assert ans.length == n;
    for (int i = n - 1; i > n - 1 - k; i--) {
      System.out.print(ans[i] + " ");
    }
    System.out.println();
  }
}
