package com.epi;

import java.util.Random;

public class SquareRootInt {
  // @include
  public static int squareRoot(int x) {
    if (x <= 1) {
      return x;
    }

    long left = 0, right = x;
    while (left + 1 < right) {
      long mid = left + ((right - left) / 2);
      long squareM = mid * mid;
      if (squareM == x) {
        return (int) mid;
      } else if (squareM < x) {
        left = mid;
      } else { // squareM > x
        right = mid - 1;
      }
    }
    if (right * right <= x) {
      return (int) right;
    }
    return (int) left;
  }
  // @exclude

  public static void main(String[] args) {
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
            + (result[1] = (int) Math.sqrt(x)));
        assert (result[0] == result[1]);
      }
    }
    x = 2147395599;
    System.out.println(squareRoot(x));
  }
}
