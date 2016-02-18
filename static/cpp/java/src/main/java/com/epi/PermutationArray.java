package com.epi;

import java.util.Arrays;
import java.util.Random;

import static com.epi.PermutationArray1.applyPermutation1;
import static com.epi.PermutationArray2.applyPermutation2;
import static com.epi.utils.Utils.shuffle;
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
      int[] A = new int[n], perm = new int[n];
      for (int i = 0; i < n; ++i) {
        A[i] = i;
        perm[i] = i;
      }

      // Knuth shuffle
      shuffle(perm);
      simplePrint(perm);
      System.out.println();

      int[] B = Arrays.copyOf(A, A.length);
      applyPermutation1(perm, B);
      simplePrint(B);
      System.out.println();

      int[] C = Arrays.copyOf(A, A.length);
      applyPermutation2(perm, C);
      simplePrint(C);
      System.out.println();

      // check answer by comparing the two permutations
      assert Arrays.equals(B, C);
      assert B.length == C.length;
    }
  }
}
