package com.epi;

import java.util.Random;

public class FindElementAppearsOnce {
  // @include
  public static int findElementAppearsOnce(int[] A) {
    int ones = 0, twos = 0;
    int nextOnes, nextTwos;
    for (int i : A) {
      nextOnes = (~i & ones) | (i & ~ones & ~twos);
      nextTwos = (~i & twos) | (i & ones);
      ones = nextOnes;
      twos = nextTwos;
    }
    return ones;
  }
  // @exclude

  public static void main(String[] args) {
    Random r = new Random();
    for (int times = 0; times < 10000; ++times) {
      int n;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = r.nextInt(10000) + 1;
      }
      int[] A = new int[3 * (n - 1) + 1];
      int single = r.nextInt(n);
      int idx = 0;
      for (int i = 0; i < n; ++i) {
        A[idx++] = i;
        if (i != single) {
          A[idx++] = i;
          A[idx++] = i;
        }
      }
      System.out.println("Singleton element: " + findElementAppearsOnce(A));
      assert (findElementAppearsOnce(A) == single);
    }
  }
}
