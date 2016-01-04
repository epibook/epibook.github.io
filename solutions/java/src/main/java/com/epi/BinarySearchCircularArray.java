package com.epi;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class BinarySearchCircularArray {
  // @include
  public static int searchSmallest(List<Integer> A) {
    int left = 0, right = A.size() - 1;
    while (left < right) {
      int mid = left + ((right - left) / 2);
      if (A.get(mid) > A.get(right)) {
        // Minimum must be in A.subList(mid + 1, right + 1).
        left = mid + 1;
      } else { // A.get(mid) < A.get(right).
        // Minimum cannot be in A.subList(mid + 1, right + 1) so it must be in
        // A.sublist(left, mid + 1).
        right = mid;
      }
    }
    // Loop ends when left == right.
    return left;
  }
  // @exclude

  private static void reverse(List<Integer> A, int a, int b) {
    for (int i = a, j = b; i < j; ++i, --j) {
      int temp = A.get(i);
      A.set(i, A.get(j));
      A.set(j, temp);
    }
  }

  private static void simpleTest() {
    List<Integer> A = Arrays.asList(3, 1, 2);
    assert(1 == searchSmallest(A));
    A = Arrays.asList(0, 2, 4, 8);
    assert(0 == searchSmallest(A));
    A = Arrays.asList(16, 2, 4, 8);
    assert(1 == searchSmallest(A));
  }

  public static void main(String[] args) {
    simpleTest();
    Random r = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = r.nextInt(10000) + 1;
      }
      List<Integer> A = new ArrayList<>();
      Set<Integer> table = new HashSet<>();
      for (int i = 0; i < n; ++i) {
        while (true) {
          int x = r.nextInt(100001);
          if (table.add(x)) {
            A.add(x);
            break;
          }
        }
      }
      Collections.sort(A);
      int shift = r.nextInt(n);
      reverse(A, 0, A.size() - 1);
      reverse(A, 0, shift);
      reverse(A, shift + 1, A.size() - 1);
      // System.out.println(A);
      assert((shift + 1) % n == searchSmallest(A));
    }
    // hand-made tests.
    List<Integer> A = Arrays.asList(2, 3, 4);
    assert(0 == searchSmallest(A));
    A = Arrays.asList(100, 101, 102, 2, 5);
    assert(3 == searchSmallest(A));
    A = Arrays.asList(10, 20, 30, 40, 5);
    assert(4 == searchSmallest(A));
  }
}
