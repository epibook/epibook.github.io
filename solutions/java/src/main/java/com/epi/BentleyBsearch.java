package com.epi;

import java.util.ArrayList;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class BentleyBsearch {
  // @include
  public static int bsearch(int t, ArrayList<Integer> A) {
    int L = 0, U = A.size() - 1;
    while (L <= U) {
      int M = (L + U) / 2;
      if (A.get(M) < t) {
        L = M + 1;
      } else if (A.get(M) == t) {
        return M;
      } else {
        U = M - 1;
      }
    }
    return -1;
  }
  // @exclude

  public static void main(String[] args) {
    ArrayList<Integer> A = new ArrayList<>();
    A.add(1);
    A.add(2);
    A.add(2);
    A.add(2);
    A.add(2);
    A.add(3);
    A.add(3);
    A.add(3);
    A.add(5);
    A.add(6);
    A.add(10);
    A.add(100);
    assert(0 == bsearch(1, A));
    assert(1 <= bsearch(2, A) && bsearch(2, A) <= 4);
    assert(5 <= bsearch(3, A));
    assert(-1 == bsearch(4, A));
  }
}
