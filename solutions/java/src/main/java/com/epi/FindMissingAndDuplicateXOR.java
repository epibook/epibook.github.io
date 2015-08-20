package com.epi;

import java.util.Random;

public class FindMissingAndDuplicateXOR {
  // @include

  private static class DuplicateAndMissing {
    public Integer duplicate;
    public Integer missing;

    public DuplicateAndMissing(Integer duplicate, Integer missing) {
      this.duplicate = duplicate;
      this.missing = missing;
    }
  }
  public static DuplicateAndMissing findDuplicateMissing(int[] A) {
    // Compute the XOR of all numbers from 0 to |A| - 1 and all entries in A.
    int missXORDup = 0;
    for (int i = 0; i < A.length; ++i) {
      missXORDup ^= i ^ A[i];
    }

    // We need to find a bit that's set to 1 in missXORDup. Such a bit
    // must exist if there is a single missing number and a single duplicated
    // number in A.
    //
    // The bit-fiddling assignment below sets all of bits in differBit to 0
    // except for the least significant bit in missXORDup that's 1.
    int differBit = missXORDup & (~(missXORDup - 1));
    int missOrDup = 0;
    for (int i = 0; i < A.length; ++i) {
      // Focus on entries and numbers in which the differBit-th bit is 1.
      if ((i & differBit) != 0) {
        missOrDup ^= i;
      }
      if ((A[i] & differBit) != 0) {
        missOrDup ^= A[i];
      }
    }

    // missOrDup is either the missing value or the duplicated entry.
    for (int a : A) {
      if (a == missOrDup) { // missOrDup is the duplicate.
        return new DuplicateAndMissing(missOrDup, missOrDup ^ missXORDup);
      }
    }
    // missOrDup is the missing value.
    return new DuplicateAndMissing(missOrDup ^ missXORDup, missOrDup);
  }
  // @exclude

  private static void SimpleTest() {
    int[] A = {0,0,1};
    DuplicateAndMissing dm = findDuplicateMissing(A);
    assert(dm.duplicate == 0 && dm.missing == 2);

    A = new int[]{1,3,2,5,3,4};
    dm = findDuplicateMissing(A);
    assert(dm.duplicate == 3 && dm.missing == 0);
  }

  public static void main(String[] args) {
    SimpleTest();
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
      DuplicateAndMissing ans = findDuplicateMissing(A);
      System.out.println("times = " + times);
      System.out.println(dup + " " + missing);
      System.out.println(ans.duplicate + " " + ans.missing);
      assert(ans.duplicate.equals(dup) && ans.missing.equals(missing));
    }
  }
}
