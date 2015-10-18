package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ScorePermutation {
  // @include
  public static long countPermutations(int k, List<Integer> scoreWays) {
    long[] permutations = new long[k + 1];
    permutations[0] = 1; // One way to reach 0.
    for (int i = 0; i <= k; ++i) {
      for (int score : scoreWays) {
        if (i >= score) {
          permutations[i] += permutations[i - score];
        }
      }
    }
    return permutations[k];
  }
  // @exclude

  private static void simpleTest() {
    int k = 12;
    List<Integer> scoreWays = Arrays.asList(2, 3, 7);
    assert(18 == countPermutations(k, scoreWays));
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
    System.out.println(countPermutations(k, scoreWays));
  }
}
