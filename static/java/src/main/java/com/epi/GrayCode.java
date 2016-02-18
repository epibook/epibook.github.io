package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GrayCode {
  // @include
  public static List<Integer> grayCode(int numBits) {
    if (numBits == 0) {
      return Arrays.asList(0);
    }
    if (numBits == 1) {
      return Arrays.asList(0, 1);
    }

    // These implicitly begin with 0 at bit-index (numBits - 1).
    List<Integer> grayCodeNumBitsMinus1 = grayCode(numBits - 1);

    // Now, add a 1 at bit-index (numBits - 1) to all entries in
    // grayCodeNumBitsMinus1.
    int leadingBitOne = 1 << (numBits - 1);

    // Process in reverse order to achieve reflection of grayCodeNumBitsMinus1.
    List<Integer> reflection = new ArrayList<>();
    for (int i = grayCodeNumBitsMinus1.size() - 1; i >= 0; --i) {
      reflection.add(leadingBitOne | grayCodeNumBitsMinus1.get(i));
    }
    List<Integer> result = new ArrayList<>(grayCodeNumBitsMinus1);
    result.addAll(reflection);
    return result;
  }
  // @exclude

  private static void smallTest() {
    List<Integer> vec = grayCode(3);
    List<Integer> expected = Arrays.asList(0, 1, 3, 2, 6, 7, 5, 4);
    assert(vec.size() == expected.size());
    assert(Arrays.equals(vec.toArray(), expected.toArray()));
  }

  private static void checkAns(List<Integer> A) {
    for (int i = 0; i < A.size(); ++i) {
      int numDifferBits = 0;
      String prevS = addZerosTo10(Integer.toBinaryString(A.get(i)));
      String nowS
          = addZerosTo10(Integer.toBinaryString(A.get((i + 1) % A.size())));
      for (int j = 0; j < 10; ++j) {
        if (prevS.charAt(j) != nowS.charAt(j)) {
          ++numDifferBits;
        }
      }
      assert(numDifferBits == 1);
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
