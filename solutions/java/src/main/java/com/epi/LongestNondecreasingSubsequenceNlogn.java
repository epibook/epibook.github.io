// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LongestNondecreasingSubsequenceNlogn {
  // @include
  public static int longestNondecreasingSubsequenceLength(List<Integer> A) {
    List<Integer> tailValues = new ArrayList<>();

    for (int a : A) {
      int it = Collections.binarySearch(tailValues, a, Compare.CMP);
      // When a key is not present in the array, Collections.binarySearch()
      // returns the negative of 1 plus the smallest index whose entry
      // is greater than the key.
      //
      // Therefore, if the return value is negative, by taking its absolute
      // value and adding 1, we get the index of the first element in the
      // array which is greater than the key.
      it = Math.abs(it) - 1;
      if (it == tailValues.size()) {
        tailValues.add(a);
      } else {
        tailValues.set(it, a);
      }
    }
    return tailValues.size();
  }

  private static class Compare implements Comparator<Integer> {
    // Note that the comparator never returns 0. Therefore
    // the return from the call to binarySearch() that uses this
    // comparator always encodes the index of the first
    // element in the array which is greater than the key.
    public int compare(Integer x, Integer y) { return x > y ? 1 : -1; }

    public static final Comparator<Integer> CMP = new Compare();
  }
  // @exclude
}
