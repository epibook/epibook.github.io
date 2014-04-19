// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
package com.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import com.epi.utils.Pair;

class TaskAssignment {
  // @include
  public static ArrayList<Pair<Integer, Integer>> taskAssignment(
      ArrayList<Integer> A) {
    Collections.sort(A);
    ArrayList<Pair<Integer, Integer>> P = new ArrayList<Pair<Integer, Integer>>();
    for (int i = 0, j = A.size() - 1; i < j; ++i, --j) {
      P.add(new Pair<Integer, Integer>(A.get(i), A.get(j)));
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
    ArrayList<Integer> A = new ArrayList<Integer>();
    for (int i = 0; i < n; ++i) {
      A.add(gen.nextInt(999));
    }
    ArrayList<Pair<Integer, Integer>> P = taskAssignment(A);
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < P.size(); ++i) {
      if (P.get(i).getFirst() + P.get(i).getSecond() > max) {
        max = P.get(i).getFirst() + P.get(i).getSecond();
      }
    }
    System.out.println(max);
  }
}
