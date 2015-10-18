package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class ScoreCombination {
  // @include
  public static long countCombinations(int totalScore,
                                       List<Integer> scoreWays) {
    long[] combinations = new long[totalScore + 1];
    combinations[0] = 1; // One way to reach 0.
    for (int score : scoreWays) {
      for (int j = score; j <= totalScore; ++j) {
        combinations[j] += combinations[j - score];
      }
    }
    return combinations[totalScore];
  }
  // @exclude

  private static void simpleTest() {
    List<Integer> scoreWays = Arrays.asList(2, 3, 7);
    assert(4 == countCombinations(12, scoreWays));
    assert(1 == countCombinations(5, scoreWays));
    assert(3 == countCombinations(9, scoreWays));
  }

  public static void main(String[] args) {
    simpleTest();
    Random r = new Random();
    int k;
    Set<Integer> scoreWaysSet = new HashSet<>();
    if (args.length == 0) {
      k = r.nextInt(1000);
      int size = r.nextInt(50) + 1;
      for (int i = 0; i < size; ++i) {
        scoreWaysSet.add(r.nextInt(1000) + 1);
      }
    } else if (args.length == 2) {
      k = Integer.parseInt(args[0]);
      int size = Integer.parseInt(args[1]);
      for (int i = 0; i < size; ++i) {
        scoreWaysSet.add(r.nextInt(1000) + 1);
      }
    } else {
      k = Integer.parseInt(args[0]);
      for (int i = 2; i < args.length; ++i) {
        scoreWaysSet.add(Integer.parseInt(args[i]));
      }
    }
    List<Integer> scoreWays = new ArrayList<>(scoreWaysSet);
    System.out.println(countCombinations(k, scoreWays));
  }
}
