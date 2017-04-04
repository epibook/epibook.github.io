package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PascalTriangle1 {
  // @include
  public static List<List<Integer>> generatePascalTriangle(int numRows) {
    List<List<Integer>> pascalTriangle = new ArrayList<>();
    for (int i = 0; i < numRows; ++i) {
      List<Integer> currRow = new ArrayList<>();
      for (int j = 0; j <= i; ++j) {
        // Set this entry to the sum of the two above adjacent entries if they
        // exist.
        currRow.add((0 < j && j < i)
                        ? pascalTriangle.get(i - 1).get(j - 1)
                              + pascalTriangle.get(i - 1).get(j)
                        : 1);
      }
      pascalTriangle.add(currRow);
    }
    return pascalTriangle;
  }
  // @exclude

  private static void smallTest() {
    List<List<Integer>> result = generatePascalTriangle(3);
    List<List<Integer>> goldenResult = Arrays.asList(
        Arrays.asList(1), Arrays.asList(1, 1), Arrays.asList(1, 2, 1));
    result.equals(goldenResult);
  }

  public static void main(String[] args) {
    smallTest();
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
