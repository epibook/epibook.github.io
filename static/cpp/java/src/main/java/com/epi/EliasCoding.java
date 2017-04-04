package com.epi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EliasCoding {
  // @include
  public static String encode(List<Integer> A) {
    StringBuilder ret = new StringBuilder();
    for (Integer a : A) {
      String binary = transIntToBinary(a);
      // Prepend 0s.
      for (int i = 0; i < binary.length(); ++i) {
        ret.append('0');
      }
      ret.append(binary);
    }
    return ret.toString();
  }

  private static String transIntToBinary(int decimal) {
    StringBuilder ret = new StringBuilder();
    while (decimal != 0) {
      ret.append((char)('0' + (decimal % 2)));
      // clang-format off
      decimal >>>= 1;
      // clang-format on
    }
    ret.reverse();
    return ret.toString();
  }

  public static List<Integer> decode(String s) {
    List<Integer> ret = new ArrayList<>();
    int idx = 0;
    while (idx < s.length()) {
      // Count the number of consecutive 0s.
      int zeroIdx = idx;
      while (zeroIdx < s.length() && s.charAt(zeroIdx) == '0') {
        ++zeroIdx;
      }

      int len = zeroIdx - idx;
      ret.add(transBinaryToInt(s.substring(zeroIdx, zeroIdx + len)));
      idx = zeroIdx + len;
    }
    return ret;
  }

  private static int transBinaryToInt(String binary) {
    int ret = 0;
    for (int i = 0; i < binary.length(); i++) {
      char c = binary.charAt(i);
      ret = (ret * 2) + c - '0';
    }
    return ret;
  }
  // @exclude

  public static void main(String[] args) {
    List<Integer> A = new ArrayList<>();
    Random r = new Random();
    if (args.length == 0) {
      int count = r.nextInt(10000) + 1;
      for (int i = 0; i < count; i++) {
        A.add(r.nextInt(Integer.MAX_VALUE));
      }
    } else {
      int count = Integer.parseInt(args[0]);
      for (int i = 0; i < count; i++) {
        A.add(4);
      }
    }
    String ret = encode(A);
    System.out.println(ret);

    List<Integer> res = decode(ret);
    assert(A.size() == res.size());
    for (int i = 0; i < A.size(); ++i) {
      assert(res.get(i).equals(A.get(i)));
    }
  }
}
