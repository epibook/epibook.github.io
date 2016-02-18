package com.epi;

import java.util.Random;

public class PowerXY {
  // 0 means equal, -1 means smaller, and 1 means larger.
  private static int compare(double a, double b) {
    // Uses normalization for precision problem.
    double diff = (a - b) / b;
    return diff < -1.0e-9 ? -1 : diff > 1.0e-9 ? 1 : 0;
  }

  // @include
  public static double power(double x, int y) {
    double result = 1.0;
    long power = y;
    if (y < 0) {
      power = -power;
      x = 1.0 / x;
    }
    while (power != 0) {
      if ((power & 1) != 0) {
        result *= x;
      }
      x *= x;
      // clang-format off
      power >>>= 1;
      // clang-format on
    }
    return result;
  }
  // @exclude

  public static void main(String[] args) {
    Random r = new Random();
    double x;
    int y;
    if (args.length == 2) {
      x = Double.parseDouble(args[0]);
      y = Integer.parseInt(args[1]);
      System.out.println(x + "^" + y + ": " + power(x, y) + ", "
                         + Math.pow(x, y));
      assert(compare(power(x, y), Math.pow(x, y)) == 0);
    } else {
      for (int times = 0; times < 10000; ++times) {
        x = r.nextDouble() * 10;
        y = r.nextInt(257) - 128;
        System.out.println(x + "^" + y + ": " + power(x, y) + ", "
                           + Math.pow(x, y));
        assert(compare(power(x, y), Math.pow(x, y)) == 0);
      }
    }
  }
}
