// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import com.epi.utils.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

class TaskAssignment {
  // @include
  public static List<Pair<Integer, Integer>> taskAssignment(int[] taskDurations) {
    Arrays.sort(taskDurations);
    List<Pair<Integer, Integer>> taskPairings = new ArrayList<>();
    for (int i = 0, j = taskDurations.length - 1; i < j; ++i, --j) {
      taskPairings.add(new Pair<>(taskDurations[i], taskDurations[j]));
    }
    return taskPairings;
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
    int[] A = new int[n];
    for (int i = 0; i < n; ++i) {
      A[i] = gen.nextInt(999);
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
