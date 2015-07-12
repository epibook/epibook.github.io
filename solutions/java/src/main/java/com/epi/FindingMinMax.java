package com.epi;

import com.epi.utils.Pair;

import java.util.Arrays;
import java.util.Random;

public class FindingMinMax {
  // @include
  // Returns (min, max) pair of elements in A.
  public static Pair<Integer, Integer> findMinMax(int[] A) {
    if (A.length <= 1) {
      return new Pair<>(A[0], A[0]);
    }

    Pair<Integer, Integer> globalMinMax = Pair.minmax(A[0], A[1]);
    // Process elements one pair at a time.
    for (int i = 2; i + 1 < A.length; i += 2) {
      Pair<Integer, Integer> localMinMax = Pair.minmax(A[i], A[i + 1]);
      globalMinMax =
          new Pair<>(Math.min(globalMinMax.getFirst(), localMinMax.getFirst()),
                     Math.max(globalMinMax.getSecond(), localMinMax.getSecond()));
    }
    // If there is odd number of elements in the array, we still
    // need to compare the last element with the existing answer.
    if ((A.length % 2) != 0) {
      globalMinMax =
          new Pair<>(Math.min(globalMinMax.getFirst(), A[A.length - 1]),
                     Math.max(globalMinMax.getSecond(), A[A.length - 1]));
    }
    return globalMinMax;
  }
  // @exclude

  public static void main(String[] args) {
    Random r = new Random();
    for (int times = 0; times < 10000; ++times) {
      int n;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = r.nextInt(10000) + 1;
      }
      int[] A = new int[n];
      for (int i = 0; i < n; ++i) {
        A[i] = r.nextInt(1000000);
      }
      Pair<Integer, Integer> res = findMinMax(A);
      Arrays.sort(A);
      assert(res.getFirst().equals(A[0]) &&
             res.getSecond().equals(A[A.length - 1]));
    }
  }
}
