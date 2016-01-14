// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class BigNumberMultiplication {
  // @include
  public static List<Integer> multiply(List<Integer> num1, List<Integer> num2) {
    boolean isNegative = (num1.get(0) < 0 && num2.get(0) >= 0)
                         || (num1.get(0) >= 0 && num2.get(0) < 0);
    num1.set(0, Math.abs(num1.get(0)));
    num2.set(0, Math.abs(num2.get(0)));

    // Reverses num1 and num2 to make multiplication easier.
    Collections.reverse(num1);
    Collections.reverse(num2);
    List<Integer> result = new ArrayList<>(num1.size() + num2.size());
    for (int i = 0; i < num1.size() + num2.size(); ++i) {
      result.add(0);
    }
    for (int i = 0; i < num1.size(); ++i) {
      for (int j = 0; j < num2.size(); ++j) {
        result.set(i + j, result.get(i + j) + num1.get(i) * num2.get(j));
        result.set(i + j + 1, result.get(i + j + 1) + result.get(i + j) / 10);
        result.set(i + j, result.get(i + j) % 10);
      }
    }

    // Skips the leading 0s and keeps one 0 if all are 0s.
    while (result.size() != 1 && result.get(result.size() - 1) == 0) {
      result.remove(result.size() - 1);
    }
    // Reverses result to get the most significant digit as the start of array.
    Collections.reverse(result);
    if (isNegative) {
      result.set(0, result.get(0) * -1);
    }
    return result;
  }
  // @exclude

  private static List<Integer> randArray(int len) {
    Random r = new Random();
    if (len == 0) {
      return Arrays.asList(0);
    }
    List<Integer> ret = new ArrayList<>();
    ret.add(r.nextInt(9) + 1);
    --len;
    while (len-- != 0) {
      ret.add(r.nextInt(10));
    }

    if (r.nextInt(1) == 1) {
      ret.set(0, ret.get(0) * -1);
    }
    return ret;
  }

  private static void simpleTest() {
    assert(multiply(Arrays.asList(0), Arrays.asList(-1, 0, 0, 0))
               .equals(Arrays.asList(0)));
    assert(multiply(Arrays.asList(0), Arrays.asList(1, 0, 0, 0))
               .equals(Arrays.asList(0)));
    assert(multiply(Arrays.asList(9), Arrays.asList(9))
               .equals(Arrays.asList(8, 1)));
    assert(multiply(Arrays.asList(9), Arrays.asList(9, 9, 9, 9))
               .equals(Arrays.asList(8, 9, 9, 9, 1)));
    assert(multiply(Arrays.asList(1, 3, 1, 4, 1, 2),
                    Arrays.asList(-1, 3, 1, 3, 3, 3, 2))
               .equals(Arrays.asList(-1, 7, 2, 5, 8, 7, 5, 8, 4, 7, 8, 4)));
    assert(multiply(Arrays.asList(7, 3), Arrays.asList(-3))
               .equals(Arrays.asList(-2, 1, 9)));
  }

  private static String listToString(List<Integer> A) {
    StringBuilder result = new StringBuilder();
    for (int a : A) {
      result.append(a);
    }
    return result.toString();
  }

  public static void main(String[] args) {
    simpleTest();
    for (int times = 0; times < 1000; ++times) {
      List<Integer> s1, s2;
      Random gen = new Random();
      s1 = randArray(gen.nextInt(20));
      s2 = randArray(gen.nextInt(20));

      List<Integer> temp1 = new ArrayList<>(s1), temp2 = new ArrayList<>(s2);
      List<Integer> res = multiply(temp1, temp2);
      System.out.println(String.format("%s * %s = %s", listToString(s1),
                                       listToString(s2), listToString(res)));

      System.out.println("multiplying using Java's BigInteger...");
      BigInteger result = new BigInteger(listToString(s1))
                              .multiply(new BigInteger(listToString(s2)));
      System.out.println("answer = " + result.toString());

      assert(listToString(res).equals(result.toString()));
    }
  }
}
