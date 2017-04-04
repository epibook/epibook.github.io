// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class PointsCoveringIntervalsSorting {
  // @include
  public static class Interval {
    public int left, right;

    public Interval(int l, int r) {
      this.left = l;
      this.right = r;
    }
  }

  public static List<Integer> findMinimumVisits(List<Interval> intervals) {
    if (intervals.isEmpty()) {
      return Collections.EMPTY_LIST;
    }

    // Sort intervals based on the right endpoints.
    Collections.sort(intervals, new Comparator<Interval>() {
      @Override
      public int compare(Interval i1, Interval i2) {
        return Integer.compare(i1.right, i2.right);
      }
    });
    List<Integer> visits = new ArrayList<>();
    Integer lastVisitTime = intervals.get(0).right;
    visits.add(lastVisitTime);
    for (Interval interval : intervals) {
      if (interval.left > lastVisitTime) {
        // The current right endpoint, lastVisitTime, will not cover any more
        // intervals.
        lastVisitTime = interval.right;
        visits.add(lastVisitTime);
      }
    }
    return visits;
  }
  // @exclude

  // O(n^2) checking solution
  private static void checkAnswer(List<Interval> intervals,
                                  List<Integer> answer) {
    boolean[] isVisited = new boolean[intervals.size()];
    for (Integer a : answer) {
      for (int i = 0; i < intervals.size(); ++i) {
        if (a >= intervals.get(i).left && a <= intervals.get(i).right) {
          isVisited[i] = true;
        }
      }
    }

    for (boolean b : isVisited) {
      assert(b);
    }
  }

  private static void simpleTest() {
    List<Interval> intervals = Arrays.asList(
        new Interval(1, 4), new Interval(2, 8), new Interval(3, 6),
        new Interval(3, 5), new Interval(7, 10), new Interval(9, 11));
    List<Integer> ans = findMinimumVisits(intervals);
    List<Integer> golden = Arrays.asList(4, 10);
    assert(ans.equals(golden));

    intervals = Arrays.asList(new Interval(1, 2), new Interval(2, 3),
                              new Interval(3, 4), new Interval(4, 5),
                              new Interval(5, 6), new Interval(6, 7));
    ans = findMinimumVisits(intervals);
    golden = Arrays.asList(2, 4, 6);
    assert(ans.equals(golden));

    intervals = Arrays.asList(new Interval(1, 5), new Interval(2, 3),
                              new Interval(3, 4));
    ans = findMinimumVisits(intervals);
    golden = Arrays.asList(3);
    assert(ans.equals(golden));
  }

  public static void main(String[] args) {
    simpleTest();
    Random gen = new Random();
    for (int times = 0; times < 1000; ++times) {
      System.out.println("Test " + times);
      int n;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = gen.nextInt(10000) + 1;
      }
      List<Interval> A = new ArrayList<>(n);
      for (int i = 0; i < n; ++i) {
        int left = gen.nextInt(9999);
        int right = gen.nextInt(left + 100) + left;
        A.add(new Interval(left, right));
      }

      List<Integer> ans = findMinimumVisits(A);
      checkAnswer(A, ans);
    }
  }
}
