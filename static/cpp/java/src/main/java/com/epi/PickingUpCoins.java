package com.epi;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PickingUpCoins {
  // @include
  public static int pickUpCoins(List<Integer> coins) {
    return computeMaximumRevenueForRange(coins, 0, coins.size() - 1,
                                         new int[coins.size()][coins.size()]);
  }

  private static int computeMaximumRevenueForRange(
      List<Integer> coins, int a, int b, int[][] maximumRevenueForRange) {
    if (a > b) {
      // No coins left.
      return 0;
    }

    if (maximumRevenueForRange[a][b] == 0) {
      int maximumRevenueA
          = coins.get(a)
            + Math.min(computeMaximumRevenueForRange(coins, a + 2, b,
                                                     maximumRevenueForRange),
                       computeMaximumRevenueForRange(coins, a + 1, b - 1,
                                                     maximumRevenueForRange));
      int maximumRevenueB
          = coins.get(b)
            + Math.min(computeMaximumRevenueForRange(coins, a + 1, b - 1,
                                                     maximumRevenueForRange),
                       computeMaximumRevenueForRange(coins, a, b - 2,
                                                     maximumRevenueForRange));
      maximumRevenueForRange[a][b] = Math.max(maximumRevenueA, maximumRevenueB);
    }
    return maximumRevenueForRange[a][b];
  }
  // @exclude

  private static int maximumRevenueAlternative(List<Integer> coins) {
    List<Integer> prefixSum = new ArrayList<>(coins);
    for (int i = 0; i < coins.size(); ++i) {
      prefixSum.set(i, prefixSum.get(i) + (i > 0 ? prefixSum.get(i - 1) : 0));
    }
    List<List<Integer>> maximumRevenueForRange = new ArrayList<>(coins.size());
    for (int i = 0; i < coins.size(); ++i) {
      maximumRevenueForRange.add(
          new ArrayList(Collections.nCopies(coins.size(), -1)));
    }
    return maximumRevenueAlternativeHelper(coins, 0, coins.size() - 1,
                                           prefixSum, maximumRevenueForRange);
  }

  private static int maximumRevenueAlternativeHelper(
      List<Integer> coins, int a, int b, List<Integer> prefixSum,
      List<List<Integer>> maximumRevenueForRange) {
    if (a > b) {
      return 0;
    } else if (a == b) {
      return coins.get(a);
    }

    if (maximumRevenueForRange.get(a).get(b) == -1) {
      maximumRevenueForRange.get(a).set(
          b,
          Math.max(
              coins.get(a) + prefixSum.get(b)
                  - (a + 1 > 0 ? prefixSum.get(a) : 0)
                  - maximumRevenueAlternativeHelper(coins, a + 1, b, prefixSum,
                                                    maximumRevenueForRange),
              coins.get(b) + prefixSum.get(b - 1)
                  - (a > 0 ? prefixSum.get(a - 1) : 0)
                  - maximumRevenueAlternativeHelper(coins, a, b - 1, prefixSum,
                                                    maximumRevenueForRange)));
    }
    return maximumRevenueForRange.get(a).get(b);
  }

  private static int greedy(List<Integer> coins) {
    return greedyHelper(coins, 0, coins.size() - 1);
  }

  private static int greedyHelper(List<Integer> coins, int start, int end) {
    if (start > end) {
      return 0;
    }

    int gain = 0;
    if (coins.get(start) > coins.get(end)) {
      gain = coins.get(start);
      if (coins.get(start + 1) > coins.get(end)) {
        gain += greedyHelper(coins, start + 2, end);
      } else {
        gain += greedyHelper(coins, start + 1, end - 1);
      }
    } else {
      gain = coins.get(end);
      if (coins.get(start) > coins.get(end - 1)) {
        gain += greedyHelper(coins, start + 1, end - 1);
      } else {
        gain += greedyHelper(coins, start, end - 2);
      }
    }
    return gain;
  }

  private static void simpleTest() {
    List<Integer> coins = Arrays.asList(25, 5, 10, 5, 10, 5, 10, 25, 1, 25, 1,
                                        25, 1, 25, 5, 10);
    assert(140 == pickUpCoins(coins));
    assert(maximumRevenueAlternative(coins) == pickUpCoins(coins));
    assert(120 == greedy(coins));
  }

  public static void main(String[] args) {
    simpleTest();
    Random r = new Random();
    List<Integer> coins = new ArrayList<>();
    if (args.length >= 1) {
      for (int i = 1; i < args.length; ++i) {
        coins.add(Integer.parseInt(args[i]));
      }
    } else {
      int size = r.nextInt(1000) + 1;
      for (int i = 0; i < size; ++i) {
        coins.add(r.nextInt(100));
      }
    }
    System.out.println(coins);
    System.out.println(pickUpCoins(coins));
    assert(pickUpCoins(coins) == maximumRevenueAlternative(coins));
  }
}
