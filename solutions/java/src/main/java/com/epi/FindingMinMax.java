package com.epi;

import java.util.Arrays;
import java.util.Random;

public class FindingMinMax {
  // @include
  private static class MinMax {
    public Integer min;
    public Integer max;

    public MinMax(Integer min, Integer max) {
      this.min = min;
      this.max = max;
    }

    private static MinMax minMax(Integer a, Integer b) {
      return b.compareTo(a) < 0 ? new MinMax(b, a) : new MinMax(a, b);
    }
  }

  public static MinMax findMinMax(int[] A) {
    if (A.length <= 1) {
      return new MinMax(A[0], A[0]);
    }

    MinMax globalMinMax = MinMax.minMax(A[0], A[1]);
    // Process two elements at a time.
    for (int i = 2; i + 1 < A.length; i += 2) {
      MinMax localMinMax = MinMax.minMax(A[i], A[i + 1]);
      globalMinMax = new MinMax(Math.min(globalMinMax.min, localMinMax.min),
                                Math.max(globalMinMax.max, localMinMax.max));
    }
    // If there is odd number of elements in the array, we still
    // need to compare the last element with the existing answer.
    if ((A.length % 2) != 0) {
      globalMinMax = new MinMax(Math.min(globalMinMax.min, A[A.length - 1]),
                                Math.max(globalMinMax.max, A[A.length - 1]));
    }
    return globalMinMax;
  }
  // @exclude

  private static void SimpleTest() {
    int[] A = new int[] {-1, 3, -4, 6, 4, 10, 4, 4, 9};
    MinMax res = findMinMax(A);
    assert(-4 == res.min && 10 == res.max);
    A[5] = -12;
    assert(-12 == res.min && 9 == res.max);

    A = new int[] {-1, 3, -4};
    res = findMinMax(A);
    assert(-4 == res.min && 3 == res.max);
  }

  public static void main(String[] args) {
    SimpleTest();
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
      MinMax res = findMinMax(A);
      Arrays.sort(A);
      assert(res.min.equals(A[0]) && res.max.equals(A[A.length - 1]));
    }
  }
}
