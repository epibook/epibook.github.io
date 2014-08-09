package com.epi;

import java.util.LinkedList;

public class ValidParentheses {
  // @include
  public static boolean isValidParentheses(String s) {
    LinkedList<Character> leftParens = new LinkedList<>();
    for (int i = 0; i < s.length(); ++i) {
      if (s.charAt(i) == '(' || s.charAt(i) == '{' || s.charAt(i) == '[') {
        leftParens.push(s.charAt(i));
      } else {
        if (leftParens.isEmpty()) {
          return false; // No left paren.
        }
        if ((s.charAt(i) == ')' && leftParens.peek() != '(')
            || (s.charAt(i) == '}' && leftParens.peek() != '{')
            || (s.charAt(i) == ']' && leftParens.peek() != '[')) {
          return false; // Unmatched left paren.
        }
        leftParens.pop();
      }
    }
    return leftParens.isEmpty();
  }
  // @exclude

  private static void smallTest() {
    assert (isValidParentheses("()"));
    assert (isValidParentheses("()[]{}"));
    assert (isValidParentheses("[()[]]{}"));
    assert (isValidParentheses("(()[]{()[]{}{}})"));
    assert (!isValidParentheses("([)]"));
    assert (!isValidParentheses("["));
    assert (!isValidParentheses("(()[]{()[]{})({}})"));
  }

  public static void main(String[] args) {
    smallTest();
    if (args.length == 1) {
      System.out.println(isValidParentheses(args[0]));
    }
  }
}
