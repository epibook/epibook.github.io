package com.epi;

import java.util.Arrays;
import java.util.Random;

import static java.lang.Math.ceil;

public class HouseMajority {
  // @include
  public static double houseMajority(double[] prob, int n) {
    // Initializes DP table.
    double[][] P = new double[n + 1][n + 1];
    for (double[] element : P) {
      Arrays.fill(element, -1.0);
    }

    // Accumulates the probabilities of majority cases.
    double probSum = 0.0;
    for (int r = (int)ceil(0.5 * n); r <= n; ++r) {
      probSum += houseMajorityHelper(prob, r, n, P);
    }
    return probSum;
  }

  // prob is the probability that each Republican wins.
  // r is the number of Republicans wins, and n is the number of elections.
  private static double houseMajorityHelper(double[] prob, int r, int n,
                                            double[][] P) {
    if (r > n) {
      return 0.0; // Base case: not enough Republicans.
    } else if (r == 0 && n == 0) {
      return 1.0; // Base case.
    } else if (r < 0) {
      return 0.0;
    }

    if (P[r][n] == -1.0) {
      P[r][n] = houseMajorityHelper(prob, r - 1, n - 1, P) * prob[n - 1] +
                houseMajorityHelper(prob, r, n - 1, P) * (1.0 - prob[n - 1]);
    }
    return P[r][n];
  }
  // @exclude

  private static void printVector(double[] prob) {
    Arrays.sort(prob);
    for (int i = 0; i < prob.length; ++i) {
      System.out.println(String.format("%s:%s:%s", i, 100 * prob[i],
                                       ((i + 1) % 10 == 0) ? "\n" : " "));
    }
    System.out.println();
  }

  public static void main(String[] args) {
    Random gen = new Random();
    int n = 435;
    double[] prob = new double[n + 1];
    prob[0] = 0.0;
    for (int i = 1; i < n + 1; ++i) {
      prob[i] = gen.nextDouble();
    }
    printVector(prob);
    double ans = houseMajority(prob, n);
    assert 0.0 <= ans&& ans <= 1.0;
    System.out.println();
  }
}
