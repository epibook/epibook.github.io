// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

public class PointsCoveringIntervalsAlternative {
  // @include
  public static class Interval {
    public int left, right;

    public Interval(int l, int r) {
      this.left = l;
      this.right = r;
    }
  }

  private static class EndPoint implements Comparable<EndPoint> {
    public Interval interval;
    public boolean isLeft;

    public EndPoint(Interval i, boolean isLeft) {
      interval = i;
      this.isLeft = isLeft;
    }

    public int compareTo(EndPoint that) {
      int a = isLeft ? interval.left : interval.right;
      int b = that.isLeft ? that.interval.left : that.interval.right;

      if (a != b) {
        return Integer.compare(a, b);
      }
      // Give preference to left endpoint.
      if (isLeft && !that.isLeft) {
        return -1;
      } else if (!isLeft && that.isLeft) {
        return 1;
      } else {
        return 0;
      }
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      return compareTo((EndPoint)o) == 0;
    }

    @Override
    public int hashCode() {
      return Objects.hash(isLeft, isLeft ? interval.left : interval.right);
    }
  }

  public static List<Integer> findMinimumVisits(List<Interval> intervals) {
    List<EndPoint> endpoints = new ArrayList<>();
    for (Interval aI : intervals) {
      endpoints.add(new EndPoint(aI, true));
      endpoints.add(new EndPoint(aI, false));
    }

    Collections.sort(endpoints);
    return findMinimumVisitsHelper(endpoints);
  }

  private static List<Integer> findMinimumVisitsHelper(
      List<EndPoint> endpoints) {
    List<Integer> S = new ArrayList<>(); // A minimum set of visit times.
    Set<Interval> covered = new HashSet<>();
    List<Interval> covering = new ArrayList<>();
    for (EndPoint e : endpoints) {
      if (e.isLeft) {
        covering.add(e.interval);
      } else if (!covered.contains(e.interval)) {
        // e's interval has not been covered.
        S.add(e.interval.right);
        // Adds all intervals in covering to covered.
        covered.addAll(covering);
        covering.clear(); // e is contained in all intervals in covering.
      }
    }
    return S;
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
