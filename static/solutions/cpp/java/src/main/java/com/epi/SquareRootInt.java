package com.epi;
/*
@slug
square-root-int

@problem
Write a program which takes a nonnegative integer
and returns the largest integer
whose square is less than or equal to the given integer.
For example, if the input is $16$, return $4$; if the input is $300$, return
$17$, since $17^2 = 289 < 300$ and $18^2 = 324 > 300$.

*/

import java.util.Random;

public class SquareRootInt {
  // @include
  public static int squareRoot(int k) {
    long left = 0, right = k;
    // Candidate interval [left, right] where everything before left has
    // square <= k, and everything after right has square > k.
    while (left <= right) {
      long mid = left + ((right - left) / 2);
      long midSquared = mid * mid;
      if (midSquared <= k) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    return (int)left - 1;
  }
  // @exclude

  private static void simpleTest() {
    assert(squareRoot(0) == 0);
    assert(squareRoot(1) == 1);
    assert(squareRoot(2) == 1);
    assert(squareRoot(3) == 1);
    assert(squareRoot(4) == 2);
    assert(squareRoot(7) == 2);
    assert(squareRoot(121) == 11);
    assert(squareRoot(64) == 8);
    assert(squareRoot(300) == 17);
    assert(squareRoot(Integer.MAX_VALUE) == 46340);
  }

  public static void main(String[] args) {
    simpleTest();
    int x;
    if (args.length == 1) {
      x = Integer.parseInt(args[0]);
    } else {
      Random r = new Random();
      for (int times = 0; times < 100000; ++times) {
        x = r.nextInt(Integer.MAX_VALUE);
        int result[] = new int[2];
        System.out.println("x is " + x);
        System.out.println((result[0] = squareRoot(x)) + " "
                           + (result[1] = (int)Math.sqrt(x)));
        assert(result[0] == result[1]);
      }
    }
  }
}
