package com.epi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FindMissingAndDuplicate {
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
    int sum = 0, squareSum = 0;
    for (int i = 0; i < A.size(); ++i) {
      sum += i - A.get(i);
      squareSum += i * i - A.get(i) * A.get(i);
    }
    return new DuplicateAndMissing((squareSum / sum - sum) / 2,
                                   (squareSum / sum + sum) / 2);
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
