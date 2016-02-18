package com.epi;

/*
    @slug
    first-occurence-of-k

    @title
    Search a sorted array for first occurrence of a key

    @problem
    Write a method that takes a sorted array and a key and returns the index of
   the
    first occurrence of that key in the array. For example, when applied to the
   array in
    the figure, your algorithm should return 3 if the given key is 108; if it is
   285, your
    algorithm should return 6.
    <p>

    <img src="/bin-search-first.png" width="500px"></img>
    <p>

    @hint
    What happens when every entry equals k? Don't stop when you first see k.

 */

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class BinarySearchFirstK {
  // @include
  // @judge-include-display
  public static int searchFirstOfK(List<Integer> A, int k) {
    // @judge-exclude-display
    int left = 0, right = A.size() - 1, result = -1;
    // A.subList(left, right + 1) is the candidate set.
    while (left <= right) {
      int mid = left + ((right - left) / 2);
      if (A.get(mid) > k) {
        right = mid - 1;
      } else if (A.get(mid) == k) {
        result = mid;
        // Nothing to the right of mid can be the first occurrence of k.
        right = mid - 1;
      } else { // A.get(mid) < k
        left = mid + 1;
      }
    }
    return result;
    // @judge-include-display
  }
  // @judge-exclude-display
  // @exclude

  private static int checkAns(List<Integer> A, int k) {
    for (int i = 0; i < A.size() && A.get(i) <= k; ++i) {
      if (A.get(i) == k) {
        return i;
      }
    }
    return -1;
  }

  private static void check(int expected, int got, List<Integer> A, int key) {
    if (expected != got) {
      System.err.println("Failed looking for " + key + " in array " + A);
      System.err.println("Got " + got + ", expected " + expected);
      System.exit(-1);
    }
  }

  private static void simpleTest() {
    List<Integer> A = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7);
    check(0, searchFirstOfK(A, 0), A, 0);
    check(1, searchFirstOfK(A, 1), A, 1);
    check(4, searchFirstOfK(A, 4), A, 4);
    check(6, searchFirstOfK(A, 6), A, 6);
    check(7, searchFirstOfK(A, 7), A, 7);
    check(-1, searchFirstOfK(A, 8), A, 8);
    check(-1, searchFirstOfK(A, Integer.MIN_VALUE), A, Integer.MIN_VALUE);
    A = Arrays.asList(1, 1, 2, 3, 4, 5, 6, 7);
    check(0, searchFirstOfK(A, 1), A, 1);
    A = Arrays.asList(1, 1, 2, 3, 4, 4, 4, 7);
    check(4, searchFirstOfK(A, 4), A, 4);
    A = Arrays.asList(1, 1, 1, 1, 1, 2);
    check(-1, searchFirstOfK(A, 0), A, 0);
    check(0, searchFirstOfK(A, 1), A, 0);
    check(5, searchFirstOfK(A, 2), A, 5);
    A = Arrays.asList(1, 1, 1, 1, 2, 2);
    check(4, searchFirstOfK(A, 2), A, 2);
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
      int k = r.nextInt(n);
      for (int i = 0; i < n; ++i) {
        A.add(r.nextInt(n));
      }
      Collections.sort(A);
      int ans = searchFirstOfK(A, k);
      System.out.println("k = " + k + " locates at " + ans);
      if (ans != -1) {
        System.out.println("A[k] = " + A.get(ans));
      }
      assert(checkAns(A, k) == ans);
    }
  }
}
