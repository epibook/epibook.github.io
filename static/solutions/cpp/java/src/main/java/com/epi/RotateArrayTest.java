package com.epi;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RotateArrayTest {
  public static List<Integer> randVector(int len) {
    Random gen = new Random();
    List<Integer> ret = new ArrayList<>(len);
    while (len-- > 0) {
      ret.add(gen.nextInt(len + 1));
    }
    return ret;
  }

  public static void checkAnswer(List<Integer> A, int i,
                                 List<Integer> rotated) {
    assert A.size() == rotated.size();
    for (int idx = 0; idx < A.size(); ++idx) {
      assert(rotated.get((idx + i) % rotated.size()).equals(A.get(idx)));
    }
  }

  public static void main(String[] args) {
    Random gen = new Random();
    for (int times = 0; times < 1000; ++times) {
      int len;
      if (args.length == 1) {
        len = Integer.parseInt(args[0]);
      } else {
        len = gen.nextInt(10000) + 1;
      }
      List<Integer> A = randVector(len);
      int i = gen.nextInt(len);

      List<Integer> B = new ArrayList<>(A);
      RotateArrayPermutation.rotateArray(i, B);
      checkAnswer(A, i, B);

      List<Integer> C = new ArrayList<>(A);
      RotateArray.rotateArray(i, C);
      checkAnswer(A, i, C);
    }
  }
}
