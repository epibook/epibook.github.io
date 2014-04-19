package com.epi;

import java.util.ArrayList;
import java.util.List;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class LongestNondecreasingSubsequenceN2 {
  // @include
  public static List<Integer> longestNondecreasingSubsequence(List<Integer> A) {
    // Empty array.
    if (A.isEmpty()) {
      return A;
    }

    ArrayList<Integer> longest = new ArrayList<Integer>(A.size());
    ArrayList<Integer> previousIndex = new ArrayList<Integer>(A.size());
    for (int i = 0; i < A.size(); i++) {
      longest.add(1);
      previousIndex.add(-1);
    }

    int maxLengthIdx = 0;
    for (int i = 1; i < A.size(); ++i) {
      for (int j = 0; j < i; ++j) {
        if (A.get(i) >= A.get(j) && longest.get(j) + 1 > longest.get(i)) {
          longest.set(i, longest.get(j) + 1);
          previousIndex.set(i, j);
        }
      }
      // Record the index where longest subsequence ends.
      if (longest.get(i) > longest.get(maxLengthIdx)) {
        maxLengthIdx = i;
      }
    }

    // Build the longest nondecreasing subsequence.
    int maxLength = longest.get(maxLengthIdx);
    ArrayList<Integer> ret = new ArrayList<Integer>(maxLength);
    while (maxLength-- > 0) {
      ret.add(0, A.get(maxLengthIdx));
      maxLengthIdx = previousIndex.get(maxLengthIdx);
    }
    return ret;
  }
  // @exclude
}
