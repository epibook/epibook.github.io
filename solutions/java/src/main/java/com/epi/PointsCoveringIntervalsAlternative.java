// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
package com.epi;

import com.epi.utils.EndPoint;
import com.epi.utils.Interval;

import java.util.*;

class PointsCoveringIntervalsAlternative {

  // @include
  public static List<Integer> findMinimumVisits(List<Interval> I) {
    List<EndPoint> endpoints = new ArrayList<>();
    for (Interval aI : I) {
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

  // O(n^2) checking solution.
  public static void checkAnswer(List<Interval> I, List<Integer> ans) {
    boolean[] isVisited = new boolean[I.size()];
    for (Integer a : ans) {
      for (int i = 0; i < I.size(); ++i) {
        if (a >= I.get(i).left && a <= I.get(i).right) {
          isVisited[i] = true;
        }
      }
    }

    for (boolean b : isVisited) {
      assert (b);
    }
  }

  private static void simpleTest() {
    List<Interval> I = new ArrayList<>();
    I.add(new Interval(1, 4));
    I.add(new Interval(2, 8));
    I.add(new Interval(3, 6));
    I.add(new Interval(3, 5));
    I.add(new Interval(7, 10));
    I.add(new Interval(9, 11));
    List<Integer> ans = findMinimumVisits(I);
    assert (ans.size() == 2 && ans.get(0) == 4 && ans.get(1) == 10);
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
      List<Interval> A = new ArrayList<>();
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
