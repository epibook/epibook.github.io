package com.epi;

import com.epi.utils.Pair;

import java.util.Random;

public class FindMissingAndDuplicate {
  // @include
  // Returns Pair<int, int>(duplicate, missing).
  public static Pair<Integer, Integer> findDuplicateMissing(int[] A) {
    int sum = 0, squareSum = 0;
    for (int i = 0; i < A.length; ++i) {
      sum += i - A[i];
      squareSum += i * i - A[i] * A[i];
    }
    return new Pair<>((squareSum / sum - sum) / 2, (squareSum / sum + sum) / 2);
  }
  // @exclude

  public static void main(String[] args) {
    Random r = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = r.nextInt(9999) + 2;
      }
      int[] A = new int[n];
      for (int i = 0; i < n; ++i) {
        A[i] = i;
      }
      int missingIdx = r.nextInt(n);
      int missing = A[missingIdx];
      int dupIdx = r.nextInt(n);
      while (dupIdx == missingIdx) {
        dupIdx = r.nextInt(n);
      }
      int dup = A[dupIdx];
      A[missingIdx] = dup;
      Pair<Integer, Integer> ans = findDuplicateMissing(A);
      System.out.println("times = " + times);
      System.out.println(dup + " " + missing);
      System.out.println(ans.getFirst() + " " + ans.getSecond());
      assert(ans.getFirst().equals(dup) && ans.getSecond().equals(missing));
    }
  }
}
