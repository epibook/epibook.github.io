package com.epi;

import java.util.Arrays;
import java.util.Random;

public class BonusImproved {
  private static void checkAns(int[] ratings, int[] C) {
    for (int i = 0; i < ratings.length; ++i) {
      if (i > 0) {
        assert ((ratings[i] > ratings[i - 1] && C[i] > C[i - 1])
            || (ratings[i] < ratings[i - 1] && C[i] < C[i - 1]) || ratings[i] == ratings[i - 1]);
      }
      if (i + 1 < ratings.length) {
        assert ((ratings[i] > ratings[i + 1] && C[i] > C[i + 1])
            || (ratings[i] < ratings[i + 1] && C[i] < C[i + 1]) || ratings[i] == ratings[i + 1]);
      }
    }
  }

  // @include
  public static int[] calculateBonus(int[] ratings) {
    // T stores the amount of bonus each one is assigned.
    int[] T = new int[ratings.length];
    Arrays.fill(T, 1);
    // From left to right.
    for (int i = 1; i < ratings.length; ++i) {
      if (ratings[i] > ratings[i - 1]) {
        T[i] = T[i - 1] + 1;
      }
    }
    // From right to left.
    for (int i = ratings.length - 2; i >= 0; --i) {
      if (ratings[i] > ratings[i + 1]) {
        T[i] = Math.max(T[i], T[i + 1] + 1);
      }
    }
    return T;
  }

  // @exclude

  private static void smallTest() {
    int[] A = new int[]{1, 2, 2};
    int[] goldenA = new int[]{1, 2, 1};
    assert (Arrays.equals(calculateBonus(A), goldenA));
    A = new int[]{1, 2, 3, 2, 1};
    goldenA = new int[]{1, 2, 3, 2, 1};
    assert (Arrays.equals(calculateBonus(A), goldenA));
  }

  public static void main(String[] args) {
    smallTest();
    Random r = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = r.nextInt(1000) + 1;
      }
      int[] ratings = new int[n];
      for (int i = 0; i < n; ++i) {
        ratings[i] = r.nextInt(10000) + 1;
      }
      int[] T = calculateBonus(ratings);
      checkAns(ratings, T);
    }
  }
}
