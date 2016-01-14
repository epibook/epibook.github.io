package com.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Rearrange {
  // @include
  public static void rearrange(List<Integer> A) {
    for (int i = 1; i < A.size(); ++i) {
      if (((i % 2) == 0 && A.get(i - 1) < A.get(i))
          || ((i % 2) != 0 && A.get(i - 1) > A.get(i))) {
        Collections.swap(A, i - 1, i);
      }
    }
  }
  // @exclude

  private static void checkAnswer(List<Integer> A) {
    for (int i = 0; i < A.size(); ++i) {
      if ((i % 2) != 0) {
        assert(A.get(i) >= A.get(i - 1));
        if (i < A.size() - 1) {
          assert(A.get(i) >= A.get(i + 1));
        }
      } else {
        if (i > 0) {
          assert(A.get(i - 1) >= A.get(i));
        }
        if (i < A.size() - 1) {
          assert(A.get(i + 1) >= A.get(i));
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
      List<Integer> A = new ArrayList<>(n);
      for (int i = 0; i < n; ++i) {
        A.add(r.nextInt(2 * n + 1) - n);
      }
      rearrange(A);
      checkAnswer(A);
    }
  }
}
