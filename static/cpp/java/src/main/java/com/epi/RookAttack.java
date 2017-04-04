package com.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RookAttack {
  // @include
  public static void rookAttack(List<List<Integer>> A) {
    int m = A.size(), n = A.get(0).size();
    boolean hasFirstRowZero = false;
    for (int j = 0; j < n; ++j) {
      if (A.get(0).get(j) == 0) {
        hasFirstRowZero = true;
        break;
      }
    }
    boolean hasFirstColumnZero = false;
    for (int i = 0; i < m; ++i) {
      if (A.get(i).get(0) == 0) {
        hasFirstColumnZero = true;
        break;
      }
    }

    for (int i = 1; i < m; ++i) {
      for (int j = 1; j < n; ++j) {
        if (A.get(i).get(j) == 0) {
          A.get(i).set(0, 0);
          A.get(0).set(j, 0);
        }
      }
    }

    for (int i = 1; i < m; ++i) {
      if (A.get(i).get(0) == 0) {
        Collections.fill(A.get(i), 0);
      }
    }

    for (int j = 1; j < n; ++j) {
      if (A.get(0).get(j) == 0) {
        for (int i = 1; i < m; ++i) {
          A.get(i).set(j, 0);
        }
      }
    }

    if (hasFirstRowZero) {
      Collections.fill(A.get(0), 0);
    }
    if (hasFirstColumnZero) {
      for (int i = 0; i < m; ++i) {
        A.get(i).set(0, 0);
      }
    }
  }
  // @exclude

  private static void checkAns(List<List<Integer>> A, List<List<Integer>> ans) {
    for (int i = 0; i < A.size(); ++i) {
      for (int j = 0; j < A.get(i).size(); ++j) {
        if (A.get(i).get(j) == 0) {
          for (List<Integer> an : ans) {
            assert(an.get(j) == 0);
          }
          for (int k = 0; k < ans.get(i).size(); ++k) {
            assert(ans.get(i).get(k) == 0);
          }
        }
      }
    }
  }

  public static void main(String[] args) {
    Random r = new Random();
    for (int times = 0; times < 1000; ++times) {
      int m, n;
      if (args.length == 2) {
        m = Integer.parseInt(args[0]);
        n = Integer.parseInt(args[1]);
      } else {
        m = r.nextInt(50) + 1;
        n = r.nextInt(50) + 1;
      }
      List<List<Integer>> A = new ArrayList<>(m);
      for (int i = 0; i < m; ++i) {
        A.add(new ArrayList(n));
        for (int j = 0; j < n; ++j) {
          A.get(i).add(r.nextInt(2));
        }
      }
      List<List<Integer>> copyA = new ArrayList<>(m);
      for (List<Integer> a : A) {
        copyA.add(new ArrayList(a));
      }
      System.out.println("m = " + m + ", n = " + n);
      rookAttack(A);
      checkAns(copyA, A);
    }
  }
}
