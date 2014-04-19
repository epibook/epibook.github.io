package com.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PascalTriangle1 {
  // @include
  public static List<List<Integer>> generatePascalTriangle(int n) {
    if (n <= 0) {
      return Collections.emptyList();
    }

    List<Integer> prevRow = new ArrayList<Integer>();
    prevRow.add(1);
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    result.add(prevRow);
    for (int i = 1; i < n; ++i) {
      List<Integer> currRow = new ArrayList<Integer>();
      currRow.add(1); // for the first element.
      for (int j = 1; j < i; ++j) {
        currRow.add(prevRow.get(j - 1) + prevRow.get(j));
      }
      currRow.add(1); // for the last element.
      List<Integer> temp = prevRow; // swaps the contents of prevRow and
                                    // currRow.
      prevRow = currRow;
      currRow = temp;
      result.add(prevRow);
    }
    return result;
  }

  // @exclude

  public static void main(String[] args) {
    Random r = new Random();
    int n;
    if (args.length == 1) {
      n = Integer.parseInt(args[0]);
    } else {
      n = r.nextInt(11);
    }
    System.out.println("n = " + n);
    List<List<Integer>> res = generatePascalTriangle(n);
    for (int i = 0; i < res.size(); ++i) {
      for (int j = 0; j < res.get(i).size(); ++j) {
        System.out.print(res.get(i).get(j) + " ");
      }
      System.out.println();
    }
  }
}
