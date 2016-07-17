package com.epi;

import java.util.Random;

public class Doors {
  // @include
  static boolean isDoorOpen(int i) {
    double sqrtI = Math.sqrt(i);
    int floorSqrtI = (int)Math.floor(sqrtI);
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
      n = Integer.parseInt(args[0]);
    } else {
      n = gen.nextInt(1000) + 1;
    }
    checkAnswer(n);
  }
}
