package com.epi;

import java.util.*;

public class NonconstructibleChange {
  // @include
  public static int SmallestNonconstructibleValue(int[] A) {
    Arrays.sort(A);
    int maxConstructibleValue = 0;
    for (int a : A) {
      if (a > maxConstructibleValue + 1) {
        break;
      }
      maxConstructibleValue += a;
    }
    return maxConstructibleValue + 1;
  }
  // @exclude

  private static void smallTest() {
    int[] A = new int[] {1, 2, 3, 4};
    assert(11 == SmallestNonconstructibleValue(A));
    A = new int[] {1, 2, 2, 4};
    assert(10 == SmallestNonconstructibleValue(A));
    A = new int[] {2, 3, 4, 5};
    assert(1 == SmallestNonconstructibleValue(A));
    A = new int[] {1, 3, 2, 1};
    assert(8 == SmallestNonconstructibleValue(A));
    A = new int[] {1, 3, 2, 5};
    assert(12 == SmallestNonconstructibleValue(A));
    A = new int[] {1, 3, 2, 6};
    assert(13 == SmallestNonconstructibleValue(A));
    A = new int[] {1, 3, 2, 7};
    assert(14 == SmallestNonconstructibleValue(A));
    A = new int[] {1, 3, 2, 8};
    assert(7 == SmallestNonconstructibleValue(A));
  }

  public static void main(String[] args) {
    smallTest();
    Random r = new Random();
    int n;
    if (args.length == 1) {
      n = Integer.parseInt(args[0]);
    } else {
      n = r.nextInt(1000) + 1;
    }
    int[] A = new int[n];
    for (int i = 0; i < n; i++) {
      A[i] = r.nextInt(1000) + 1;
    }
    int ans = SmallestNonconstructibleValue(A);
    System.out.println(ans);
  }
}
