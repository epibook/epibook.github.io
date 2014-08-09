// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
package com.epi;

import com.epi.utils.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

class TaskAssignment {
  // @include
  public static List<Pair<Integer, Integer>> taskAssignment(
      List<Integer> A) {
    Collections.sort(A);
    List<Pair<Integer, Integer>> P = new ArrayList<>();
    for (int i = 0, j = A.size() - 1; i < j; ++i, --j) {
      P.add(new Pair<>(A.get(i), A.get(j)));
    }
    return P;
  }
  // @exclude

  public static void main(String[] args) {
    Random gen = new Random();
    int n;
    if (args.length == 1) {
      n = Integer.parseInt(args[0]);
    } else {
      n = gen.nextInt(10000) + 1;
    }
    List<Integer> A = new ArrayList<>();
    for (int i = 0; i < n; ++i) {
      A.add(gen.nextInt(999));
    }
    List<Pair<Integer, Integer>> P = taskAssignment(A);
    int max = Integer.MIN_VALUE;
    for (Pair<Integer, Integer> aP : P) {
      if (aP.getFirst() + aP.getSecond() > max) {
        max = aP.getFirst() + aP.getSecond();
      }
    }
    System.out.println(max);
  }
}
