package com.epi;

import java.util.Random;

import static com.epi.utils.Utils.simplePrint;

public class PickingUpCoinsDontLose {
  // @include
  // Return 0 means choosing F (even numbered coins),
  // and return 1 means choosing S (odd numbered coins).
  static int pickUpCoins(int[] C) {
    int evenSum = 0, oddSum = 0;
    for (int i = 0; i < C.length; ++i) {
      if ((i % 2) == 1) { // odd.
        oddSum += C[i];
      } else { // even.
        evenSum += C[i];
      }
    }
    return evenSum >= oddSum ? 0 : 1;
  }
  // @exclude

  static void check(int[] C, int choose) {
    int even = 0, odd = 0;
    for (int i = 0; i < C.length; ++i) {
      if ((i % 2) == 1) {
        odd += C[i];
      } else {
        even += C[i];
      }
    }
    if (choose == 0) {
      assert(even >= odd);
    } else {
      assert(odd >= even);
    }
  }

  public static void main(String[] args) {
    Random gen = new Random();
    for (int times = 0; times < 1000; ++times) {
      int[] C;
      if (args.length >= 1) {
        C = new int[args.length - 1];
        for (int i = 0; i < args.length; ++i) {
          C[i] = Integer.valueOf(args[i]);
        }
      } else {
        C = new int[gen.nextInt(1000)];
        for (int i = 0; i < C.length; ++i) {
          C[i] = gen.nextInt(100);
        }
      }

      simplePrint(C);
      System.out.println();

      int res = pickUpCoins(C);
      System.out.println(res);
      check(C, res);
    }
  }
}
