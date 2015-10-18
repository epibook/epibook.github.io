// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class UnionIntervals {
  // @include
  public static class Interval implements Comparable<Interval> {
    public Endpoint left = new Endpoint();
    public Endpoint right = new Endpoint();

    private static class Endpoint {
      public boolean isClosed;
      public int val;
    }

    public int compareTo(Interval i) {
      if (Integer.compare(left.val, i.left.val) != 0) {
        return left.val - i.left.val;
      }
      // Left endpoints are equal, so now see if one is closed and the
      // other open - closed intervals should appear first.
      if (left.isClosed && !i.left.isClosed) {
        return -1;
      }
      if (!left.isClosed && i.left.isClosed) {
        return 1;
      }
      return 0;
    }

    @Override
    public boolean equals(Object obj) {
      if (!(obj instanceof Interval)) {
        return false;
      }
      if (this == obj) {
        return true;
      }
      Interval that = (Interval)obj;
      return this.left.val == that.left.val
          && this.left.isClosed == that.left.isClosed;
    }

    // clang-format off
    @Override
    public int hashCode() { return Objects.hash(left.val, left.isClosed); }
    // clang-format on
  }

  public static List<Interval> unionOfIntervals(List<Interval> intervals) {
    // Empty input.
    if (intervals.size() == 0) {
      return new ArrayList<>();
    }

    // Sort intervals according to left endpoints of intervals.
    Collections.sort(intervals);
    Interval curr = intervals.get(0);
    List<Interval> result = new ArrayList<>();
    for (int i = 1; i < intervals.size(); ++i) {
      if (intervals.get(i).left.val < curr.right.val
          || (intervals.get(i).left.val == curr.right.val
              && (intervals.get(i).left.isClosed || curr.right.isClosed))) {
        if (intervals.get(i).right.val > curr.right.val
            || (intervals.get(i).right.val == curr.right.val
                && intervals.get(i).right.isClosed)) {
          curr.right = intervals.get(i).right;
        }
      } else {
        result.add(curr);
        curr = intervals.get(i);
      }
    }
    result.add(curr);
    return result;
  }
  // @exclude

  private static void checkIntervals(List<Interval> A) {
    // only check the Intervals do not overlap with each other.
    for (int i = 1; i < A.size(); ++i) {
      assert(A.get(i - 1).right.val < A.get(i).left.val
             || (A.get(i - 1).right.val == A.get(i).left.val
                 && !A.get(i - 1).right.isClosed && !A.get(i).left.isClosed));
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
      List<Interval> A = new ArrayList<>(n);
      for (int i = 0; i < n; ++i) {
        Interval temp = new Interval();
        temp.left.isClosed = gen.nextBoolean();
        temp.left.val = gen.nextInt(9999);
        temp.right.isClosed = gen.nextBoolean();
        temp.right.val = gen.nextInt(temp.left.val + 100) + temp.left.val + 1;
        A.add(temp);
      }
      List<Interval> ret = unionOfIntervals(A);
      if (!ret.isEmpty()) {
        checkIntervals(ret);
      }
    }
  }
}
