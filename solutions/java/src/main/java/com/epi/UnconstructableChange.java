package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class UnconstructableChange {
  // @include
  public static int unconstructableChange(List<Integer> A) {
    Collections.sort(A);
    int sum = 0;
    for (int a : A) {
      if (a > sum + 1) {
        break;
      }
      sum += a;
    }
    return sum + 1;
  }

  // @exclude

  private static void smallTest() {
    List<Integer> A = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4));
    assert (11 == unconstructableChange(A));
    A = new ArrayList<Integer>(Arrays.asList(1, 2, 2, 4));
    assert (10 == unconstructableChange(A));
    A = new ArrayList<Integer>(Arrays.asList(2, 3, 4, 5));
    assert (1 == unconstructableChange(A));
    A = new ArrayList<Integer>(Arrays.asList(1, 3, 2, 1));
    assert (8 == unconstructableChange(A));
    A = new ArrayList<Integer>(Arrays.asList(1, 3, 2, 5));
    assert (12 == unconstructableChange(A));
    A = new ArrayList<Integer>(Arrays.asList(1, 3, 2, 6));
    assert (13 == unconstructableChange(A));
    A = new ArrayList<Integer>(Arrays.asList(1, 3, 2, 7));
    assert (14 == unconstructableChange(A));
    A = new ArrayList<Integer>(Arrays.asList(1, 3, 2, 8));
    assert (7 == unconstructableChange(A));
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
    List<Integer> A = new ArrayList<Integer>();
    for (int i = 0; i < n; i++) {
      A.add(r.nextInt(1000) + 1);
    }
    System.out.println(A);
    int ans = unconstructableChange(A);
    System.out.println(ans);
  }
}
