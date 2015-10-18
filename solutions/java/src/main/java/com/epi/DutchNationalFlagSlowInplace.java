// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DutchNationalFlagSlowInplace {
  // @include
  public static enum Color { RED, WHITE, BLUE }

  public static void dutchFlagPartition(int pivotIndex, List<Color> A) {
    Color pivot = A.get(pivotIndex);
    // First pass: group elements smaller than pivot.
    for (int i = 0; i < A.size(); ++i) {
      // Look for a smaller element.
      for (int j = i + 1; j < A.size(); ++j) {
        if (A.get(j).ordinal() < pivot.ordinal()) {
          Collections.swap(A, i, j);
          break;
        }
      }
    }
    // Second pass: group elements larger than pivot.
    for (int i = A.size() - 1; i >= 0 && A.get(i).ordinal() >= pivot.ordinal();
         --i) {
      // Look for a larger element. Stop when we reach an element less
      // than pivot, since first pass has moved them to the start of A.
      for (int j = i - 1; j >= 0 && A.get(j).ordinal() >= pivot.ordinal();
           --j) {
        if (A.get(j).ordinal() > pivot.ordinal()) {
          Collections.swap(A, i, j);
          break;
        }
      }
    }
  }
  // @exclude

  private static List<Color> randArray(int len) {
    Random r = new Random();
    List<Color> ret = new ArrayList<>(len);
    for (int i = 0; i < len; ++i) {
      ret.add(Color.values()[r.nextInt(3)]);
    }
    return ret;
  }

  public static void main(String[] args) {
    Random gen = new Random();

    for (int times = 0; times < 1000; ++times) {
      int n;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = gen.nextInt(100) + 1;
      }

      List<Color> A = randArray(n);

      int pivotIndex = gen.nextInt(n);
      Color pivot = A.get(pivotIndex);

      dutchFlagPartition(pivotIndex, A);

      int i = 0;
      while (i < n && A.get(i).ordinal() < pivot.ordinal()) {
        System.out.print(A.get(i) + " ");
        ++i;
      }
      while (i < n && A.get(i) == pivot) {
        System.out.print(A.get(i) + " ");
        ++i;
      }
      while (i < n && A.get(i).ordinal() > pivot.ordinal()) {
        System.out.print(A.get(i) + " ");
        ++i;
      }
      System.out.println();

      assert(i == n);
    }
  }
}
