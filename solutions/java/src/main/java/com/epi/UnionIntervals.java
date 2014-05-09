// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
package com.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

// @include

class ExInterval implements Comparable<ExInterval> {
  class Endpoint {
    public boolean isClose;
    public int val;
  }

  public int compareTo(ExInterval i) {
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

  public static ArrayList<ExInterval> unionExIntervals(ArrayList<ExInterval> I) {

    // Empty input.
    ArrayList<ExInterval> uni = new ArrayList<ExInterval>();
    if (I.isEmpty()) {
      return uni;
    }

    // Sort ExIntervals according to their left endpoints.
    Collections.sort(I);
    ExInterval curr = new ExInterval();
    curr = I.get(0);

    for (int i = 1; i < I.size(); ++i) {
      if (I.get(i).left.val < curr.right.val
          || (I.get(i).left.val == curr.right.val 
                 && (I.get(i).left.isClose || curr.right.isClose))) {
        if (I.get(i).right.val > curr.right.val
            || (I.get(i).right.val == curr.right.val 
                   && I.get(i).right.isClose)) {
          curr.right = I.get(i).right;
        }
      } else {
        uni.add(curr);
        curr = I.get(i);
      }
    }
    uni.add(curr);
    return uni;
  }

  // @exclude

  public static void checkExIntervals(ArrayList<ExInterval> A) {
    // only check the ExIntervals do not overlap with each other.
    for (int i = 1; i < A.size(); ++i) {
      assert (A.get(i - 1).right.val < A.get(i).left.val || (A.get(i - 1).right.val == A
          .get(i).left.val && !A.get(i - 1).right.isClose && !A.get(i).left.isClose));
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
      ArrayList<ExInterval> A = new ArrayList<ExInterval>();
      for (int i = 0; i < n; ++i) {
        ExInterval temp = new ExInterval();
        temp.left.isClose = gen.nextBoolean();
        temp.left.val = gen.nextInt(9999);
        temp.right.isClose = gen.nextBoolean();
        temp.right.val = gen.nextInt(temp.left.val + 100) + temp.left.val + 1;
        A.add(temp);
      }
      ArrayList<ExInterval> ret = unionExIntervals(A);
      if (!ret.isEmpty()) {
        checkExIntervals(ret);
      }
    }
  }
}
