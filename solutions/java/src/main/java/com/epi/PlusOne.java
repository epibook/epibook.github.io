package com.epi;

import java.util.*;

public class PlusOne {
  // @include
  public static int[] plusOne(int[] A) {
    ++A[A.length - 1];
    for (int i = A.length - 1; i > 0 && A[i] == 10; --i) {
      A[i] = 0;
      ++A[i - 1];
    }
    if (A[0] < 10) {
      return A;
    }

    int[] result = new int[A.length + 1];
    System.arraycopy(A, 0, result, 1, A.length);
    result[1] = 0;
    result[0] = 1;
    return result;
  }
  // @exclude

  private static int[] randVector(int len) {
    if (len == 0) {
      int[] A = {0};
      return A;
    }
    Random r = new Random();
    int[] A = new int[len];
    A[0] = r.nextInt(9) + 1;
    for (int i = 1; i < len; ++i) {
      A[i] = r.nextInt(10);
    }
    return A;
  }

  private static void smallTest() {
    int[] input = {9, 9};
    int[] result = plusOne(input);
    assert(result.length == 3 && result[0] == 1 && result[1] == 0 &&
           result[2] == 0);
    input = new int[3];
    input[0] = 3;
    input[1] = 1;
    input[2] = 4;
    result = plusOne(input);
    assert(result.length == 3 && result[0] == 3 && result[1] == 1 &&
           result[2] == 5);
  }

  public static void main(String[] args) {
    smallTest();
    Random r = new Random();
    int n;
    if (args.length == 1) {
      n = Integer.parseInt(args[0]);
    } else {
      n = r.nextInt(1001);
    }
    int[] A = randVector(n);
    for (int a : A) {
      System.out.print(a);
    }
    System.out.println();
    int[] result = plusOne(A);
    for (int a : result) {
      System.out.print(a);
    }
    System.out.println();
  }
}
