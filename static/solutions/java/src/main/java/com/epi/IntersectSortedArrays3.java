package com.epi;

/*
    @slug
    intersect-two-sorted-arrays

    @title
    Intersect two sorted arrays.

    @problem
    Write a program which takes as input two sorted arrays, and returns a new
   array containing elements that are present in both of the input arrays. The
   input
   arrays may have duplicate entries, but the returned array should be free of
   duplicates.
    <p>

    For example, if the input is <2, 3, 3, 5, 5, 6, 7, 7, 8, 12> and
    <5, 5, 6, 8, 8, 9, 10, 10>, your output should be <5, 6, 8>.

    @hint
    Solve the problem if the input array lengths differ by orders of magnitude.
   What if they
    are approximately equal?

 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class IntersectSortedArrays3 {
  // @include
  // @judge-include-display
  public static List<Integer> intersectTwoSortedArrays(List<Integer> A,
                                                       List<Integer> B) {
    // @judge-exclude-display
    List<Integer> intersectionAB = new ArrayList<>();
    int i = 0, j = 0;
    while (i < A.size() && j < B.size()) {
      if (A.get(i) == B.get(j) && (i == 0 || A.get(i) != A.get(i - 1))) {
        intersectionAB.add(A.get(i));
        ++i;
        ++j;
      } else if (A.get(i) < B.get(j)) {
        ++i;
      } else { // A.get(i) > B.get(j).
        ++j;
      }
    }
    return intersectionAB;
    // @judge-include-display
  }
  // @judge-exclude-display
  // @exclude

  private static void check(List<Integer> A, List<Integer> B,
                            List<Integer> expected) {
    List<Integer> got = intersectTwoSortedArrays(A, B);
    if (!got.equals(expected)) {
      System.err.println("Incorrect intersection");
      System.err.println("A = " + A);
      System.err.println("B = " + B);
      System.err.println("Got " + got);
      System.err.println("Expected " + expected);
      System.exit(-1);
    }
  }

  public static void main(String[] args) {
    List<Integer> empty = Collections.emptyList();
    check(Arrays.asList(1, 2, 3, 4), Arrays.asList(1, 4, 5),
          Arrays.asList(1, 4));
    check(empty, Arrays.asList(1, 4, 5), empty);
    check(empty, empty, empty);
    check(Arrays.asList(1, 4, 5), empty, empty);
    check(Arrays.asList(1, 2, 2, 4), Arrays.asList(1, 2, 5),
          Arrays.asList(1, 2));
    check(Arrays.asList(1, 2, 3, 4), Arrays.asList(1, 4, 5),
          Arrays.asList(1, 4));
    check(Arrays.asList(1, 2, 3, 4), Arrays.asList(5, 6, 9), empty);
    check(Arrays.asList(1, 1, 1, 1), Arrays.asList(1, 1, 1), Arrays.asList(1));
    check(Arrays.asList(1, 1, 1, 2, 2), Arrays.asList(1, 1, 1, 1, 1, 2),
          Arrays.asList(1, 2));
    check(Arrays.asList(-2, 1, 1, 1, 2, 2),
          Arrays.asList(-1, 1, 1, 1, 1, 1, 2, 2), Arrays.asList(1, 2));

    int N = 100000000;
    List<Integer> bigList = new ArrayList<>(N);
    Collections.fill(bigList, 1);
    check(bigList, bigList, bigList);
    check(bigList, empty, empty);
  }
}
