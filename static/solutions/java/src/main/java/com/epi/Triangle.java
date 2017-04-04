package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Triangle {
  // @include
  public static int minimumPathTotal(List<List<Integer>> triangle) {
    if (triangle.isEmpty()) {
      return 0;
    }

    // As we iterate, prevRow stores the minimum path sum to each entry in
    // triangle.get(i - 1).
    List<Integer> prevRow = new ArrayList<>(triangle.get(0));
    for (int i = 1; i < triangle.size(); ++i) {
      // Stores the minimum path sum to each entry in triangle.get(i).
      List<Integer> currRow = new ArrayList<>(triangle.get(i));
      // For the first element.
      currRow.set(0, currRow.get(0) + prevRow.get(0));
      for (int j = 1; j < currRow.size() - 1; ++j) {
        currRow.set(
            j, currRow.get(j) + Math.min(prevRow.get(j - 1), prevRow.get(j)));
      }
      // For the last element
      currRow.set(currRow.size() - 1, currRow.get(currRow.size() - 1)
                                          + prevRow.get(prevRow.size() - 1));

      prevRow = currRow;
    }
    return Collections.min(prevRow);
  }
  // @exclude

  public static void main(String[] args) {
    List<List<Integer>> A
        = Arrays.asList(Arrays.asList(2), Arrays.asList(3, 4),
                        Arrays.asList(6, 5, 7), Arrays.asList(4, 1, 8, 3));
    assert(11 == minimumPathTotal(A));
  }
}
