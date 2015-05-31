package com.epi;

import java.util.Arrays;
import java.util.Random;

public class BonusImproved {
  private static void checkAns(int[] ratings, int[] C) {
    for (int i = 0; i < ratings.length; ++i) {
      if (i > 0) {
        assert((ratings[i] > ratings[i - 1] && C[i] > C[i - 1]) ||
               (ratings[i] < ratings[i - 1] && C[i] < C[i - 1]) ||
               ratings[i] == ratings[i - 1]);
      }
      if (i + 1 < ratings.length) {
        assert((ratings[i] > ratings[i + 1] && C[i] > C[i + 1]) ||
               (ratings[i] < ratings[i + 1] && C[i] < C[i + 1]) ||
               ratings[i] == ratings[i + 1]);
      }
    }
  }

  // @include
  public static int[] calculateBonus(int[] productivity) {
    // Initially assigns one ticket to everyone.
    int[] tickets = new int[productivity.length];
    Arrays.fill(tickets, 1);
    // From left to right.
    for (int i = 1; i < productivity.length; ++i) {
      if (productivity[i] > productivity[i - 1]) {
        tickets[i] = tickets[i - 1] + 1;
      }
    }
    // From right to left.
    for (int i = productivity.length - 2; i >= 0; --i) {
      if (productivity[i] > productivity[i + 1]) {
        tickets[i] = Math.max(tickets[i], tickets[i + 1] + 1);
      }
    }
    return tickets;
  }
  // @exclude

  private static void smallTest() {
    int[] A = new int[] {1, 2, 2};
    int[] goldenA = new int[] {1, 2, 1};
    assert(Arrays.equals(calculateBonus(A), goldenA));
    A = new int[] {1, 2, 3, 2, 1};
    goldenA = new int[] {1, 2, 3, 2, 1};
    assert(Arrays.equals(calculateBonus(A), goldenA));
    A = new int[] {300, 400, 500, 200};
    goldenA = new int[] {1, 2, 3, 1};
    assert(Arrays.equals(calculateBonus(A), goldenA));
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
