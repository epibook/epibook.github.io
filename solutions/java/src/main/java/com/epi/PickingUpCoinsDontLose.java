package com.epi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.epi.utils.Utils.simplePrint;

public class PickingUpCoinsDontLose {
  // @include
  // Return 0 means choosing F (even numbered coins),
  // and return 1 means choosing S (odd numbered coins).
  public static int pickUpCoins(List<Integer> C) {
    int evenSum = 0, oddSum = 0;
    for (int i = 0; i < C.size(); ++i) {
      if ((i % 2) != 0) { // odd.
        oddSum += C.get(i);
      } else { // even.
        evenSum += C.get(i);
      }
    }
    return evenSum >= oddSum ? 0 : 1;
  }
  // @exclude

  private static void check(List<Integer> C, int choose) {
    int even = 0, odd = 0;
    for (int i = 0; i < C.size(); ++i) {
      if ((i % 2) != 0) {
        odd += C.get(i);
      } else {
        even += C.get(i);
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
      List<Integer> C = new ArrayList<>();
      if (args.length >= 1) {
        for (int i = 0; i < args.length; ++i) {
          C.add(Integer.parseInt(args[i]));
        }
      } else {
        int n = gen.nextInt(1000);
        for (int i = 0; i < n; ++i) {
          C.add(gen.nextInt(100));
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
