package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CompletionSearch {
  // @include
  public static double findSalaryCap(double targetPayroll,
                                     Double[] currentSalaries) {
    Arrays.sort(currentSalaries);
    double unadjustedSalarySum = 0;
    double adjustedSalarySum = currentSalaries[0] * currentSalaries.length;
    for (int i = 0; i < currentSalaries.length; ++i) {
      unadjustedSalarySum += currentSalaries[i];
      adjustedSalarySum = currentSalaries[i] * (currentSalaries.length - (i + 1));
      if (unadjustedSalarySum + adjustedSalarySum >= targetPayroll) {
        return (targetPayroll - unadjustedSalarySum + currentSalaries[i]) /
            (currentSalaries.length - i);
      }
    }
    // No solution, since targetPayroll > existing payroll.
    return -1.0;
  }
  // @exclude

  private static void smallTest() {
    Double[] A = {20.0, 30.0, 40.0, 90.0, 100.0};
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
      Double[] A = new Double[n];
      for (int i = 0; i < n; ++i) {
        A[i] = (double)r.nextInt(10000);
      }
      System.out.println("tar = " + tar);
      double ret = findSalaryCap(tar, A);
      if (ret != -1.0) {
        System.out.println("ret = " + ret);
        double sum = 0.0;
        for (int i = 0; i < n; ++i) {
          if (A[i] > ret) {
            sum += ret;
          } else {
            sum += A[i];
          }
        }
        tar -= sum;
        System.out.println("sum = " + sum);
        assert(tar < 1.0e-8);
      }
    }
  }
}
