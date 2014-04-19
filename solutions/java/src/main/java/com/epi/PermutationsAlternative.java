package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PermutationsAlternative {
  // @include
  public static List<List<Integer>> permutations(List<Integer> A) {
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    permutationsHelper(A, 0, res);
    return res;
  }

  private static void permutationsHelper(List<Integer> A, int i,
      List<List<Integer>> res) {
    if (i == A.size()) {
      res.add(new ArrayList<Integer>(A));
      return;
    }

    for (int j = i; j < A.size(); ++j) {
      Collections.swap(A, i, j);
      permutationsHelper(A, i + 1, res);
      Collections.swap(A, i, j);
    }
  }

  // @exclude

  private static void smallTest() {
    List<Integer> A = new ArrayList<Integer>() {
      {
        add(0);
        add(1);
        add(2);
      }
    };
    List<List<Integer>> res = permutations(A);
    assert (res.size() == 6);
    List<List<Integer>> goldenRes = Arrays.asList(Arrays.asList(0, 1, 2),
        Arrays.asList(0, 2, 1), Arrays.asList(1, 0, 2), Arrays.asList(1, 2, 0),
        Arrays.asList(2, 1, 0), Arrays.asList(2, 0, 1));
    assert (res.equals(goldenRes));
  }

  public static void main(String[] args) {
    smallTest();
    Random r = new Random();
    int n;
    if (args.length == 1) {
      n = Integer.parseInt(args[0]);
    } else {
      n = r.nextInt(10) + 1;
    }
    List<Integer> A = new ArrayList<Integer>(n);
    int val = 0;
    for (int i = 0; i < n; i++) {
      A.add(val++);
    }
    List<List<Integer>> res = permutations(A);
    System.out.println("n = " + n);
    for (List<Integer> vec : res) {
      System.out.println(vec);
    }
  }
}
