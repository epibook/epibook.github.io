// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TaskAssignment {
  // @include
  private static class PairedTasks {
    public Integer task1;
    public Integer task2;

    public PairedTasks(Integer task1, Integer task2) {
      this.task1 = task1;
      this.task2 = task2;
    }
  }

  public static List<PairedTasks> optimumTaskAssignment(
      List<Integer> taskDurations) {
    Collections.sort(taskDurations);
    List<PairedTasks> optimumAssignments = new ArrayList<>();
    for (int i = 0, j = taskDurations.size() - 1; i < j; ++i, --j) {
      optimumAssignments.add(
          new PairedTasks(taskDurations.get(i), taskDurations.get(j)));
    }
    return optimumAssignments;
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
    List<Integer> A = new ArrayList<>(n);
    for (int i = 0; i < n; ++i) {
      A.add(gen.nextInt(999));
    }
    List<PairedTasks> P = optimumTaskAssignment(A);
    int max = Integer.MIN_VALUE;
    for (PairedTasks aP : P) {
      if (aP.task1 + aP.task2 > max) {
        max = aP.task1 + aP.task2;
      }
    }
    System.out.println(max);
  }
}
