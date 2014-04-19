package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PlusOne {
  // @include
  public static List<Integer> plusOne(List<Integer> A) {
    Collections.reverse(A);
    A.set(0, A.get(0) + 1);
    for (int i = 0; i < A.size() && A.get(i) == 10; ++i) {
      A.set(i, 0);
      if (i + 1 >= A.size()) {
        A.add(0);
      }
      A.set(i + 1, A.get(i + 1) + 1);
    }
    Collections.reverse(A);
    return A;
  }

  // @exclude

  private static List<Integer> randVector(int len) {
    if (len == 0) {
      return Arrays.asList(0);
    }
    Random r = new Random();
    List<Integer> A = new ArrayList<Integer>();
    A.add(r.nextInt(9) + 1);
    --len;
    while (len != 0) {
      A.add(r.nextInt(10));
      --len;
    }
    return A;
  }

  private static void smallTest() {
    List<Integer> res = plusOne(new ArrayList<Integer>() {
      {
        add(9);
        add(9);
      }
    });
    assert (res.size() == 3 && res.get(0) == 1 && res.get(1) == 0 && res.get(2) == 0);
    res = plusOne(new ArrayList<Integer>() {
      {
        add(3);
        add(1);
        add(4);
      }
    });
    assert (res.size() == 3 && res.get(0) == 3 && res.get(1) == 1 && res.get(2) == 5);
  }

  public static void main(String[] args) {
    smallTest();
    Random r = new Random();
    int n;
    if (args.length == 1) {
      n = Integer.parseInt(args[0]);
    } else {
      n = r.nextInt(1001);
    }
    List<Integer> A = randVector(n);
    System.out.println(A);
    List<Integer> res = plusOne(A);
    System.out.println(res);
  }
}
