package com.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.epi.utils.Pair;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class FindingMinMax {
  // @include
  // Return (min, max) pair of elements in A.
  public static Pair<Integer, Integer> findMinMax(List<Integer> A) {
    if (A.size() <= 1) {
      return new Pair<Integer, Integer>(A.get(0), A.get(0));
    }

    // Initialize the min and max pair.
    Pair<Integer, Integer> minMaxPair = Pair.minmax(A.get(0), A.get(1));
    for (int i = 2; i + 1 < A.size(); i += 2) {
      Pair<Integer, Integer> localPair = Pair.minmax(A.get(i), A.get(i + 1));
      minMaxPair = new Pair<Integer, Integer>(Math.min(minMaxPair.getFirst(),
          localPair.getFirst()), Math.max(minMaxPair.getSecond(),
          localPair.getSecond()));
    }
    // Special case: if there is odd number of elements in the array, we still
    // need to compare the last element with the existing answer.
    if ((A.size() & 1) != 0) {
      minMaxPair = new Pair<Integer, Integer>(Math.min(minMaxPair.getFirst(),
          A.get(A.size() - 1)), Math.max(minMaxPair.getSecond(),
          A.get(A.size() - 1)));
    }
    return minMaxPair;
  }

  // @exclude

  public static void main(String[] args) {
    Random r = new Random();
    for (int times = 0; times < 10000; ++times) {
      int n;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = r.nextInt(10000) + 1;
      }
      ArrayList<Integer> A = new ArrayList<Integer>();
      for (int i = 0; i < n; ++i) {
        A.add(r.nextInt(1000000));
      }
      Pair<Integer, Integer> res = findMinMax(A);
      assert (res.getFirst().equals(Collections.min(A)) && res.getSecond()
          .equals(Collections.max(A)));
    }

  }
}
