package com.epi;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.epi.utils.Utils.simplePrint;

public class OnlineSampling {
  // @include
  public static int[] onlineSampling(int n, int k) {
    Map<Integer, Integer> H = new HashMap<>();
    Random gen = new Random(); // Random num generator.
    for (int i = 0; i < k; ++i) {
      // Generate random int in [0, n - 1 - i].
      int r = gen.nextInt(n - i);
      Integer ptr1 = H.get(r), ptr2 = H.get(n - 1 - i);
      if (ptr1 == null && ptr2 == null) {
        H.put(r, n - 1 - i);
        H.put(n - 1 - i, r);
      } else if (ptr1 == null && ptr2 != null) {
        H.put(r, ptr2);
        H.put(n - 1 - i, r);
      } else if (ptr1 != null && ptr2 == null) {
        H.put(n - 1 - i, ptr1);
        H.put(r, n - 1 - i);
      } else {
        H.put(n - 1 - i, ptr1);
        H.put(r, ptr2);
      }
    }

    int[] res = new int[k];
    for (int i = 0; i < k; ++i) {
      res[i] = H.get(n - 1 - i);
    }
    return res;
  }
  // @exclude

  public static void main(String[] args) {
    Random gen = new Random();
    int n, k;
    if (args.length == 1) {
      n = Integer.valueOf(args[0]);
      k = gen.nextInt(n) + 1;
    } else if (args.length == 2) {
      n = Integer.valueOf(args[0]);
      k = Integer.valueOf(args[1]);
    } else {
      n = gen.nextInt(10000);
      k = gen.nextInt(n) + 1;
    }

    System.out.println(String.format("n = %d k = %d", n, k));
    for (int i = 0; i < 6; ++i) {
      int[] res = onlineSampling(n, k);
      System.out.print("result = ");
      simplePrint(res);
      System.out.println();
    }
  }
}
