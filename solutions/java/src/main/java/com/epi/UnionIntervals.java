// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

class Interval implements Comparable<Interval> {
  class Endpoint {
    public boolean isClose;
    public int val;
  }

  public int compareTo(Interval i) {
    if (left.val < i.left.val) {
      return -1;
    }
    if (left.val > i.left.val) {
      return 1;
    }
    if (left.isClose && !i.left.isClose) {
      return -1;
    }
    if (!left.isClose && i.left.isClose) {
      return 1;
    }
    return 0;
  }

  public Endpoint left = new Endpoint();
  public Endpoint right = new Endpoint();
}

class UnionIntervals {

  // @include
  public static List<Interval> unionIntervals(Interval[] I) {
    // Empty input.
    List<Interval> uni = new ArrayList<>();
    if (I.length == 0) {
      return uni;
    }

    // Sorts Intervals according to their left endpoints.
    Arrays.sort(I);
    Interval curr = new Interval();
    curr = I[0];

    for (int i = 1; i < I.length; ++i) {
      if (I[i].left.val < curr.right.val
          || (I[i].left.val == curr.right.val
              && (I[i].left.isClose || curr.right.isClose))) {
        if (I[i].right.val > curr.right.val
            || (I[i].right.val == curr.right.val
                && I[i].right.isClose)) {
          curr.right = I[i].right;
        }
      } else {
        uni.add(curr);
        curr = I[i];
      }
    }
    uni.add(curr);
    return uni;
  }
  // @exclude

  private static void checkIntervals(List<Interval> A) {
    // only check the Intervals do not overlap with each other.
    for (int i = 1; i < A.size(); ++i) {
      assert(A.get(i - 1).right.val < A.get(i).left.val ||
             (A.get(i - 1).right.val == A.get(i).left.val &&
              !A.get(i - 1).right.isClose && !A.get(i).left.isClose));
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
        temp.left.isClose = gen.nextBoolean();
        temp.left.val = gen.nextInt(9999);
        temp.right.isClose = gen.nextBoolean();
        temp.right.val = gen.nextInt(temp.left.val + 100) + temp.left.val + 1;
        A[i] = temp;
      }
      List<Interval> ret = unionIntervals(A);
      if (!ret.isEmpty()) {
        checkIntervals(ret);
      }
    }
  }
}
