package com.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MaxSubmatrixSquare {
  // O(m^3 n^3) time solution.
  private static int checkAns(List<List<Boolean>> A) {
    int max = 0;
    for (int a = 0; a < A.size(); ++a) {
      for (int b = 0; b < A.get(a).size(); ++b) {
        for (int r = 1; a + r <= A.size() && b + r <= A.get(0).size(); ++r) {
          int count = 0;
          boolean all1 = true;
          for (int c = a; c < a + r; ++c) {
            for (int d = b; d < b + r; ++d) {
              if (A.get(c).get(d)) {
                ++count;
              } else {
                all1 = false;
                count = 0;
                break;
              }
            }
            if (!all1) {
              break;
            }
          }
          if (count > max) {
            max = count;
          }
        }
      }
    }
    return max;
  }

  // @include
  public static class MaxHW {
    public int h, w;

    public MaxHW(int h, int w) {
      this.h = h;
      this.w = w;
    }
  }

  public static int maxSquareSubmatrix(List<List<Boolean>> A) {
    // DP table stores (h, w) for each (i, j).
    List<List<MaxHW>> table = new ArrayList<>(A.size());
    for (int i = 0; i < A.size(); ++i) {
      table.add(
          new ArrayList(Collections.nCopies(A.get(i).size(), new MaxHW(0, 0))));
    }

    for (int i = A.size() - 1; i >= 0; --i) {
      for (int j = A.get(i).size() - 1; j >= 0; --j) {
        // Find the largest h such that (i, j) to (i + h - 1, j) are feasible.
        // Find the largest w such that (i, j) to (i, j + w - 1) are feasible.
        table.get(i).set(
            j, A.get(i).get(j)
                   ? new MaxHW(
                         i + 1 < A.size() ? table.get(i + 1).get(j).h + 1 : 1,
                         j + 1 < A.get(i).size() ? table.get(i).get(j + 1).w + 1
                                                 : 1)
                   : new MaxHW(0, 0));
      }
    }

    // A table stores the length of the largest square for each (i, j).
    List<List<Integer>> s = new ArrayList<>(A.size());
    for (int i = 0; i < A.size(); ++i) {
      s.add(new ArrayList(Collections.nCopies(A.get(i).size(), 0)));
    }
    int maxSquareArea = 0;
    for (int i = A.size() - 1; i >= 0; --i) {
      for (int j = A.get(i).size() - 1; j >= 0; --j) {
        int side = Math.min(table.get(i).get(j).h, table.get(i).get(j).w);
        if (A.get(i).get(j)) {
          // Get the length of largest square with bottom-left corner (i, j).
          if (i + 1 < A.size() && j + 1 < A.get(i + 1).size()) {
            side = Math.min(s.get(i + 1).get(j + 1) + 1, side);
          }
          s.get(i).set(j, side);
          maxSquareArea = Math.max(maxSquareArea, side * side);
        }
      }
    }
    return maxSquareArea;
  }
  // @exclude

  public static void main(String[] args) {
    Random r = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n, m;
      if (args.length == 2) {
        n = Integer.parseInt(args[0]);
        m = Integer.parseInt(args[1]);
      } else {
        n = r.nextInt(50) + 1;
        m = r.nextInt(50) + 1;
      }

      List<List<Boolean>> A = new ArrayList<>(n);
      for (int i = 0; i < n; ++i) {
        List<Boolean> last = new ArrayList<>(m);
        A.add(last);
        for (int j = 0; j < m; ++j) {
          last.add(r.nextBoolean());
        }
      }
      // System.out.println(A);
      System.out.println(maxSquareSubmatrix(A));
      System.out.println(checkAns(A));
      assert(checkAns(A) == maxSquareSubmatrix(A));
    }
  }
}
