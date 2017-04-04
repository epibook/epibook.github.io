package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class NQueens {
  // @include
  public static List<List<Integer>> nQueens(int n) {
    List<List<Integer>> result = new ArrayList<>();
    solveNQueens(n, 0, new ArrayList<Integer>(), result);
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

  // Test if a newly placed queen will conflict any earlier queens
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

  private static void simpleTest() {
    List<List<Integer>> result = nQueens(2);
    assert(0 == result.size());

    result = nQueens(3);
    assert(0 == result.size());

    result = nQueens(4);
    assert(2 == result.size());
    assert(result.get(0).equals(Arrays.asList(1, 3, 0, 2))
           || result.get(0).equals(Arrays.asList(2, 0, 3, 1)));
    assert(result.get(1).equals(Arrays.asList(1, 3, 0, 2))
           || result.get(1).equals(Arrays.asList(2, 0, 3, 1)));
    assert(!result.get(0).equals(result.get(1)));
  }

  public static void main(String[] args) {
    simpleTest();
    Random r = new Random();
    int n;
    if (args.length == 1) {
      n = Integer.parseInt(args[0]);
    } else {
      n = r.nextInt(10) + 1;
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
