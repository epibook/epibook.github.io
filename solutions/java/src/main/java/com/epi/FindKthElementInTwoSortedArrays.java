package com.epi;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class FindKthElementInTwoSortedArrays {
  // @include
  public static int findKthNTwoSortedArrays(List<Integer> A, List<Integer> B,
                                            int k) {
    // Lower bound of elements we will choose in A.
    int b = Math.max(0, k - B.size());
    // Upper bound of elements we will choose in A.
    int t = Math.min(A.size(), k);

    while (b < t) {
      int x = b + ((t - b) / 2);
      int ax1 = (x <= 0 ? Integer.MIN_VALUE : A.get(x - 1));
      int ax = (x >= A.size() ? Integer.MAX_VALUE : A.get(x));
      int bkx1 = (k - x <= 0 ? Integer.MIN_VALUE : B.get(k - x - 1));
      int bkx = (k - x >= B.size() ? Integer.MAX_VALUE : B.get(k - x));

      if (ax < bkx1) {
        b = x + 1;
      } else if (ax1 > bkx) {
        t = x - 1;
      } else {
        // B.get(k - x - 1) <= A.get(x) && A.get(x - 1) < B.get(k - x).
        return Math.max(ax1, bkx1);
      }
    }

    int ab1 = b <= 0 ? Integer.MIN_VALUE : A.get(b - 1);
    int bkb1 = k - b - 1 < 0 ? Integer.MIN_VALUE : B.get(k - b - 1);
    return Math.max(ab1, bkb1);
  }
  // @exclude

  private static int checkAnswer(List<Integer> A, List<Integer> B, int k) {
    int i = 0, j = 0, count = 0;
    int ret = -1;
    while ((i < A.size() || j < B.size()) && count < k) {
      if (i < A.size() && j < B.size()) {
        if (A.get(i) < B.get(j)) {
          ret = A.get(i);
          ++i;
        } else {
          ret = B.get(j);
          ++j;
        }
      } else if (i < A.size()) {
        ret = A.get(i);
        ++i;
      } else {
        ret = B.get(j);
        ++j;
      }
      ++count;
    }
    return ret;
  }

  private static void smallTest() {
    // AA: handwritten test
    List<Integer> a0 = Arrays.asList(0, 1, 2, 3);
    List<Integer> b0 = Arrays.asList(0, 1, 2, 3);
    assert(0 == findKthNTwoSortedArrays(a0, b0, 1));
    assert(0 == findKthNTwoSortedArrays(a0, b0, 2));
    assert(1 == findKthNTwoSortedArrays(a0, b0, 3));
    assert(1 == findKthNTwoSortedArrays(a0, b0, 4));
    assert(2 == findKthNTwoSortedArrays(a0, b0, 5));
  }

  public static void main(String[] args) {
    smallTest();
    Random r = new Random();
    // Random test 10000 times.
    for (int times = 0; times < 10000; ++times) {
      int n, m, k;
      if (args.length == 2) {
        n = Integer.parseInt(args[0]);
        m = Integer.parseInt(args[1]);
        k = r.nextInt(n + m) + 1;
      } else if (args.length == 3) {
        n = Integer.parseInt(args[0]);
        m = Integer.parseInt(args[1]);
        k = Integer.parseInt(args[2]);
      } else {
        n = r.nextInt(10000) + 1;
        m = r.nextInt(10000) + 1;
        k = r.nextInt(n + m) + 1;
      }
      List<Integer> A = new ArrayList<>(n);
      List<Integer> B = new ArrayList<>(m);
      for (int i = 0; i < n; ++i) {
        A.add(r.nextInt(100000));
      }
      for (int i = 0; i < m; ++i) {
        B.add(r.nextInt(100000));
      }
      Collections.sort(A);
      Collections.sort(B);
      /*
       * System.out.println(A); System.out.println(B);
       */
      int ans = findKthNTwoSortedArrays(A, B, k);
      System.out.println(k + "th = " + ans);
      assert(ans == checkAnswer(A, B, k));
    }
  }
}
