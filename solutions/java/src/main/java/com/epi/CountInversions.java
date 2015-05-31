package com.epi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CountInversions {
  // @include
  public static int countInversions(List<Integer> A) {
    return countSubarrayInversions(A, 0, A.size());
  }

  // Return the number of inversions in A[start : end - 1].
  private static int countSubarrayInversions(List<Integer> A, int start,
                                             int end) {
    if (end - start <= 1) {
      return 0;
    }

    int mid = start + ((end - start) / 2);
    return countSubarrayInversions(A, start, mid) +
        countSubarrayInversions(A, mid, end) +
        mergeSortAndCountInversionsAcrossSubarrays(A, start, mid, end);
  }

  // Merge two sorted subarrays A[start : mid - 1] and A[mid : end - 1]
  // into A[start : end - 1] and return the number of inversions across
  // A[start : mid - 1] and A[mid : end - 1].
  private static int mergeSortAndCountInversionsAcrossSubarrays(List<Integer> A,
                                                                int start,
                                                                int mid,
                                                                int end) {
    List<Integer> sortedA = new ArrayList<>();
    int leftStart = start, rightStart = mid, inversionCount = 0;

    while (leftStart < mid && rightStart < end) {
      if (A.get(leftStart).compareTo(A.get(rightStart)) <= 0) {
        sortedA.add(A.get(leftStart++));
      } else {
        // A[leftStart : mid - 1] are the inversions of A[rightStart].
        inversionCount += mid - leftStart;
        sortedA.add(A.get(rightStart++));
      }
    }
    sortedA.addAll(A.subList(leftStart, mid));
    sortedA.addAll(A.subList(rightStart, end));

    // Updates A with sortedA.
    for (Integer t : sortedA) {
      A.set(start++, t);
    }
    return inversionCount;
  }
  // @exclude

  // O(n^2) check of inversions.
  private static <T extends Comparable<T>> int n2Check(List<T> a) {
    int count = 0;
    for (int i = 0; i < a.size(); ++i) {
      for (int j = i + 1; j < a.size(); ++j) {
        if (a.get(i).compareTo(a.get(j)) > 0) {
          ++count;
        }
      }
    }
    System.out.println(count);
    return count;
  }

  public static void main(String[] args) {
    Random r = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = r.nextInt(10000) + 1;
      }
      List<Integer> A = new ArrayList<>();
      for (int i = 0; i < n; ++i) {
        A.add(r.nextInt(2000001) - 1000000);
      }
      assert(n2Check(A) == countInversions(A));
    }
  }
}
