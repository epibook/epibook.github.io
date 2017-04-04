package com.epi;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EvenOddArray {
  // @include
  public static void evenOdd(int[] A) {
    int nextEven = 0, nextOdd = A.length - 1;
    while (nextEven < nextOdd) {
      if (A[nextEven] % 2 == 0) {
        nextEven++;
      } else {
        int temp = A[nextEven];
        A[nextEven] = A[nextOdd];
        A[nextOdd--] = temp;
      }
    }
  }
  // @exclude

  /*
  public static int[] evenOddStable(int[] A) {
    int firstOdd = -1;
    for (int i = 0; i < A.length; i++) {
      if (A[i] % 2 == 1) {
        if (firstOdd == -1) {
          firstOdd = i;
        }
      } else if (firstOdd != -1) {
        int tmp = A[firstOdd];
        A[firstOdd] = A[i];
        A[i] = tmp;
        firstOdd++;
      }
      return A;
  }
  */

  private static void test(int[] A) {
    List<Integer> even = new ArrayList<>();
    List<Integer> odd = new ArrayList<>();
    for (int a : A) {
      if (a % 2 == 0) {
        even.add(a);
      } else {
        odd.add(a);
      }
    }
    List<Integer> gold = new ArrayList<>();
    gold.addAll(even);
    gold.addAll(odd);
    evenOdd(A);
    System.out.println(Arrays.toString(A));
    boolean inOdd = false;
    for (int i = 0; i < A.length; i++) {
      if (A[i] % 2 == 0) {
        assert(even.contains(A[i]));
        assert(!inOdd);
      } else {
        assert(odd.contains(A[i]));
        inOdd = true;
      }
    }
    List<Integer> AList = new ArrayList<>();
    for (int a : A) {
      AList.add(a);
    }
    for (int a : even) {
      assert(AList.contains(a));
    }
    for (int a : odd) {
      assert(AList.contains(a));
    }
  }

  public static void main(String[] args) {
    Random rnd = new Random();
    for (int i = 0; i < 1000; i++) {
      int[] A = new int[rnd.nextInt(20) + 1];
      for (int j = 0; j < A.length; j++) {
        A[j] = rnd.nextInt(20);
      }
      test(A);
    }
  }
}
