package com.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class BinarySearchLargerKTemplate {
  // @include
  public static int searchFirstLargerK(ArrayList<Integer> a, int k) {
    int l = 0, r = a.size() - 1, res = -1;
    while (l <= r) {
      int m = l + ((r - l) >> 1);
      if (a.get(m) > k) {
        // Records the solution and keep searching the left part.
        res = m;
        r = m - 1;
      } else { // A.get(m) <= k.
        l = m + 1;
      }
    }
    return res;
  }

  // @exclude
  private static int checkAns(ArrayList<Integer> a, int k) {
    for (int i = 0; i < a.size(); ++i) {
      if (a.get(i) > k) {
        return i;
      }
    }
    return -1;
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
      ArrayList<Integer> A = new ArrayList<Integer>();
      for (int i = 0; i < n; ++i) {
        A.add(r.nextInt(n));
      }
      Collections.sort(A);
      int k = r.nextInt(n);
      int ans = searchFirstLargerK(A, k);
      System.out.println("k = " + k + " locates at " + ans);
      if (ans != -1) {
        System.out.println("A[k] = " + A.get(ans));
      }
      assert (ans == checkAns(A, k));
    }
  }
}
