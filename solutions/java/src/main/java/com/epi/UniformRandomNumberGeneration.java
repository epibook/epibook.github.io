package com.epi;

import java.util.Random;

public class UniformRandomNumberGeneration {
  static int zeroOneRandom() {
    Random gen = new Random();
    return gen.nextInt(2);
  }

  // @include
  public static int uniformRandom(int a, int b) {
    int l = b - a + 1, res;
    do {
      res = 0;
      for (int i = 0; (1 << i) < l; ++i) {
        // zero_one_random() is the system-provided random number generator.
        res = (res * 2) | zeroOneRandom();
      }
    } while (res >= l);
    return res + a;
  }
  // @exclude

  public static void main(String[] args) {
    Random gen = new Random();
    for (int times = 0; times < 1000; ++times) {
      int a, b;
      if (args.length == 2) {
        a = Integer.valueOf(args[0]);
        b = Integer.valueOf(args[1]);
      } else {
        a = gen.nextInt(100);
        b = gen.nextInt(100) + a + 1;
      }
      int x = uniformRandom(a, b);
      System.out.println(String.format("a = %d, b = %d", a, b));
      System.out.println(String.format("random result = %d", x));
      assert x >= a && x <= b;
    }
  }
}
