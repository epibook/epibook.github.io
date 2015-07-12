// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class NonUniformRandomNumberGeneration {
  // @include
  public static int nonuniformRandomNumberGeneration(List<Integer> values,
                                                     List<Double> probabilities) {
    List<Double> prefixSumOfProbabilities = new ArrayList<>();
    prefixSumOfProbabilities.add(0.0);
    // Creating the endpoints for the intervals corresponding to the
    // probabilities.
    for (double p : probabilities) {
      prefixSumOfProbabilities.add(
          prefixSumOfProbabilities.get(prefixSumOfProbabilities.size() - 1) + p);
    }

    Random uniform01 = new Random();
    // Find the index of the interval that uniform01 lies in.
    int intervalIdx =
        Math.abs(Collections.binarySearch(
            prefixSumOfProbabilities, uniform01.nextDouble(), new Compare())) -
        2;
    return values.get(intervalIdx);
  }

  private static class Compare implements Comparator<Double> {
    public int compare(Double x, Double y) { return x > y ? 1 : -1; }
  }
  // @exclude

  public static void main(String[] args) {
    Random gen = new Random();
    int n;
    if (args.length == 1) {
      n = Integer.valueOf(args[0]);
    } else {
      n = gen.nextInt(50) + 1;
    }
    List<Integer> T = new ArrayList<>(n);
    for (int i = 0; i < n; ++i) {
      T.add(i);
    }
    List<Double> P = new ArrayList<>();
    double fullProb = 1.0;
    for (int i = 0; i < n - 1; ++i) {
      double pi = gen.nextDouble() * fullProb;
      P.add(pi);
      fullProb -= pi;
    }
    P.add(fullProb);

    System.out.println(T);

    System.out.println(P);

    System.out.println(nonuniformRandomNumberGeneration(T, P));
    // Test. Perform the nonuniform random number generation for n * kTimes
    // times
    // and calculate the distribution of each bucket.
    int kTimes = 100000;
    int[] counts = new int[n];
    for (int i = 0; i < n * kTimes; ++i) {
      int t = nonuniformRandomNumberGeneration(T, P);
      ++counts[t];
    }
    for (int i = 0; i < n; ++i) {
      System.out.println((double)(counts[i]) / (n * kTimes) + " " + P.get(i));
      assert Math.abs(((double)counts[i]) / (n * kTimes) - P.get(i)) < 0.01;
    }
  }
}
