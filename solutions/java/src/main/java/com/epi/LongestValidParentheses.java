package com.epi;

import java.util.LinkedList;

public class LongestValidParentheses {
  // @include
  public static int longestValidParentheses(String s) {
    int maxLength = 0, lastEnd = 0;
    LinkedList<Integer> leftParentheses = new LinkedList<>();
    for (int i = 0; i < s.length(); ++i) {
      if (s.charAt(i) == '(') {
        leftParentheses.push(i);
      } else {
        if (leftParentheses.isEmpty()) {
          lastEnd = i + 1;
        } else {
          leftParentheses.pop();
          int start =
              leftParentheses.isEmpty() ? lastEnd - 1 : leftParentheses.peek();
          maxLength = Math.max(maxLength, i - start);
        }
      }
    }
    return maxLength;
  }
  // @exclude

  private static void smallTest() {
    assert (longestValidParentheses("((())()(()(") == 6);
    assert (longestValidParentheses("()()())") == 6);
  }

  public static void main(String[] args) {
    smallTest();
    if (args.length == 1) {
      String s = args[0];
      System.out.println("s = " + s);
      System.out.println(longestValidParentheses(s));
    }
  }
}
