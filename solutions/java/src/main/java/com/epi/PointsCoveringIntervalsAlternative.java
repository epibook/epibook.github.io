// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.*;

class PointsCoveringIntervalsAlternative {
  // @include

  private static class Interval {
    public int left, right;

    public Interval(int l, int r) {
      this.left = l;
      this.right = r;
    }
  }

  public static class EndPoint implements Comparable<EndPoint> {
    public Interval ptr;
    public boolean isLeft;

    public EndPoint(Interval i, boolean il) {
      ptr = i;
      isLeft = il;
    }

    public int compareTo(EndPoint that) {
      int a = isLeft ? ptr.left : ptr.right, b = that.isLeft ? that.ptr.left
                                                             : that.ptr.right;
      if (a != b) {
        return a - b;
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
  }

  public static List<Integer> findMinimumVisits(Interval[] intervals) {
    List<EndPoint> endpoints = new ArrayList<>();
    for (Interval aI : intervals) {
      endpoints.add(new EndPoint(aI, true));
      endpoints.add(new EndPoint(aI, false));
    }

    Collections.sort(endpoints);
    return findMinimumVisitsHelper(endpoints);
  }

  private static List<Integer> findMinimumVisitsHelper(List<EndPoint> endpoints) {
    List<Integer> S = new ArrayList<>(); // A minimum set of visit times.
    Set<Interval> covered = new HashSet<>();
    List<Interval> covering = new ArrayList<>();
    for (EndPoint e : endpoints) {
      if (e.isLeft) {
        covering.add(e.ptr);
      } else if (!covered.contains(e.ptr)) {
        // e's interval has not been covered.
        S.add(e.ptr.right);
        // Adds all intervals in covering to covered.
        covered.addAll(covering);
        covering.clear(); // e is contained in all intervals in covering.
      }
    }
    return S;
  }
  // @exclude

  // O(n^2) checking solution
  private static void checkAnswer(Interval[] intervals, List<Integer> answer) {
    boolean[] isVisited = new boolean[intervals.length];
    for (Integer a : answer) {
      for (int i = 0; i < intervals.length; ++i) {
        if (a >= intervals[i].left && a <= intervals[i].right) {
          isVisited[i] = true;
        }
      }
    }

    for (boolean b : isVisited) {
      assert(b);
    }
  }

  private static void simpleTest() {
    Interval[] intervals = new Interval[6];
    intervals[0] = new Interval(1, 4);
    intervals[1] = new Interval(2, 8);
    intervals[2] = new Interval(3, 6);
    intervals[3] = new Interval(3, 5);
    intervals[4] = new Interval(7, 10);
    intervals[5] = new Interval(9, 11);
    List<Integer> ans = findMinimumVisits(intervals);
    List<Integer> golden = Arrays.asList(4, 10);
    assert(ans.equals(golden));

    intervals = new Interval[6];
    intervals[0] = new Interval(1, 2);
    intervals[1] = new Interval(2, 3);
    intervals[2] = new Interval(3, 4);
    intervals[3] = new Interval(4, 5);
    intervals[4] = new Interval(5, 6);
    intervals[5] = new Interval(6, 7);
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
      Interval[] A = new Interval[n];
      for (int i = 0; i < n; ++i) {
        int left = gen.nextInt(9999);
        int right = gen.nextInt(left + 100) + left;
        A[i] = new Interval(left, right);
      }

      List<Integer> ans = findMinimumVisits(A);
      checkAnswer(A, ans);
    }
  }
}
