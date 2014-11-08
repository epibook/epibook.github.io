package com.epi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PascalTriangle1 {
  // @include
  public static List<List<Integer>> generatePascalTriangle(int n) {
    List<List<Integer>> result = new ArrayList<>();
    for (int i = 0; i < n; ++i) {
      List<Integer> currRow = new ArrayList<>();
      for (int j = 0; j < i + 1; ++j) {
        currRow.add(1);
      }
      for (int j = 1; j < i; ++j) {
        // Set this entry to the sum of the two above adjacent entries.
        currRow.set(j, result.get(i - 1).get(j - 1) + result.get(i - 1).get(j));
      }
      result.add(currRow);
    }
    return result;
  }
  // @exclude

  public static void main(String[] args) {
    Random r = new Random();
    int n = r.nextInt(11);
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
