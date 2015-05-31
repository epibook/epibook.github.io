package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class NQueens {
  // @include
  public static List<List<Integer>> nQueens(int n) {
    List<Integer> placement = new ArrayList<>();
    List<List<Integer>> result = new ArrayList<>();
    solveNQueens(n, 0, placement, result);
    return result;
  }

  private static void solveNQueens(int n, int row, List<Integer> colPlacement,
                                   List<List<Integer>> result) {
    if (row == n) {
      // All queens are legally placed.
      result.add(new ArrayList<>(colPlacement));
    } else {
      for (int col = 0; col < n; ++col) {
        colPlacement.add(col);
        if (isValid(colPlacement)) {
          solveNQueens(n, row + 1, colPlacement, result);
        }
        colPlacement.remove(colPlacement.size() - 1);
      }
    }
  }

  // Test if a newly placed queen on rowID will conflict any earlier queens
  // placed before.
  private static boolean isValid(List<Integer> colPlacement) {
    int rowID = colPlacement.size() - 1;
    for (int i = 0; i < rowID; ++i) {
      int diff = Math.abs(colPlacement.get(i) - colPlacement.get(rowID));
      if (diff == 0 || diff == rowID - i) {
        return false;
      }
    }
    return true;
  }
  // @exclude

  private static List<String> toTextRepresentation(List<Integer> colPlacement) {
    List<String> sol = new ArrayList<>();
    for (int aColPlacement : colPlacement) {
      char[] line = new char[colPlacement.size()];
      Arrays.fill(line, '.');
      line[aColPlacement] = 'Q';
      sol.add(new String(line));
    }
    return sol;
  }

  public static void main(String[] args) {
    Random r = new Random();
    int n;
    if (args.length == 1) {
      n = Integer.parseInt(args[0]);
    } else {
      n = r.nextInt(15) + 1;
    }
    System.out.println("n = " + n);
    List<List<Integer>> result = nQueens(n);
    for (List<Integer> vec : result) {
      List<String> textRep = toTextRepresentation(vec);
      for (String s : textRep) {
        System.out.println(s);
      }
      System.out.println();
    }
  }
}
