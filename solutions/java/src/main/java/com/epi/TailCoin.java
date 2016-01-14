package com.epi;

import java.util.Random;

public class TailCoin {
  // @include
  // Return the number of failed trails.
  public static int simulateBiasedCoin(int n, int trails) {
    // We use it to generate random double in [0.0, 1.0].
    Random gen = new Random();
    double kBias = 0.4;
    int fails = 0;
    for (int i = 0; i < trails; ++i) {
      int biasedNum = 0;
      for (int j = 0; j < n; ++j) {
        biasedNum += (gen.nextDouble() >= kBias ? 1 : 0);
      }

      if (biasedNum < (n / 2)) {
        ++fails;
      }
    }
    return fails;
  }
  // @exclude

  public static void main(String[] args) {
    int n, trails;
    Random gen = new Random();
    if (args.length == 2) {
      n = Integer.parseInt(args[0]);
      trails = Integer.parseInt(args[1]);
    } else {
      n = gen.nextInt(1000) + 1;
      trails = gen.nextInt(1000) + 1;
    }
    int fails = simulateBiasedCoin(n, trails);
    System.out.println("fails = " + fails);
    System.out.println("ratio = " + ((double)fails) / trails);
  }
}
