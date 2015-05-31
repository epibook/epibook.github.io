package com.epi;

import java.util.Random;

import static java.lang.Math.floor;
import static java.lang.Math.sqrt;

public class Doors {
  // @include
  static boolean isDoorOpen(int i) {
    double sqrtI = sqrt(i);
    int floorSqrtI = (int)floor(sqrtI);
    return floorSqrtI * floorSqrtI == i;
  }
  // @exclude

  static void checkAnswer(int n) {
    boolean[] doors = new boolean[n + 1]; // false means closed door.
    for (int i = 1; i <= n; ++i) {
      int start = 0;
      while (start + i <= n) {
        start += i;
        doors[start] = !doors[start];
      }
    }

    for (int i = 1; i <= n; ++i) {
      assert isDoorOpen(i) == doors[i];
    }
  }

  public static void main(String[] args) {
    Random gen = new Random();
    int n;
    if (args.length == 1) {
      n = Integer.valueOf(args[0]);
    } else {
      n = gen.nextInt(1000) + 1;
    }
    checkAnswer(n);
  }
}
