package com.epi;

import java.util.Random;

public class SquareRoot {
  // @include
  public static double squareRoot(double x) {
    // Decides the search range according to x.
    double left, right;
    if (compare(x, 1.0) < 0) { // x < 1.0.
      left = x;
      right = 1.0;
    } else { // x >= 1.0.
      left = 1.0;
      right = x;
    }

    // Keeps searching if left < right.
    while (compare(left, right) == -1) {
      double mid = left + 0.5 * (right - left);
      double squareM = mid * mid;
      if (compare(squareM, x) == 0) {
        return mid;
      } else if (compare(squareM, x) == 1) {
        right = mid;
      } else {
        left = mid;
      }
    }
    return left;
  }

  // 0 means equal, -1 means smaller, and 1 means larger.
  private static int compare(double a, double b) {
    final double EPSILON = 0.00001;
    // Uses normalization for precision problem.
    double diff = (a - b) / b;
    return diff < -EPSILON ? -1 : (diff > EPSILON ? 1 : 0);
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
