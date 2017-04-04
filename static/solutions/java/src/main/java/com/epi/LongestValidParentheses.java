package com.epi;

import java.util.Deque;
import java.util.LinkedList;

public class LongestValidParentheses {
  // @include
  public static int longestValidParentheses(String s) {
    int maxLength = 0, end = -1;
    Deque<Integer> leftParenthesesIndices = new LinkedList<>();
    for (int i = 0; i < s.length(); ++i) {
      if (s.charAt(i) == '(') {
        leftParenthesesIndices.addFirst(i);
      } else if (leftParenthesesIndices.isEmpty()) {
        end = i;
      } else {
        leftParenthesesIndices.removeFirst();
        int start = leftParenthesesIndices.isEmpty()
                        ? end
                        : leftParenthesesIndices.peekFirst();
        maxLength = Math.max(maxLength, i - start);
      }
    }
    return maxLength;
  }
  // @exclude

  private static void smallTest() {
    assert(longestValidParentheses(")(((())()(()(") == 6);
    assert(longestValidParentheses("((())()(()(") == 6);
    assert(longestValidParentheses(")(") == 0);
    assert(longestValidParentheses("()") == 2);
    assert(longestValidParentheses("") == 0);
    assert(longestValidParentheses("()()())") == 6);
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
