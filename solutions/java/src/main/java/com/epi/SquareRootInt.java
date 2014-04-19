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
      long mid = left + ((right - left) >> 1);
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
        int res[] = new int[2];
        System.out.println("x is " + x);
        System.out.println((res[0] = squareRoot(x)) + " "
            + (res[1] = (int) Math.sqrt(x)));
        assert (res[0] == res[1]);
      }
    }
    x = 2147395599;
    System.out.println(squareRoot(x));
  }
}
