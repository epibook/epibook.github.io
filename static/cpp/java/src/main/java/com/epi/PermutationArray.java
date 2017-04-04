package com.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PermutationArray {
  public static void main(String[] args) {
    Random gen = new Random();
    int n;
    for (int times = 0; times < 1000; ++times) {
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = gen.nextInt(100) + 1;
      }
      List<Integer> A = new ArrayList<>(n), perm = new ArrayList<>(n);
      for (int i = 0; i < n; ++i) {
        A.add(i);
        perm.add(i);
      }

      // Knuth shuffle
      Collections.shuffle(perm);
      System.out.println("perm = " + perm);

      List<Integer> B = new ArrayList<>(A);
      PermutationArray1.applyPermutation(perm, B);
      System.out.println(B);

      List<Integer> C = new ArrayList<>(A);
      PermutationArray2.applyPermutation(perm, C);
      System.out.println(C);

      // check answer by comparing the two permutations
      assert(B.equals(C));
    }
  }
}
