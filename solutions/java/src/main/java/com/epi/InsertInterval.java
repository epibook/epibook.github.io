package com.epi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.epi.utils.Interval;

public class InsertInterval {
  // @include
  public static List<Interval> insertInterval(List<Interval> intervals,
      Interval newInterval) {
    int i = 0;
    List<Interval> res = new ArrayList<Interval>();
    // Insert intervals appeared before new_interval.
    while (i < intervals.size() && newInterval.left > intervals.get(i).right) {
      res.add(intervals.get(i++));
    }

    // Merges intervals that overlap with new_interval.
    while (i < intervals.size() && newInterval.right >= intervals.get(i).left) {
      newInterval = new Interval(Math.min(newInterval.left,
          intervals.get(i).left), Math.max(newInterval.right,
          intervals.get(i).right));
      ++i;
    }
    res.add(newInterval);

    // Insert intervals appearing after new_interval.
    res.addAll(intervals.subList(i, intervals.size()));
    return res;
  }

  // @exclude

  private static void checkIntervals(List<Interval> res) {
    // Only check the intervals do not overlap with each other.
    for (int i = 1; i < res.size(); ++i) {
      assert (res.get(i - 1).right < res.get(i).left);
    }
  }

  private static void smallTest() {
    List<Interval> A = new ArrayList<Interval>() {
      {
        add(new Interval(1, 5));
      }
    };
    Interval newOne = new Interval(0, 3);
    List<Interval> res = insertInterval(A, newOne);
    assert (res.size() == 1 && res.get(0).left == 0 && res.get(0).right == 5);
    newOne = new Interval(0, 0);
    res = insertInterval(A, newOne);
    assert (res.size() == 2 && res.get(0).left == 0 && res.get(0).right == 0
        && res.get(1).left == 1 && res.get(1).right == 5);
  }

  public static void main(String[] args) {
    smallTest();
    Random r = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = r.nextInt(10000) + 1;
      }
      List<Interval> A = new ArrayList<Interval>();
      int pre = 0;
      for (int i = 0; i < n; ++i) {
        Interval temp = new Interval();
        temp.left = pre + r.nextInt(10) + 1;
        temp.right = temp.left + r.nextInt(10) + 1;
        pre = temp.right;
        A.add(temp);
      }
      Interval target = new Interval();
      target.left = r.nextInt(101);
      target.right = target.left + r.nextInt(101);
      List<Interval> res = insertInterval(A, target);
      checkIntervals(res);
    }
  }
}
