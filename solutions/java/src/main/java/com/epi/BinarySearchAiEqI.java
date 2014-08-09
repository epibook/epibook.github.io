package com.epi;

import java.util.*;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class BinarySearchAiEqI {
  // @include
  public static int searchIndexValueEqual(ArrayList<Integer> A) {
    int l = 0, r = A.size() - 1;
    while (l <= r) {
      int m = l + ((r - l) / 2);
      int val = A.get(m) - m;
      if (val == 0) {
        return m;
      } else if (val > 0) {
        r = m - 1;
      } else { // val < 0
        l = m + 1;
      }
    }
    return -1;
  }
  // @exclude

  // O(n) way to find ans.
  private static int checkAns(ArrayList<Integer> A) {
    int ret = -1;
    for (int i = 0; i < A.size(); ++i) {
      if (A.get(i).equals(i)) {
        return i;
      }
    }
    return ret;
  }

  public static void main(String[] args) {
    Random r = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n;
      ArrayList<Integer> A = new ArrayList<>();
      Set<Integer> table = new HashSet<>();
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = r.nextInt(1000) + 1;
      }
      for (int i = 0; i < n; ++i) {
        int x;
        do {
          x = r.nextInt(1999) - 999;
        } while (table.contains(x));
        table.add(x);
        A.add(x);
      }
      Collections.sort(A);
      int ans = searchIndexValueEqual(A);
      if (ans != -1) {
        System.out.println("A[" + ans + "] = " + A.get(ans));
        assert (A.get(ans).equals(ans));
      } else {
        System.out.println("no entry where A[k] = k");
        assert (checkAns(A) == -1);
      }
    }
  }

}
