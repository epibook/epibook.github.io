package com.epi;

import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class Division {
  public static long divideXbyYBinSearch(long x, long y) {
    if (x < y) {
      return 0;
    }

    int powerLeft = 0;
    int powerRight = 8 << 3;
    int powerMid = -1;
    while (powerLeft < powerRight) {
      int tmp = powerMid;
      powerMid = powerLeft + ((powerRight - powerLeft) >> 1);
      if (tmp == powerMid) {
        break;
      }
      long yshift = y << powerMid;
      if ((yshift >> powerMid) != y) {
        // yshift overflowed, use a smaller shift.
        powerRight = powerMid;
        continue;
      }
      if ((y << powerMid) > x) {
        powerRight = powerMid;
      } else if ((y << powerMid) < x) {
        powerLeft = powerMid;
      } else {
        return (1L << powerMid);
      }
    }
    long part = 1L << powerLeft;
    return part | divideXbyYBinSearch(x - (y << powerLeft), y);
  }

  // @include
  public static long divideXbyY(long x, long y) {
    long res = 0;
    while (x >= y) {
      int power = 1;
      // Checks (y << power) >= (y << (power - 1)) to prevent potential
      // overflow of unsigned.
      while ((y << power) >= (y << (power - 1)) && (y << power) <= x) {
        ++power;
      }

      res += 1L << (power - 1);
      x -= y << (power - 1);
    }
    return res;
  }

  // @exclude

  private static void simpleTest() {
    assert (divideXbyY(64, 1) == 64);
    assert (divideXbyY(64, 2) == 32);
    assert (divideXbyY(64, 3) == 21);
    assert (divideXbyY(64, 4) == 16);
    assert (divideXbyY(64, 5) == 12);
    assert (divideXbyY(65, 2) == 32);
    assert (divideXbyY(2600540749L, 2590366779L) == 1);
    assert (divideXbyYBinSearch(4, 2) == 2);
    assert (divideXbyYBinSearch(64, 1) == 64);
    assert (divideXbyYBinSearch(64, 2) == 32);
    assert (divideXbyYBinSearch(64, 3) == 21);
    assert (divideXbyYBinSearch(64, 4) == 16);
    assert (divideXbyYBinSearch(64, 5) == 12);
    assert (divideXbyYBinSearch(65, 2) == 32);
    assert (divideXbyYBinSearch(9444, 4714) == 2);
    assert (divideXbyYBinSearch(8186, 19) == 430);
    assert (divideXbyYBinSearch(8186, 19) == 430);
  }

  public static void main(String[] args) {
    simpleTest();
    if (args.length == 2) {
      long x = Long.parseLong(args[0]);
      long y = Long.parseLong(args[1]);
      assert (x / y == divideXbyY(x, y));
      assert (x / y == divideXbyYBinSearch(x, y));
    } else {
      Random r = new Random();
      for (int times = 0; times < 100000; ++times) {
        long x = r.nextInt(Integer.MAX_VALUE), y = r.nextInt(Integer.MAX_VALUE);
        y = (y == 0) ? 1 : y; // ensure no divide by 0.
        System.out.println("times = " + times + ", x = " + x + ", y = " + y);
        System.out.println("first = " + x / y + ", second = "
            + divideXbyY(x, y));
        assert (x / y == divideXbyY(x, y));
        assert (x / y == divideXbyYBinSearch(x, y));
      }
    }

  }
}
