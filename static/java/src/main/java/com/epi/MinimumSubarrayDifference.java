package com.epi;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class MinimumSubarrayDifference {
  // @include
  public static int minimizeDifference(List<Integer> A) {
    int sum = 0;
    for (int a : A) {
      sum += a;
    }

    Set<Integer> isOk = new HashSet<>();
    isOk.add(0);
    for (int item : A) {
      for (int v = sum / 2; v >= item; --v) {
        if (isOk.contains(v - item)) {
          isOk.add(v);
        }
      }
    }

    // Finds the first i from middle where isOk[i] == true.
    for (int i = sum / 2; i > 0; --i) {
      if (isOk.contains(i)) {
        return (sum - i) - i;
      }
    }
    return sum; // One thief takes all.
  }
  // @exclude

  public static void main(String[] args) {
    Random r = new Random();
    int n;
    List<Integer> A = new ArrayList<>();
    if (args.length == 1) {
      n = Integer.parseInt(args[0]);
    } else if (args.length == 0) {
      n = r.nextInt(50);
    } else {
      for (String arg : args) {
        A.add(Integer.parseInt(arg));
      }
      n = 0;
    }
    for (int i = 0; i < n; ++i) {
      A.add(r.nextInt(100));
    }
    System.out.println(A);
    int sum = 0;
    for (int a : A) {
      sum += a;
    }
    System.out.println(sum);
    System.out.println("minimum difference = " + minimizeDifference(A));
  }
}
