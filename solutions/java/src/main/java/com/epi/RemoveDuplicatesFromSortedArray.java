package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RemoveDuplicatesFromSortedArray {
  // @include
  // Returns the number of valid entries after deletion.
  public static int deleteDuplicates(List<Integer> A) {
    if (A.isEmpty()) {
      return 0;
    }

    int writeIndex = 1;
    for (int i = 1; i < A.size(); ++i) {
      if (!A.get(writeIndex - 1).equals(A.get(i))) {
        A.set(writeIndex++, A.get(i));
      }
    }
    return writeIndex;
  }
  // @exclude

  private static void checkAns(List<Integer> A, int n) {
    for (int i = 1; i < n; ++i) {
      assert(!A.get(i - 1).equals(A.get(i)));
    }
  }

  private static void smallTest() {
    assert(6 == deleteDuplicates(Arrays.asList(2, 3, 5, 5, 7, 11, 11, 11, 13)));
  }

  public static void main(String[] args) {
    smallTest();
    Random r = new Random();
    int n;
    if (args.length == 1) {
      n = Integer.parseInt(args[0]);
    } else {
      n = r.nextInt(10000) + 1;
    }
    for (int times = 0; times < 1000; ++times) {
      List<Integer> A = new ArrayList<>();
      for (int i = 0; i < n; i++) {
        A.add(r.nextInt(2001) - 1000);
      }
      Collections.sort(A);
      Set<Integer> unique = new HashSet<>(A);
      int size = deleteDuplicates(A);
      assert(size == unique.size());
      checkAns(A, size);
    }
  }
}
