package com.epi;

import java.util.Random;

public class Parity {
  public static void main(String[] args) {
    if (args.length == 1) {
      long x = Long.parseLong(args[0]);
      assert(Parity1.parity(x) == Parity3.parity(x));
      assert(Parity2.parity(x) == Parity3.parity(x));
      assert(Parity3.parity(x) == Parity4.parity(x));
      System.out.println("x = " + x + ", parity = " + Parity3.parity(x));
    } else {
      Random r = new Random();
      for (int times = 0; times < 1000; ++times) {
        long x = r.nextInt(Integer.MAX_VALUE);
        assert(Parity1.parity(x) == Parity3.parity(x));
        assert(Parity2.parity(x) == Parity3.parity(x));
        assert(Parity4.parity(x) == Parity3.parity(x));
        System.out.println("x = " + x + ", parity = " + Parity3.parity(x));
      }
    }
  }
}
