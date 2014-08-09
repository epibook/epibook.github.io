package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class NQueens {
  // @include
  public static List<List<String>> nQueens(int n) {
    int[] placement = new int[n];
    List<List<String>> result = new ArrayList<>();
    nQueensHelper(n, 0, placement, result);
    return result;
  }

  private static void nQueensHelper(int n, int row, int[] colPlacement,
                                    List<List<String>> result) {
    if (row == n) {
      result.add(createOutput(colPlacement));
    } else {
      for (int col = 0; col < n; ++col) {
        colPlacement[row] = col;
        if (isFeasible(colPlacement, row)) {
          nQueensHelper(n, row + 1, colPlacement, result);
        }
      }
    }
  }

  private static List<String> createOutput(int[] colPlacement) {
    List<String> sol = new ArrayList<>();
    for (int aColPlacement : colPlacement) {
      char[] line = new char[colPlacement.length];
      Arrays.fill(line, '.');
      line[aColPlacement] = 'Q';
      sol.add(new String(line));
    }
    return sol;
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
    List<List<String>> result = nQueens(n);
    for (List<String> vec : result) {
      for (String s : vec) {
        System.out.println(s);
      }
      System.out.println();
    }

  }
}
