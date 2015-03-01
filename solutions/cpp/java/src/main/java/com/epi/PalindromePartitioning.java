package com.epi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PalindromePartitioning {
  // @include
  public static List<List<String>> palindromePartitioning(String s) {
    List<List<String>> result = new ArrayList<>();
    List<String> partition = new ArrayList<>();
    palindromePartitioningHelper(s, 0, partition, result);
    return result;
  }

  private static void palindromePartitioningHelper(String s, int begin,
                                                   List<String> partition,
                                                   List<List<String>> result) {
    if (begin == s.length()) {
      result.add(new ArrayList<>(partition));
      return;
    }

    for (int i = begin + 1; i <= s.length(); ++i) {
      String prefix = s.substring(begin, i);
      if (isPalindrome(prefix)) {
        partition.add(prefix);
        palindromePartitioningHelper(s, i, partition, result);
        partition.remove(partition.size() - 1);
      }
    }
  }

  private static boolean isPalindrome(String s) {
    for (int i = 0, j = s.length() - 1; i < j; ++i, --j) {
      if (s.charAt(i) != s.charAt(j)) {
        return false;
      }
    }
    return true;
  }
  // @exclude

  private static void checkAns(List<List<String>> vecs, String input) {
    for (List<String> vec : vecs) {
      String temp = "";
      for (String s : vec) {
        assert (isPalindrome(s));
        temp += s;
      }
      assert (temp.equals(input));
    }
  }

  private static String randString(int len) {
    Random r = new Random();
    StringBuilder ret = new StringBuilder(len);
    while (len-- > 0) {
      ret.append((char) (r.nextInt(26) + 'a'));
    }
    return ret.toString();
  }

  public static void main(String[] args) {
    if (args.length == 1) {
      String s = args[0];
    } else {
      Random r = new Random();
      for (int times = 0; times < 1000; ++times) {
        String s = randString(r.nextInt(11));
        List<List<String>> result = palindromePartitioning(s);
        checkAns(result, s);
        System.out.println("string s = " + s);
        for (List<String> vec : result) {
          System.out.println(vec);
        }
      }
    }

  }
}
