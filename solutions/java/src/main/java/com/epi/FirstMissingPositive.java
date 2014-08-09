package com.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class FirstMissingPositive {
  private static int checkAns(List<Integer> A) {
    Collections.sort(A);
    int target = 1;
    for (int a : A) {
      if (a > 0) {
        if (a == target) {
          ++target;
        } else if (a > target) {
          return target;
        }
      }
    }
    return target;
  }

  // @include
  public static int findFirstMissingPositive(List<Integer> A) {
    int i = 0;
    while (i < A.size()) {
      if (A.get(i) > 0 && A.get(i) <= A.size()
          && !A.get(A.get(i) - 1).equals(A.get(i)) && !A.get(i).equals(i + 1)) {
        Collections.swap(A, i, A.get(i) - 1);
      } else {
        ++i;
      }
    }

    for (i = 0; i < A.size(); ++i) {
      if (A.get(i) != i + 1) {
        return i + 1;
      }
    }
    return A.size() + 1;
  }
  // @exclude

  public static void main(String[] args) {
    Random r = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = r.nextInt(500001);
      }
      List<Integer> A = new ArrayList<>();
      for (int i = 0; i < n; i++) {
        A.add(r.nextInt(n + 1));
      }
      System.out.println("n = " + n);
      assert (findFirstMissingPositive(A) == checkAns(A));
    }
  }
}
