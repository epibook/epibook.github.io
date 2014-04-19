package com.epi;

import java.util.ArrayList;
import java.util.List;

public class ValidIPAddress {
  // @include
  public static List<String> getValidIpAddress(String s) {
    List<String> res = new ArrayList<String>();
    for (int i = 1; i < 4 && i < s.length(); ++i) {
      String first = s.substring(0, i);
      if (isValid(first)) {
        for (int j = 1; i + j < s.length() && j < 4; ++j) {
          String second = s.substring(i, i + j);
          if (isValid(second)) {
            for (int k = 1; i + j + k < s.length() && k < 4; ++k) {
              String third = s.substring(i + j, i + j + k), fourth = s
                  .substring(i + j + k);
              if (isValid(third) && isValid(fourth)) {
                res.add(first + "." + second + "." + third + "." + fourth);
              }
            }
          }
        }
      }
    }
    return res;
  }

  private static boolean isValid(String s) {
    if ((s.startsWith("0") && s.length() > 1) || s.length() > 3) {
      return false;
    }
    int val = Integer.parseInt(s);
    return val <= 255 && val >= 0;
  }

  // @exclude

  public static void main(String[] args) {
    if (args.length == 1) {
      List<String> res = getValidIpAddress(args[0]);
      System.out.println(res);
    }
    List<String> res1 = getValidIpAddress("255255255255");
    System.out.println(res1);
    List<String> res2 = getValidIpAddress("19216811");
    System.out.println(res2);
  }
}
