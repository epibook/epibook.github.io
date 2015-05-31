// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

class Interval implements Comparable<Interval> {
  class Endpoint {
    public boolean isClosed;
    public int val;
  }

  public int compareTo(Interval i) {
    if (left.val < i.left.val) {
      return -1;
    }
    if (left.val > i.left.val) {
      return 1;
    }
    if (left.isClosed && !i.left.isClosed) {
      return -1;
    }
    if (!left.isClosed && i.left.isClosed) {
      return 1;
    }
    return 0;
  }

  public Endpoint left = new Endpoint();
  public Endpoint right = new Endpoint();
}

class UnionIntervals {
  // @include
  public static List<Interval> unionOfIntervals(Interval[] intervals) {
    // Empty input.
    if (intervals.length == 0) {
      return new ArrayList<>();
    }

    // Sort intervals according to left endpoints of intervals.
    Arrays.sort(intervals);
    Interval curr = new Interval();
    curr = intervals[0];
    List<Interval> result = new ArrayList<>();
    for (int i = 1; i < intervals.length; ++i) {
      if (intervals[i].left.val < curr.right.val ||
          (intervals[i].left.val == curr.right.val &&
           (intervals[i].left.isClosed || curr.right.isClosed))) {
        if (intervals[i].right.val > curr.right.val ||
            (intervals[i].right.val == curr.right.val &&
             intervals[i].right.isClosed)) {
          curr.right = intervals[i].right;
        }
      } else {
        result.add(curr);
        curr = intervals[i];
      }
    }
    result.add(curr);
    return result;
  }
  // @exclude

  private static void checkIntervals(List<Interval> A) {
    // only check the Intervals do not overlap with each other.
    for (int i = 1; i < A.size(); ++i) {
      assert(A.get(i - 1).right.val < A.get(i).left.val ||
             (A.get(i - 1).right.val == A.get(i).left.val &&
              !A.get(i - 1).right.isClosed && !A.get(i).left.isClosed));
    }
  }

  public static void main(String[] args) {
    Random gen = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = gen.nextInt(1000) + 1;
      }
      Interval[] A = new Interval[n];
      for (int i = 0; i < n; ++i) {
        Interval temp = new Interval();
        temp.left.isClosed = gen.nextBoolean();
        temp.left.val = gen.nextInt(9999);
        temp.right.isClosed = gen.nextBoolean();
        temp.right.val = gen.nextInt(temp.left.val + 100) + temp.left.val + 1;
        A[i] = temp;
      }
      List<Interval> ret = unionOfIntervals(A);
      if (!ret.isEmpty()) {
        checkIntervals(ret);
      }
    }
  }
}
