// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LongestNondecreasingSubsequence {
  public static void main(String[] args) {
    Random r = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n;
      if (args.length == 2) {
        n = Integer.parseInt(args[0]);
      } else {
        n = r.nextInt(1000) + 1;
      }
      List<Integer> A = new ArrayList<>();
      for (int i = 0; i < n; ++i) {
        A.add(r.nextInt(99999999));
      }
      System.out.println(A);
      System.out.println("n = " + n);
      int retLength = LongestNondecreasingSubsequenceNlogn
                          .longestNondecreasingSubsequenceLength(A);
      int anotherLength = LongestNondecreasingSubsequenceN2
                              .longestNondecreasingSubsequenceLength(A);
      assert(retLength == anotherLength);
    }
  }
}
