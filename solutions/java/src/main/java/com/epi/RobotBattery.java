// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
package com.epi;

import com.epi.utils.Utils;

import java.util.Random;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class RobotBattery {

  // @include
  public static int findBatteryCapacity(int[] h) {
    int minHeight = Integer.MAX_VALUE, capacity = 0;
    for (int height : h) {
      capacity = max(capacity, height - minHeight);
      minHeight = min(minHeight, height);
    }
    return capacity;
  }
  // @exclude

  // O(n^2) checking answer.
  private static int checkAns(int[] h) {
    int cap = 0;
    for (int i = 1; i < h.length; ++i) {
      for (int j = 0; j < i; ++j) {
        cap = max(cap, h[i] - h[j]);
      }
    }
    return cap;
  }

  public static void main(String[] args) {
    Random gen = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n;
      if (args.length == 1) {
        n = Integer.valueOf(args[0]);
      } else {
        n = gen.nextInt(10000) + 1;
      }
      int[] A = new int[n];
      for (int i = 0; i < n; ++i) {
        A[i] = Utils.nextPositiveInt(gen, n);
      }
      System.out.println(findBatteryCapacity(A));
      assert (checkAns(A) == findBatteryCapacity(A));
    }
  }
}
