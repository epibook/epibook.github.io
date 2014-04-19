package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class NQueens {
  // @include
  public static List<List<String>> nQueens(int n) {
    int[] placement = new int[n];
    List<List<String>> res = new ArrayList<List<String>>();
    nQueensHelper(n, 0, placement, res);
    return res;
  }

  private static void nQueensHelper(int n, int row, int[] colPlacement,
      List<List<String>> res) {
    if (row == n) {
      List<String> sol = new ArrayList<String>();
      for (int i = 0; i < colPlacement.length; ++i) {
        char[] line = new char[n];
        Arrays.fill(line, '.');
        line[colPlacement[i]] = 'Q';
        sol.add(new String(line));
      }
      res.add(sol);
    } else {
      for (int col = 0; col < n; ++col) {
        colPlacement[row] = col;
        if (isFeasible(colPlacement, row)) {
          nQueensHelper(n, row + 1, colPlacement, res);
        }
      }
    }
  }

  private static boolean isFeasible(int[] colPlacement, int row) {
    for (int i = 0; i < row; ++i) {
      int diff = Math.abs(colPlacement[i] - colPlacement[row]);
      if (diff == 0 || diff == row - i) {
        return false;
      }
    }
    return true;
  }

  // @exclude

  public static void main(String[] args) {
    Random r = new Random();
    int n;
    if (args.length == 1) {
      n = Integer.parseInt(args[0]);
    } else {
      n = r.nextInt(15) + 1;
    }
    System.out.println("n = " + n);
    List<List<String>> res = nQueens(n);
    for (List<String> vec : res) {
      for (String s : vec) {
        System.out.println(s);
      }
      System.out.println();
    }

  }
}
