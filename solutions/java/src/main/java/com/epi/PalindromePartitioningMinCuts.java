package com.epi;

import java.util.Random;

public class PalindromePartitioningMinCuts {
  // @include
  public static int minCuts(String s) {
    boolean[][] isPalindrome = new boolean[s.length()][s.length()];
    int[] T = new int[s.length() + 1];
    int val = -1;
    for (int i = T.length - 1; i >= 0; i--) {
      T[i] = val;
      val++;
    }
    for (int i = s.length() - 1; i >= 0; --i) {
      for (int j = i; j < s.length(); ++j) {
        if (s.charAt(i) == s.charAt(j)
            && (j - i < 2 || isPalindrome[i + 1][j - 1])) {
          isPalindrome[i][j] = true;
          T[i] = Math.min(T[i], 1 + T[j + 1]);
        }
      }
    }
    return T[0];
  }
  // @exclude

  private static String randString(int len) {
    Random r = new Random();
    StringBuilder ret = new StringBuilder(len);
    while (len-- > 0) {
      ret.append((char)(r.nextInt(26) + 'a'));
    }
    return ret.toString();
  }

  private static void smallTest() {
    assert(1 == minCuts("aab"));
    assert(0 == minCuts("bb"));
    assert(3 == minCuts("cabababcbc"));
    assert(42 == minCuts("eegiicgaeadbcfacfhifdbiehbgejcaeggcgbahfcajfhjjdgj"));
  }

  public static void main(String[] args) {
    smallTest();
    String s;
    if (args.length == 1) {
      s = args[0];
    } else {
      Random r = new Random();
      s = randString(r.nextInt(11));
    }
    System.out.println("times = " + minCuts(s));
  }
}
