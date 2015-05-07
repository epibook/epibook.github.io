package com.epi;

import java.util.ArrayList;
import java.util.List;

public class LongestNondecreasingSubsequenceN2 {
  // @include
  public static List<Integer> longestNondecreasingSubsequence(List<Integer> A) {
    // Empty array.
    if (A.isEmpty()) {
      return A;
    }

    List<Integer> longest = new ArrayList<>(A.size());
    List<Integer> previousIndex = new ArrayList<>(A.size());
    for (Integer aA : A) {
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
      // Records the index where longest subsequence ends.
      if (longest.get(i) > longest.get(maxLengthIdx)) {
        maxLengthIdx = i;
      }
    }

    // Builds the longest nondecreasing subsequence.
    int maxLength = longest.get(maxLengthIdx);
    List<Integer> ret = new ArrayList<>(maxLength);
    while (maxLength-- > 0) {
      ret.add(0, A.get(maxLengthIdx));
      maxLengthIdx = previousIndex.get(maxLengthIdx);
    }
    return ret;
  }
  // @exclude
}
