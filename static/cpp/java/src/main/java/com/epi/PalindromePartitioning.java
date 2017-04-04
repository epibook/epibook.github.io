package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PalindromePartitioning {
  // @include
  public static List<List<String>> palindromePartitioning(String input) {
    List<List<String>> result = new ArrayList<>();
    directedPalindromePartitioning(input, 0, new ArrayList<String>(), result);
    return result;
  }

  private static void directedPalindromePartitioning(
      String input, int offset, List<String> partialPartition,
      List<List<String>> result) {
    if (offset == input.length()) {
      result.add(new ArrayList<>(partialPartition));
      return;
    }

    for (int i = offset + 1; i <= input.length(); ++i) {
      String prefix = input.substring(offset, i);
      if (isPalindrome(prefix)) {
        partialPartition.add(prefix);
        directedPalindromePartitioning(input, i, partialPartition, result);
        partialPartition.remove(partialPartition.size() - 1);
      }
    }
  }

  private static boolean isPalindrome(String prefix) {
    for (int i = 0, j = prefix.length() - 1; i < j; ++i, --j) {
      if (prefix.charAt(i) != prefix.charAt(j)) {
        return false;
      }
    }
    return true;
  }
  // @exclude

  private static void checkAns(List<List<String>> vecs, String input) {
    for (List<String> vec : vecs) {
      StringBuilder temp = new StringBuilder();
      for (String s : vec) {
        assert(isPalindrome(s));
        temp.append(s);
      }
      assert(temp.toString().equals(input));
    }
  }

  private static String randString(int len) {
    Random r = new Random();
    StringBuilder ret = new StringBuilder(len);
    while (len-- > 0) {
      ret.append((char)(r.nextInt(26) + 'a'));
    }
    return ret.toString();
  }

  private static void simpleTest() {
    List<List<String>> result = palindromePartitioning("abbbac");
    List<List<String>> golden = Arrays.asList(
        Arrays.asList("a", "b", "b", "b", "a", "c"),
        Arrays.asList("a", "b", "bb", "a", "c"),
        Arrays.asList("a", "bb", "b", "a", "c"),
        Arrays.asList("a", "bbb", "a", "c"), Arrays.asList("abbba", "c"));
    System.out.println(result);
    assert(result.equals(golden));
  }

  public static void main(String[] args) {
    simpleTest();
    if (args.length == 1) {
      String s = args[0];
      List<List<String>> result = palindromePartitioning(s);
      checkAns(result, s);
      System.out.println("string s = " + s);
      for (List<String> vec : result) {
        System.out.println(vec);
      }
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
