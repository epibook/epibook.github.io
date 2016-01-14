package com.epi;

import java.util.Random;

public class GCD {
  public static void main(String[] args) {
    long x = 18, y = 12;
    assert(GCD1.GCD(x, y) == 6);
    if (args.length == 2) {
      x = Integer.parseInt(args[0]);
      y = Integer.parseInt(args[1]);
      System.out.println(GCD1.GCD(x, y));
      assert(GCD1.GCD(x, y) == GCD2.GCD(x, y));
    } else {
      Random r = new Random();
      for (int times = 0; times < 1000; ++times) {
        x = r.nextInt(Integer.MAX_VALUE);
        y = r.nextInt(Integer.MAX_VALUE);
        assert(GCD1.GCD(x, y) == GCD2.GCD(x, y));
      }
    }
  }
}
