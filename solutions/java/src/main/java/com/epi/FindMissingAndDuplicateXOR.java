package com.epi;

import com.epi.utils.Pair;

import java.util.Random;

public class FindMissingAndDuplicateXOR {
  // @include
  // Returns Pair<Integer, Integer>(duplicate, missing)
  public static Pair<Integer, Integer> findDuplicateMissing(int[] A) {
    int missXORDup = 0;
    for (int i = 0; i < A.length; ++i) {
      missXORDup ^= i ^ A[i];
    }

    // We need to find a bit that's set to 1 in missXORDup. This assignment
    // sets all of bits in differBit to 0 except for the least significant
    // bit in missXORDup that's 1.
    int differBit = missXORDup & (~(missXORDup - 1));

    int missOrDup = 0;
    for (int i = 0; i < A.length; ++i) {
      if ((i & differBit) != 0) {
        missOrDup ^= i;
      }
      if ((A[i] & differBit) != 0) {
        missOrDup ^= A[i];
      }
    }

    for (int a : A) {
      if (a == missOrDup) { // Find duplicate.
        return new Pair<>(missOrDup, missOrDup ^ missXORDup);
      }
    }
    // missOrDup is the missing element.
    return new Pair<>(missOrDup ^ missXORDup, missOrDup);
  }
  // @exclude

  public static void main(String[] args) {
    Random r = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = r.nextInt(9999) + 2;
      }
      int[] A = new int[n];
      for (int i = 0; i < n; ++i) {
        A[i] = i;
      }
      int missingIdx = r.nextInt(n);
      int missing = A[missingIdx];
      int dupIdx = r.nextInt(n);
      while (dupIdx == missingIdx) {
        dupIdx = r.nextInt(n);
      }
      int dup = A[dupIdx];
      A[missingIdx] = dup;
      Pair<Integer, Integer> ans = findDuplicateMissing(A);
      System.out.println("times = " + times);
      System.out.println(dup + " " + missing);
      System.out.println(ans.getFirst() + " " + ans.getSecond());
      assert (ans.getFirst().equals(dup) && ans.getSecond().equals(missing));
    }
  }
}
