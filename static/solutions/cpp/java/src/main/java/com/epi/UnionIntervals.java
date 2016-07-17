// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.
/*

@slug
union-of-intervals

@summary
In this problem we consider sets of intervals with integer endpoints;
the intervals may be open or closed at either end.
Examples of such sets are $\{[0,2], [1,5], [7,8]\}$ and $\{[0,2),(2,3],
(3,5]\}$.
We want to compute the union of the intervals in such sets.
For example, the union of the intervals in the first
set is $\{[0,5], [7,8]\}$; the union of the intervals in the
second set is $\{[0,2), (2,5]\}$.
A concrete example is given in Figure~\vref{union-of-intervals-fig}.

\begin{figure}[htb]
\centering
\input{graphics/union_interval.tex}
\mycaption{A set of intervals and their union.\label{union-of-intervals-fig}}
\end{figure}

@problem
Design an algorithm that takes as input a set of intervals, and
outputs their union expressed as a set of disjoint intervals.

*/

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
      if (obj == null || !(obj instanceof Interval)) {
        return false;
      }
      if (this == obj) {
        return true;
      }
      Interval that = (Interval)obj;
      return left.val == that.left.val && left.isClosed == that.left.isClosed;
    }

    // clang-format off
    @Override
    public int hashCode() { return Objects.hash(left.val, left.isClosed); }
    // clang-format on
  }

  public static List<Interval> unionOfIntervals(List<Interval> intervals) {
    // Empty input.
    if (intervals.isEmpty()) {
      return Collections.EMPTY_LIST;
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
