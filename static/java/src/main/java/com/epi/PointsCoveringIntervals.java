// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

// @include
public class PointsCoveringIntervals {
  public static class Interval {
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
      return Integer.compare(a.right, b.right);
    }
  }

  private static class RightComp implements Comparator<Interval> {
    public int compare(Interval a, Interval b) {
      if (a.right != b.right) {
        return Integer.compare(a.right, b.right);
      }
      return Integer.compare(a.left, b.left);
    }
  }

  public static List<Integer> findMinimumVisits(List<Interval> intervals) {
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
    assert(ans.size() == 2 && ans.get(0) == 4 && ans.get(1) == 10);

    intervals = Arrays.asList(new Interval(1, 2), new Interval(2, 3),
                              new Interval(3, 4), new Interval(4, 5),
                              new Interval(5, 6), new Interval(6, 7));
    ans = findMinimumVisits(intervals);
    System.out.println(ans);
    assert(ans.size() == 3 && ans.get(0) == 2 && ans.get(1) == 4
           && ans.get(2) == 6);
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
      List<Interval> intervals = new ArrayList<>(n);
      for (int i = 0; i < n; ++i) {
        int left = gen.nextInt(9999);
        int right = gen.nextInt(left + 100) + left + 1;
        intervals.add(new Interval(left, right));
      }
      List<Integer> ans = findMinimumVisits(intervals);
      checkAnswer(intervals, ans);
    }
  }
}
