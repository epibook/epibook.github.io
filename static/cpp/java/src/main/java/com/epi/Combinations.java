package com.epi;

/*
  @slug
  combinations-1

  @summary
  There are a number of testing applications in which it is required
  to compute all subsets of a given size for a specified set.


  @problem
  Write a program which
  computes all size $k$ subsets of $\{1,2,\dots,n\}$, where $k$ and $n$ are
  program inputs.
  For example, if $k=2$ and $n=5$, then the result is the following:
  $\{ \{1,2\}, \{1,3\}, \{1,4\}, \{1,5\}, \{2,3\}, \{2,4\}, \{2,5\}, \{3,4\},
  \{3,5\}, \{4,5\} \}$

*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Combinations {
  // @include
  public static List<List<Integer>> combinations(int n, int k) {
    List<List<Integer>> result = new ArrayList<>();
    directedCombinations(n, k, 1, new ArrayList<Integer>(), result);
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
    // numRemaining.
    final int numRemaining = k - partialCombination.size();
    for (int i = offset; i <= n && numRemaining <= n - i + 1; ++i) {
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
