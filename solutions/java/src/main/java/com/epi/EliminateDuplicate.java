// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
package com.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

class EliminateDuplicate {
  // @include
  public static void eliminateDuplicate(ArrayList<Integer> a) {
    Collections.sort(a); // makes identical elements become neighbors.
    // C++ unique-like algorithm on indexes
    if (a.size() < 2) {
      return;
    }
    int result = 0;
    for (int first = 1; first < a.size(); first++) {
      if (!a.get(first).equals(a.get(result))) {
        a.set(++result, a.get(first));
      }
    }
    // shrink array size
    a.subList(++result, a.size()).clear();
  }
  // @exclude

  public static void checkAnswer(ArrayList<Integer> a) {
    for (int i = 1; i < a.size(); ++i) {
      assert (!a.get(i).equals(a.get(i - 1)));
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
