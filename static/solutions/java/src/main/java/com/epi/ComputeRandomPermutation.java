package com.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ComputeRandomPermutation {
  // @include
  public static List<Integer> computeRandomPermutation(int n) {
    List<Integer> permutation = new ArrayList<>(n);
    for (int i = 0; i < n; ++i) {
      permutation.add(i);
    }
    OfflineSampling.randomSampling(permutation.size(), permutation);
    return permutation;
  }
  // @exclude

  public static void main(String[] args) {
    int n;
    Random gen = new Random();
    if (args.length == 1) {
      n = Integer.parseInt(args[0]);
    } else {
      n = gen.nextInt(1000000) + 1;
    }

    System.out.println(n);
    List<Integer> result = computeRandomPermutation(n);
    Collections.sort(result);
    for (int i = 0; i < n; ++i) {
      assert(i == result.get(i));
    }
  }
}
