// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Permutations {
  // @include
  public static List<List<Integer>> permutations(List<Integer> A) {
    List<List<Integer>> result = new ArrayList<>();
    // Generate the first permutation in dictionary order.
    Collections.sort(A);
    do {
      result.add(new ArrayList<>(A));
      A = NextPermutation.nextPermutation(A);
    } while (!A.isEmpty());
    return result;
  }
  // @exclude

  private static void smallTest() {
    List<Integer> A = Arrays.asList(0, 1, 2);
    List<List<Integer>> result = permutations(A);
    assert(result.size() == 6);
    List<List<Integer>> goldenResult = Arrays.asList(
        Arrays.asList(0, 1, 2), Arrays.asList(0, 2, 1), Arrays.asList(1, 0, 2),
        Arrays.asList(1, 2, 0), Arrays.asList(2, 0, 1), Arrays.asList(2, 1, 0));
    assert(result.equals(goldenResult));
  }

  public static void main(String[] args) {
    smallTest();
    Random r = new Random();
    int n;
    if (args.length == 1) {
      n = Integer.parseInt(args[0]);
    } else {
      n = r.nextInt(10) + 1;
    }
    List<Integer> A = new ArrayList<>(n);
    int val = 0;
    for (int i = 0; i < n; i++) {
      A.add(val++);
    }
    List<List<Integer>> result = permutations(A);
    System.out.println("n = " + n);
    for (List<Integer> vec : result) {
      System.out.println(vec);
    }
  }
}
