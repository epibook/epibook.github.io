// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

class EliminateDuplicate {
  // @include
  public static void eliminateDuplicate(ArrayList<Integer> A) {
    Collections.sort(A); // Makes identical elements become neighbors.
    int result = 0;
    for (int first = 1; first < A.size(); first++) {
      if (!A.get(first).equals(A.get(result))) {
        A.set(++result, A.get(first));
      }
    }
    // Shrinks array size.
    A.subList(++result, A.size()).clear();
  }
  // @exclude

  public static void checkAnswer(ArrayList<Integer> a) {
    for (int i = 1; i < a.size(); ++i) {
      assert(!a.get(i).equals(a.get(i - 1)));
    }
  }

  public static void main(String[] args) {
    Random gen = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n;
      ArrayList<Integer> a = new ArrayList<>();
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = gen.nextInt(100000);
      }
      for (int i = 0; i < n; ++i) {
        a.add((n > 1) ? gen.nextInt(n - 1) : 0);
      }
      eliminateDuplicate(a);
      checkAnswer(a);
    }
  }
}
