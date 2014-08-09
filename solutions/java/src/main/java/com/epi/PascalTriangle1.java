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

    List<Integer> prevRow = new ArrayList<>();
    prevRow.add(1);
    List<List<Integer>> result = new ArrayList<>();
    result.add(prevRow);
    for (int i = 1; i < n; ++i) {
      List<Integer> currRow = new ArrayList<>();
      currRow.add(1); // For the first element.
      for (int j = 1; j < i; ++j) {
        // Set this entry to the sum of the two above adjacent entries.
        currRow.add(prevRow.get(j - 1) + prevRow.get(j));
      }
      currRow.add(1); // For the last element.
      // Swaps the contents of prevRow and currRow.
      List<Integer> temp = prevRow;
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
    List<List<Integer>> result = generatePascalTriangle(n);
    for (List<Integer> re : result) {
      for (Integer aRe : re) {
        System.out.print(aRe + " ");
      }
      System.out.println();
    }
  }
}
