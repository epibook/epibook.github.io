package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GrayCode {
  // @include
  public static List<Integer> grayCode(int n) {
    if (n == 0) {
      return new ArrayList<Integer>() {
        {
          add(0);
        }
      };
    }
    if (n == 1) {
      return new ArrayList<Integer>() {
        {
          add(0);
          add(1);
        }
      };
    }

    List<Integer> prevRes = grayCode(n - 1); // Result prepends 0.
    // Creates result prepending 1.
    int leadingBitOne = 1 << (n - 1);
    List<Integer> result = new ArrayList<>();
    for (int i = prevRes.size() - 1; i >= 0; --i) {
      result.add(leadingBitOne + prevRes.get(i));
    }
    prevRes.addAll(result);
    return prevRes;
  }
  // @exclude

  private static void smallTest() {
    List<Integer> vec = grayCode(3);
    List<Integer> expected = Arrays.asList(0, 1, 3, 2, 6, 7, 5, 4);
    assert (vec.size() == expected.size());
    assert (Arrays.equals(vec.toArray(), expected.toArray()));
  }

  private static void checkAns(List<Integer> A) {
    for (int i = 1; i < A.size(); ++i) {
      int numDifferBits = 0;
      String prevS = addZerosTo10(Integer.toBinaryString(A.get(i - 1)));
      String nowS = addZerosTo10(Integer.toBinaryString(A.get(i)));
      for (int j = 0; j < 10; ++j) {
        if (prevS.charAt(j) != nowS.charAt(j)) {
          ++numDifferBits;
        }
      }
      assert (numDifferBits == 1);
    }
  }

  private static String addZerosTo10(String s) {
    while (s.length() < 10) {
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
    System.out.println(vec);
    checkAns(vec);
  }
}
