package com.epi;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
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

  public static DuplicateAndMissing findDuplicateMissing(List<Integer> A) {
    // Compute the XOR of all numbers from 0 to |A| - 1 and all entries in A.
    int missXORDup = 0;
    for (int i = 0; i < A.size(); ++i) {
      missXORDup ^= i ^ A.get(i);
    }

    // We need to find a bit that's set to 1 in missXORDup. Such a bit
    // must exist if there is a single missing number and a single duplicated
    // number in A.
    //
    // The bit-fiddling assignment below sets all of bits in differBit to 0
    // except for the least significant bit in missXORDup that's 1.
    int differBit = missXORDup & (~(missXORDup - 1));
    int missOrDup = 0;
    for (int i = 0; i < A.size(); ++i) {
      // Focus on entries and numbers in which the differBit-th bit is 1.
      if ((i & differBit) != 0) {
        missOrDup ^= i;
      }
      if ((A.get(i) & differBit) != 0) {
        missOrDup ^= A.get(i);
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

  private static void simpleTest() {
    List<Integer> A = Arrays.asList(0, 1, 2, 4, 5, 6, 6);
    DuplicateAndMissing dm = findDuplicateMissing(A);
    assert(dm.duplicate == 6 && dm.missing == 3);

    A = Arrays.asList(0, 0, 1);
    dm = findDuplicateMissing(A);
    assert(dm.duplicate == 0 && dm.missing == 2);

    A = Arrays.asList(1, 3, 2, 5, 3, 4);
    dm = findDuplicateMissing(A);
    assert(dm.duplicate == 3 && dm.missing == 0);
  }

  public static void main(String[] args) {
    simpleTest();
    Random r = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = r.nextInt(9999) + 2;
      }
      List<Integer> A = new ArrayList<>(n);
      for (int i = 0; i < n; ++i) {
        A.add(i);
      }
      int missingIdx = r.nextInt(n);
      int missing = A.get(missingIdx);
      int dupIdx = r.nextInt(n);
      while (dupIdx == missingIdx) {
        dupIdx = r.nextInt(n);
      }
      int dup = A.get(dupIdx);
      A.set(missingIdx, dup);
      DuplicateAndMissing ans = findDuplicateMissing(A);
      System.out.println("times = " + times);
      System.out.println(dup + " " + missing);
      System.out.println(ans.duplicate + " " + ans.missing);
      assert(ans.duplicate.equals(dup) && ans.missing.equals(missing));
    }
  }
}
