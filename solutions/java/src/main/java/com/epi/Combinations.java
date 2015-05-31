package com.epi;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Combinations {
  // @include
  public static List<List<Integer>> combinations(int n, int k) {
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> partialCombination = new ArrayList<>();
    directedCombinations(n, k, 1, partialCombination, result);
    return result;
  }

  private static void directedCombinations(int n, int k, int offset,
                                           List<Integer> partialCombination,
                                           List<List<Integer>> result) {
    if (partialCombination.size() == k) {
      result.add(new ArrayList<>(partialCombination));
      return;
    }

    // Generate remaining combinations over {offset, ..., n - 1} of size
    // kNumRemaining.
    final int NUM_REMAINING = k - partialCombination.size();
    for (int i = offset; i <= n && NUM_REMAINING <= n - i + 1; ++i) {
      partialCombination.add(i);
      directedCombinations(n, k, i + 1, partialCombination, result);
      partialCombination.remove(partialCombination.size() - 1);
    }
  }
  // @exclude

  private static void smallTest() {
    List<List<Integer>> result = combinations(4, 2);
    List<List<Integer>> goldenResult = Arrays.asList(
        Arrays.asList(1, 2), Arrays.asList(1, 3), Arrays.asList(1, 4),
        Arrays.asList(2, 3), Arrays.asList(2, 4), Arrays.asList(3, 4));
    assert(result.equals(goldenResult));
  }

  public static void main(String[] args) {
    smallTest();
    Random r = new Random();
    int n, k;
    if (args.length == 2) {
      n = Integer.parseInt(args[0]);
      k = Integer.parseInt(args[1]);
    } else {
      n = r.nextInt(10) + 1;
      k = r.nextInt(n + 1);
    }
    List<List<Integer>> res = combinations(n, k);
    System.out.println("n = " + n + ", k = " + k);
    System.out.println(res);
  }
}
