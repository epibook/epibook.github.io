package com.epi;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class NonconstructibleChange {
  // @include
  public static int smallestNonconstructibleValue(List<Integer> A) {
    Collections.sort(A);
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
    List<Integer> A = Arrays.asList(1, 2, 3, 4);
    assert(11 == smallestNonconstructibleValue(A));
    A = Arrays.asList(1, 2, 2, 4);
    assert(10 == smallestNonconstructibleValue(A));
    A = Arrays.asList(2, 3, 4, 5);
    assert(1 == smallestNonconstructibleValue(A));
    A = Arrays.asList(1, 3, 2, 1);
    assert(8 == smallestNonconstructibleValue(A));
    A = Arrays.asList(1, 3, 2, 5);
    assert(12 == smallestNonconstructibleValue(A));
    A = Arrays.asList(1, 3, 2, 6);
    assert(13 == smallestNonconstructibleValue(A));
    A = Arrays.asList(1, 3, 2, 7);
    assert(14 == smallestNonconstructibleValue(A));
    A = Arrays.asList(1, 3, 2, 8);
    assert(7 == smallestNonconstructibleValue(A));
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
    List<Integer> A = new ArrayList<>(n);
    for (int i = 0; i < n; i++) {
      A.add(r.nextInt(1000) + 1);
    }
    int ans = smallestNonconstructibleValue(A);
    System.out.println(ans);
  }
}
