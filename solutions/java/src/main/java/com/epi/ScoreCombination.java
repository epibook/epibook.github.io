package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class ScoreCombination {
  // @include
  public static long countCombinations(int k, List<Integer> scoreWays) {
    long[] combinations = new long[k + 1];
    combinations[0] = 1; // One way to reach 0.
    for (int score : scoreWays) {
      for (int j = score; j <= k; ++j) {
        combinations[j] += combinations[j - score];
      }
    }
    return combinations[k];
  }
  // @exclude

  private static void simpleTest() {
    int k = 12;
    List<Integer> scoreWays = Arrays.asList(2, 3, 7);
    assert(4 == countCombinations(k, scoreWays));
  }

  public static void main(String[] args) {
    simpleTest();
    Random r = new Random();
    int k;
    List<Integer> scoreWays = new ArrayList<>();
    if (args.length == 0) {
      k = r.nextInt(1000);
      int size = r.nextInt(50) + 1;
      for (int i = 0; i < size; ++i) {
        scoreWays.add(r.nextInt(1000) + 1);
      }
    } else if (args.length == 2) {
      k = Integer.parseInt(args[0]);
      int size = Integer.parseInt(args[1]);
      for (int i = 0; i < size; ++i) {
        scoreWays.add(r.nextInt(1000) + 1);
      }
    } else {
      k = Integer.parseInt(args[0]);
      for (int i = 2; i < args.length; ++i) {
        scoreWays.add(Integer.parseInt(args[i]));
      }
    }
    System.out.println(countCombinations(k, scoreWays));
  }
}
