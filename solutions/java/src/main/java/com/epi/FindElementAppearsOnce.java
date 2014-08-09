package com.epi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class FindElementAppearsOnce {
  // @include
  public static int findElementAppearsOnce(List<Integer> A) {
    int ones = 0, twos = 0;
    int nextOnes, nextTwos;
    for (int i : A) {
      nextOnes = (~i & ones) | (i & ~ones & ~twos);
      nextTwos = (~i & twos) | (i & ones);
      ones = nextOnes;
      twos = nextTwos;
    }
    return ones;
  }
  // @exclude

  public static void main(String[] args) {
    Random r = new Random();
    for (int times = 0; times < 10000; ++times) {
      List<Integer> A = new ArrayList<>();
      int n;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = r.nextInt(10000) + 1;
      }
      int single = r.nextInt(n);
      for (int i = 0; i < n; ++i) {
        A.add(i);
        if (i != single) {
          A.add(i);
          A.add(i);
        }
      }
      System.out.println("Singleton element: " + findElementAppearsOnce(A));
      assert (findElementAppearsOnce(A) == single);
    }
  }
}
