package com.epi;

import java.util.Random;

public class SquareRootInt {
  // @include
  public static int squareRoot(int k) {
    long left = 0, right = k;
    // Candidate interval [left : right] where everything before left has
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

  public static void main(String[] args) {
    assert(squareRoot(0) == 0);
    assert(squareRoot(1) == 1);
    assert(squareRoot(121) == 11);
    assert(squareRoot(64) == 8);
    assert(squareRoot(300) == 17);
    int x;
    if (args.length == 1) {
      x = Integer.parseInt(args[0]);
    } else {
      Random r = new Random();
      for (int times = 0; times < 100000; ++times) {
        x = r.nextInt(Integer.MAX_VALUE);
        int result[] = new int[2];
        System.out.println("x is " + x);
        System.out.println((result[0] = squareRoot(x)) + " " +
                           (result[1] = (int)Math.sqrt(x)));
        assert(result[0] == result[1]);
      }
    }
    x = 2147395599;
    System.out.println(squareRoot(x));
  }
}
