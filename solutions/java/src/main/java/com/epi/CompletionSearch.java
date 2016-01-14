package com.epi;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CompletionSearch {
  // @include
  public static double findSalaryCap(double targetPayroll,
                                     List<Double> currentSalaries) {
    Collections.sort(currentSalaries);
    double unadjustedSalarySum = 0;
    for (int i = 0; i < currentSalaries.size(); ++i) {
      final double adjustedSalarySum
          = currentSalaries.get(i) * (currentSalaries.size() - i);
      if (unadjustedSalarySum + adjustedSalarySum >= targetPayroll) {
        return (targetPayroll - unadjustedSalarySum)
            / (currentSalaries.size() - i);
      }
      unadjustedSalarySum += currentSalaries.get(i);
    }
    // No solution, since targetPayroll > existing payroll.
    return -1.0;
  }
  // @exclude

  private static void smallTest() {
    List<Double> A = Arrays.asList(20.0, 30.0, 40.0, 90.0, 100.0);
    double T = 210;
    assert(findSalaryCap(T, A) == 60);
    T = 280;
    assert(findSalaryCap(T, A) == 100);
    T = 50;
    assert(findSalaryCap(T, A) == 10);
    T = 281;
    assert(findSalaryCap(T, A) == -1.0);
  }

  public static void main(String[] args) {
    smallTest();
    Random r = new Random();
    for (int times = 0; times < 10000; ++times) {
      int n;
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
      List<Double> A = new ArrayList<>(n);
      for (int i = 0; i < n; ++i) {
        A.add((double)r.nextInt(10000));
      }
      System.out.println("tar = " + tar);
      double ret = findSalaryCap(tar, A);
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
        assert(tar < 1.0e-8);
      }
    }
  }
}
