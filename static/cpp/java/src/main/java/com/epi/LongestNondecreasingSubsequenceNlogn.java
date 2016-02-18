package com.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class LongestNondecreasingSubsequenceNlogn {
  // @include
  public static int longestNondecreasingSubsequence(List<Integer> A) {
    List<Integer> tailValues = new ArrayList<>();

    for (int a : A) {
      int it = Collections.binarySearch(tailValues, a);
      if (it < 0) {
        tailValues.add(a);
      } else {
        tailValues.set(it, a);
      }
    }
    return tailValues.size();
  }
  // @exclude
}
