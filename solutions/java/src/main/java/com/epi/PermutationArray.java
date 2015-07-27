package com.epi;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static com.epi.utils.Utils.simplePrint;

public class PermutationArray {
  public static void main(String[] args) {
    Random gen = new Random();
    int n;
    for (int times = 0; times < 1000; ++times) {
      if (args.length == 1) {
        n = Integer.valueOf(args[0]);
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
      simplePrint(perm);
      System.out.println();

      List<Integer> B = new ArrayList<>(A);
      PermutationArray1.applyPermutation(perm, B);
      simplePrint(B);
      System.out.println();

      List<Integer> C = new ArrayList<>(A);
      PermutationArray2.applyPermutation(perm, C);
      simplePrint(C);
      System.out.println();

      // check answer by comparing the two permutations
      assert(B.equals(C));
    }
  }
}
