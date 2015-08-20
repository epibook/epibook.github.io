package com.epi;

import java.util.Arrays;
import java.util.Random;

public class RotateArrayTest {
  public static int[] randVector(int len) {
    Random gen = new Random();
    int[] ret = new int[len];
    while (len-- > 0) {
      ret[len] = gen.nextInt(len + 1);
    }
    return ret;
  }

  public static void checkAnswer(int[] A, int i, int[] rotated) {
    assert A.length == rotated.length;
    for (int idx = 0; idx < A.length; ++idx) {
      assert(rotated[(idx + i) % rotated.length] == A[idx]);
    }
  }

  public static void main(String[] args) {
    Random gen = new Random();
    for (int times = 0; times < 1000; ++times) {
      int len;
      if (args.length == 1) {
        len = Integer.valueOf(args[0]);
      } else {
        len = gen.nextInt(10000) + 1;
      }
      int[] A = randVector(len);
      int i = gen.nextInt(len);

      int[] B = Arrays.copyOf(A, A.length);
      RotateArrayPermutation.rotateArray(i, B);
      checkAnswer(A, i, B);

      int[] C = Arrays.copyOf(A, A.length);
      RotateArray.rotateArray(i, C);
      checkAnswer(A, i, C);
    }
  }
}
