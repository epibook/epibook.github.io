package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class GrayCodeBacktrack {
  // @include
  public static List<Integer> grayCode(int numBits) {
    List<Integer> result = new ArrayList<>(Arrays.asList(0));
    directedGrayCode(numBits, new HashSet<Integer>(Arrays.asList(0)), result);
    return result;
  }

  private static boolean directedGrayCode(int numBits, Set<Integer> history,
                                          List<Integer> result) {
    if (result.size() == (1 << numBits)) {
      return differsByOneBit(result.get(0), result.get(result.size() - 1));
    }

    for (int i = 0; i < numBits; ++i) {
      int previousCode = result.get(result.size() - 1);
      int candidateNextCode = previousCode ^ (1 << i);
      if (!history.contains(candidateNextCode)) {
        history.add(candidateNextCode);
        result.add(candidateNextCode);
        if (directedGrayCode(numBits, history, result)) {
          return true;
        }
        history.remove(candidateNextCode);
        result.remove(result.size() - 1);
      }
    }
    return false;
  }

  private static boolean differsByOneBit(int x, int y) {
    int bitDifference = x ^ y;
    return bitDifference != 0 && (bitDifference & (bitDifference - 1)) == 0;
  }
  // @exclude

  private static void smallTest() {
    List<Integer> vec = grayCode(3);
    List<Integer> expected = Arrays.asList(0, 1, 3, 2, 6, 7, 5, 4);
    assert(vec.size() == expected.size());
    assert(Arrays.equals(vec.toArray(), expected.toArray()));
  }

  private static void checkAns(List<Integer> A) {
    for (int i = 1; i < A.size(); ++i) {
      int numDifferBits = 0;
      String prevS = addZerosTo100(Integer.toBinaryString(A.get(i)));
      String nowS
          = addZerosTo100(Integer.toBinaryString(A.get((i + 1) % A.size())));
      for (int j = 0; j < 100; ++j) {
        if (prevS.charAt(j) != nowS.charAt(j)) {
          ++numDifferBits;
        }
      }
      assert(numDifferBits == 1);
    }
  }

  private static String addZerosTo100(String s) {
    while (s.length() < 100) {
      s = "0" + s;
    }
    return s;
  }

  public static void main(String[] args) {
    smallTest();
    Random r = new Random();
    int n;
    if (args.length == 1) {
      n = Integer.parseInt(args[0]);
    } else {
      n = r.nextInt(9) + 1;
    }
    System.out.println("n = " + n);
    List<Integer> vec = grayCode(n);
    // System.out.println(vec);
    for (Integer v : vec) {
      System.out.println(Integer.toBinaryString(v));
    }
    checkAns(vec);
  }
}
