package com.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class BinarySearchCircularArrayWithDuplicates {
  // @include
  public static int searchSmallest(ArrayList<Integer> A) {
    return searchSmallestHelper(A, 0, A.size() - 1);
  }

  private static int searchSmallestHelper(ArrayList<Integer> A, int l, int r) {
    if (l == r) {
      return l;
    }

    int m = l + ((r - l) / 2);
    if (A.get(m) > A.get(r)) {
      return searchSmallestHelper(A, m + 1, r);
    } else if (A.get(m) < A.get(r)) {
      return searchSmallestHelper(A, l, m);
    } else { // A.get(m) == A.get(r)
      // Smallest element must exist in either left or right side.
      int lRes = searchSmallestHelper(A, l, m);
      int rRes = searchSmallestHelper(A, m + 1, r);
      return A.get(rRes) < A.get(lRes) ? rRes : lRes;
    }
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
      ArrayList<Integer> A = new ArrayList<>(n);
      for (int i = 0; i < n; i++) {
        A.add(r.nextInt(1000000));
      }
      Collections.sort(A);
      int shift = r.nextInt(n);
      Collections.reverse(A);
      Collections.reverse(A.subList(0, shift + 1));
      Collections.reverse(A.subList(shift + 1, A.size()));
      // System.out.println(A);
      assert ((shift + 1) % n == searchSmallest(A));
    }
    // hand-made tests
    ArrayList<Integer> A = new ArrayList<>();
    A.add(2);
    A.add(2);
    A.add(2);
    assert (0 == searchSmallest(A));
    A.clear();
    A.add(100);
    A.add(2);
    A.add(5);
    A.add(5);
    assert (1 == searchSmallest(A));
    A.clear();
    A.add(1);
    A.add(2);
    A.add(3);
    A.add(3);
    A.add(3);
    assert (0 == searchSmallest(A));
    A.clear();
    A.add(5);
    A.add(2);
    A.add(3);
    A.add(3);
    A.add(3);
    assert (1 == searchSmallest(A));
  }
}
