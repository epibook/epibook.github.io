// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.*;

// @include
public class PointsCoveringIntervals {
  private static class Interval {
    public int left, right;

    public Interval(int l, int r) {
      this.left = l;
      this.right = r;
    }
  }

  private static class LeftComp implements Comparator<Interval> {
    public int compare(Interval a, Interval b) {
      if (a.left != b.left) {
        return Integer.compare(a.left, b.left);
      }
      if (a.right != b.right) {
        return Integer.compare(a.right, b.right);
      }
      return 0;
    }
  }

  private static class RightComp implements Comparator<Interval> {
    public int compare(Interval a, Interval b) {
      if (a.right != b.right) {
        return a.right - b.right;
      }
      if (a.left != b.left) {
        return a.left - b.left;
      }
      return 0;
    }
  }

  public static List<Integer> findMinimumVisits(Interval[] intervals) {
    SortedSet<Interval> left = new TreeSet<>(new LeftComp());
    SortedSet<Interval> right = new TreeSet<>(new RightComp());

    for (Interval interval : intervals) {
      left.add(interval);
      right.add(interval);
    }

    List<Integer> s = new ArrayList<>();
    while (!left.isEmpty() && !right.isEmpty()) {
      int b = right.first().right;
      s.add(b);

      // Removes the intervals which intersect with R.cbegin().
      Iterator<Interval> it = left.iterator();
      while (it.hasNext()) {
        Interval interval = it.next();
        if (interval.left > b) {
          break;
        }

        right.remove(interval);
        it.remove();
      }
    }

    return s;
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
    assert(ans.size() == 2 && ans.get(0) == 4 && ans.get(1) == 10);

    intervals = new Interval[6];
    intervals[0] = new Interval(1, 2);
    intervals[1] = new Interval(2, 3);
    intervals[2] = new Interval(3, 4);
    intervals[3] = new Interval(4, 5);
    intervals[4] = new Interval(5, 6);
    intervals[5] = new Interval(6, 7);
    ans = findMinimumVisits(intervals);
    System.out.println(ans);
    assert(ans.size() == 3 && ans.get(0) == 2 && ans.get(1) == 4 && ans.get(2) == 6);
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
      Interval[] intervals = new Interval[n];
      for (int i = 0; i < n; ++i) {
        int left = gen.nextInt(9999);
        int right = gen.nextInt(left + 100) + left + 1;
        intervals[i] = new Interval(left, right);
      }
      List<Integer> ans = findMinimumVisits(intervals);
      checkAnswer(intervals, ans);
    }
  }
}
