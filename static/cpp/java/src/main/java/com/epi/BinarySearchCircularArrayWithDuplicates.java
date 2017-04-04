package com.epi;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class BinarySearchCircularArrayWithDuplicates {
  // @include
  public static int searchSmallest(List<Integer> A) {
    return searchSmallestHelper(A, 0, A.size() - 1);
  }

  private static int searchSmallestHelper(List<Integer> A, int left,
                                          int right) {
    if (left == right) {
      return left;
    }

    int mid = left + ((right - left) / 2);
    if (A.get(mid) > A.get(right)) {
      return searchSmallestHelper(A, mid + 1, right);
    } else if (A.get(mid) < A.get(right)) {
      return searchSmallestHelper(A, left, mid);
    } else { // A.get(mid) == A.get(right)
      // We cannot eliminate either side so we compare the results from both
      // sides.
      int leftResult = searchSmallestHelper(A, left, mid);
      int rightResult = searchSmallestHelper(A, mid + 1, right);
      return A.get(rightResult) < A.get(leftResult) ? rightResult : leftResult;
    }
  }
  // @exclude

  private static void reverse(List<Integer> A, int a, int b) {
    for (int i = a, j = b; i < j; ++i, --j) {
      int temp = A.get(i);
      A.set(i, A.get(j));
      A.set(j, temp);
    }
  }

  // Hand-made tests
  private static void simpleTest() {
    List<Integer> A = Arrays.asList(3, 1, 2);
    assert(1 == searchSmallest(A));
    A = Arrays.asList(0, 2, 4, 8);
    assert(0 == searchSmallest(A));
    A = Arrays.asList(16, 2, 4, 8);
    assert(1 == searchSmallest(A));

    A = Arrays.asList(2, 2, 2);
    assert(0 == searchSmallest(A));
    A = Arrays.asList(100, 2, 5, 5);
    assert(1 == searchSmallest(A));
    A = Arrays.asList(1, 2, 3, 3, 3);
    assert(0 == searchSmallest(A));
    A = Arrays.asList(5, 2, 3, 3, 3);
    assert(1 == searchSmallest(A));
    A = Arrays.asList(5, 5, 2, 2, 2, 3, 3, 3);
    assert(2 == searchSmallest(A));
  }

  public static void main(String[] args) {
    simpleTest();
    Random r = new Random();
    for (int times = 0; times < 10000; ++times) {
      int n;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = r.nextInt(10000) + 1;
      }
      List<Integer> A = new ArrayList<>();
      for (int i = 0; i < n; i++) {
        A.add(r.nextInt(1000000));
      }
      Collections.sort(A);
      int shift = r.nextInt(n);
      reverse(A, 0, A.size() - 1);
      reverse(A, 0, shift);
      reverse(A, shift + 1, A.size() - 1);
      // System.out.println(A);
      assert((shift + 1) % n == searchSmallest(A));
    }
  }
}
