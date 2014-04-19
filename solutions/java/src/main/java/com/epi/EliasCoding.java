package com.epi;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class EliasCoding {
  // @include
  public static String transIntToBinary(int decimal) {
    StringBuilder ret = new StringBuilder();
    while (decimal != 0) {
      ret.append((char) ('0' + (decimal & 1)));
      decimal >>= 1;
    }
    ret.reverse();
    return ret.toString();
  }

  public static String encode(ArrayList<Integer> A) {
    StringBuilder ret = new StringBuilder();
    for (Integer a : A) {
      String binary = transIntToBinary(a);
      for (int i = 0; i < binary.length(); ++i) {
        ret.append('0');
      }
      ret.append(binary);
    }
    return ret.toString();
  }

  public static int transBinaryToInt(String binary) {
    int ret = 0;
    for (char c : binary.toCharArray()) {
      ret = (ret << 1) + c - '0';
    }
    return ret;
  }

  public static ArrayList<Integer> decode(String s) {
    ArrayList<Integer> ret = new ArrayList<Integer>();
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

  // @exclude

  public static void main(String[] args) {
    ArrayList<Integer> A = new ArrayList<Integer>();
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

    ArrayList<Integer> res = decode(ret);
    assert (A.size() == res.size());
    for (int i = 0; i < A.size(); ++i) {
      assert (res.get(i).equals(A.get(i)));
    }
  }
}
