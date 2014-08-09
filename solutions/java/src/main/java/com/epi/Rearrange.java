package com.epi;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class Rearrange {
  // @include
  public static void rearrange(ArrayList<Integer> A) {
    for (int i = 1; i < A.size(); ++i) {
      if (((i & 1) == 0 && A.get(i - 1) < A.get(i))
          || ((i & 1) == 1 && A.get(i - 1) > A.get(i))) {
        Integer temp = A.get(i - 1);
        A.set(i - 1, A.get(i));
        A.set(i, temp);
      }
    }
  }
  // @exclude

  private static void checkAnswer(ArrayList<Integer> A) {
    for (int i = 0; i < A.size(); ++i) {
      if ((i & 1) != 0) {
        assert (A.get(i) >= A.get(i - 1));
        if (i < A.size() - 1) {
          assert (A.get(i) >= A.get(i + 1));
        }
      } else {
        if (i > 0) {
          assert (A.get(i - 1) >= A.get(i));
        }
        if (i < A.size() - 1) {
          assert (A.get(i + 1) >= A.get(i));
        }
      }
    }
  }

  public static void main(String[] args) {
    Random r = new Random();
    for (int times = 0; times < 10000; ++times) {
      int n;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = r.nextInt(10000) + 1;
      }
      ArrayList<Integer> A = new ArrayList<>();
      for (int i = 0; i < n; ++i) {
        A.add(r.nextInt(2 * n + 1) - n);
      }
      rearrange(A);
      /*
       * System.out.println(A);
       */
      checkAnswer(A);
    }
  }
}
