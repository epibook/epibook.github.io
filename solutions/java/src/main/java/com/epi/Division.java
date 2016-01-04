package com.epi;

import java.util.Random;

public class Division {
  // Alternative solution.
  public static long divideBinSearch(long x, long y) {
    if (x < y) {
      return 0;
    }

    int powerLeft = 0;
    int powerRight = 8 << 3;
    int powerMid = -1;
    while (powerLeft < powerRight) {
      int tmp = powerMid;
      powerMid = powerLeft + ((powerRight - powerLeft) / 2);
      if (tmp == powerMid) {
        break;
      }
      long yshift = y << powerMid;
      if ((yshift >>> powerMid) != y) {
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
    return part | divideBinSearch(x - (y << powerLeft), y);
  }

  // @include
  public static long divide(long x, long y) {
    long result = 0;
    int power = 32;
    long yPower = y << power;
    while (x >= y) {
      while (yPower > x) {
        // clang-format off
        yPower >>>= 1;
        // clang-format on
        --power;
      }

      result += 1L << power;
      x -= yPower;
    }
    return result;
  }
  // @exclude

  private static void simpleTest() {
    assert(divide(64, 1) == 64);
    assert(divide(64, 2) == 32);
    assert(divide(64, 3) == 21);
    assert(divide(64, 4) == 16);
    assert(divide(64, 5) == 12);
    assert(divide(65, 2) == 32);
    assert(divide(2600540749L, 2147483647L) == 1);
    assert(divideBinSearch(4, 2) == 2);
    assert(divideBinSearch(64, 1) == 64);
    assert(divideBinSearch(64, 2) == 32);
    assert(divideBinSearch(64, 3) == 21);
    assert(divideBinSearch(64, 4) == 16);
    assert(divideBinSearch(64, 5) == 12);
    assert(divideBinSearch(65, 2) == 32);
    assert(divideBinSearch(9444, 4714) == 2);
    assert(divideBinSearch(8186, 19) == 430);
    assert(divideBinSearch(8186, 19) == 430);
  }

  public static void main(String[] args) {
    simpleTest();
    if (args.length == 2) {
      long x = Long.parseLong(args[0]);
      long y = Long.parseLong(args[1]);
      assert(x / y == divide(x, y));
      assert(x / y == divideBinSearch(x, y));
    } else {
      Random r = new Random();
      for (int times = 0; times < 100000; ++times) {
        long x = r.nextInt(Integer.MAX_VALUE), y = r.nextInt(Integer.MAX_VALUE);
        y = (y == 0) ? 1 : y; // ensure no divide by 0.
        System.out.println("times = " + times + ", x = " + x + ", y = " + y);
        System.out.println("first = " + x / y + ", second = " + divide(x, y));
        assert(x / y == divide(x, y));
        assert(x / y == divideBinSearch(x, y));
      }
    }
  }
}
