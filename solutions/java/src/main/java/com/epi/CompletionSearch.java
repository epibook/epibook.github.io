package com.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class CompletionSearch {
  private static int lowerBound(List<Double> a, double val) {
    for (int i = 0; i < a.size(); i++) {
      if (a.get(i) > val) {
        return i;
      }
    }
    return -1;
  }

  // @include
  public static double completionSearch(List<Double> A, double budget) {
    Collections.sort(A);
    // Calculates the prefix sum for A.
    List<Double> prefixSum = new ArrayList<>();
    double val = 0;
    for (Double a : A) {
      val += a;
      prefixSum.add(val);
    }
    // costs[i] represents the total payroll if the cap is A[i].
    List<Double> costs = new ArrayList<>();
    for (int i = 0; i < prefixSum.size(); ++i) {
      costs.add(prefixSum.get(i) + (A.size() - i - 1) * A.get(i));
    }

    int lower = lowerBound(costs, budget);

    if (lower == -1) {
      return -1.0; // No solution since budget is too large.
    }

    if (lower == 0) {
      return budget / A.size();
    }
    int idx = lower - 1;
    return A.get(idx) + (budget - costs.get(idx)) / (A.size() - idx - 1);
  }
  // @exclude

  public static void main(String[] args) {
    Random r = new Random();
    for (int times = 0; times < 10000; ++times) {
      int n;
      List<Double> A = new ArrayList<>();
      double tar;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
        tar = r.nextInt(100000);
      } else if (args.length == 2) {
        n = Integer.parseInt(args[0]);
        tar = Integer.parseInt(args[1]);
      } else {
        n = r.nextInt(1000) + 1;
        tar = r.nextInt(100000);
      }
      for (int i = 0; i < n; ++i) {
        A.add((double) r.nextInt(10000));
      }
      System.out.println("A = " + A.toString());
      System.out.println("tar = " + tar);
      double ret = completionSearch(A, tar);
      if (ret != -1.0) {
        System.out.println("ret = " + ret);
        double sum = 0.0;
        for (int i = 0; i < n; ++i) {
          if (A.get(i) > ret) {
            sum += ret;
          } else {
            sum += A.get(i);
          }
        }
        tar -= sum;
        System.out.println("sum = " + sum);
        assert (tar < 1.0e-8);
      }
    }
  }
}
