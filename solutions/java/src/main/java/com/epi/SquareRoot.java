package com.epi;

import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class SquareRoot {
  // @include
  private static final Double EPSILON = 0.00001;

  // 0 means equal, -1 means smaller, and 1 means larger.
  private static int compare(double a, double b) {

    // Use normalization for precision problem.
    double diff = (a - b) / b;
    return diff < -EPSILON ? -1 : (diff > EPSILON ? 1 : 0);
  }

  public static double squareRoot(double x) {
    // Decide the search range according to x.
    double l, r;
    if (compare(x, 1.0) < 0) { // x < 1.0.
      l = x;
      r = 1.0;
    } else { // x >= 1.0.
      l = 1.0;
      r = x;
    }

    // Keep searching if l < r.
    while (compare(l, r) == -1) {
      double m = l + 0.5 * (r - l);
      double squareM = m * m;
      if (compare(squareM, x) == 0) {
        return m;
      } else if (compare(squareM, x) == 1) {
        r = m;
      } else {
        l = m;
      }
    }
    return l;
  }

  // @exclude

  public static void main(String[] args) {
    Random r = new Random();
    for (int times = 0; times < 100000; ++times) {
      double x;
      if (args.length == 1) {
        x = Double.valueOf(args[0]);
      } else {
        x = r.nextDouble() * 100000000;
      }
      double[] res = new double[2];
      System.out.println("x is " + x);
      System.out.println((res[0] = squareRoot(x)) + " "
          + (res[1] = Math.sqrt(x)));
      assert (compare(res[0], res[1]) == 0);
    }
  }
}
