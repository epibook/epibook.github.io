package com.epi;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class HouseMajority {
  // @include
  public static double houseMajority(List<Double> prob, int n) {
    // Initializes DP table.
    List<List<Double>> P = new ArrayList<>(n + 1);
    for (int i = 0; i < n + 1; ++i) {
      P.add(new ArrayList(Collections.nCopies(n + 1, -1.0)));
    }

    // Accumulates the probabilities of majority cases.
    double probSum = 0.0;
    for (int r = (int)Math.ceil(0.5 * n); r <= n; ++r) {
      probSum += houseMajorityHelper(prob, r, n, P);
    }
    return probSum;
  }

  // prob is the probability that each Republican wins.
  // r is the number of Republicans wins, and n is the number of elections.
  private static double houseMajorityHelper(List<Double> prob, int r, int n,
                                            List<List<Double>> P) {
    if (r > n) {
      return 0.0; // Base case: not enough Republicans.
    } else if (r == 0 && n == 0) {
      return 1.0; // Base case.
    } else if (r < 0) {
      return 0.0;
    }

    if (P.get(r).get(n) == -1.0) {
      P.get(r)
          .set(n, houseMajorityHelper(prob, r - 1, n - 1, P) * prob.get(n - 1)
                      + houseMajorityHelper(prob, r, n - 1, P)
                            * (1.0 - prob.get(n - 1)));
    }
    return P.get(r).get(n);
  }
  // @exclude

  private static void printVector(List<Double> prob) {
    Collections.sort(prob);
    for (int i = 0; i < prob.size(); ++i) {
      System.out.println(String.format("%s:%s:%s", i, 100 * prob.get(i),
                                       ((i + 1) % 10 == 0) ? "\n" : " "));
    }
    System.out.println();
  }

  public static void main(String[] args) {
    Random gen = new Random();
    int n = 435;
    List<Double> prob = new ArrayList<>(n + 1);
    prob.add(0.0);
    for (int i = 1; i < n + 1; ++i) {
      prob.add(gen.nextDouble());
    }
    printVector(prob);
    double ans = houseMajority(prob, n);
    assert 0.0 <= ans&& ans <= 1.0;
    System.out.println();
  }
}
