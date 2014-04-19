package com.epi;

import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class Parity {
  public static void main(String[] args) {
    if (args.length == 1) {
      long x = Long.parseLong(args[0]);
      assert (Parity1.parity1(x) == Parity3.parity3(x));
      assert (Parity2.parity2(x) == Parity3.parity3(x));
      assert (Parity3.parity3(x) == Parity4.parity4(x));
      System.out.println("x = " + x + ", parity = " + Parity3.parity3(x));
    } else {
      Random r = new Random();
      for (int times = 0; times < 1000; ++times) {
        long x = r.nextInt(Integer.MAX_VALUE);
        assert (Parity1.parity1(x) == Parity3.parity3(x));
        assert (Parity2.parity2(x) == Parity3.parity3(x));
        assert (Parity4.parity4(x) == Parity3.parity3(x));
        System.out.println("x = " + x + ", parity = " + Parity3.parity3(x));
      }
    }
  }
}
