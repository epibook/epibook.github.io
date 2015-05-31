package com.epi;

import java.util.LinkedList;

public class ValidParentheses {
  // @include
  public static boolean isWellFormed(String s) {
    LinkedList<Character> leftChars = new LinkedList<>();
    for (int i = 0; i < s.length(); ++i) {
      if (s.charAt(i) == '(' || s.charAt(i) == '{' || s.charAt(i) == '[') {
        leftChars.push(s.charAt(i));
      } else {
        if (leftChars.isEmpty()) {
          return false; // Unmatched right char.
        }
        if ((s.charAt(i) == ')' && leftChars.peek() != '(') ||
            (s.charAt(i) == '}' && leftChars.peek() != '{') ||
            (s.charAt(i) == ']' && leftChars.peek() != '[')) {
          return false; // Mismatched chars.
        }
        leftChars.pop();
      }
    }
    return leftChars.isEmpty();
  }
  // @exclude

  private static void smallTest() {
    assert(isWellFormed("()"));
    assert(isWellFormed("()[]{}"));
    assert(isWellFormed("[()[]]{}"));
    assert(isWellFormed("(()[]{()[]{}{}})"));
    assert(!isWellFormed("([)]"));
    assert(!isWellFormed("["));
    assert(!isWellFormed("(()[]{()[]{})({}})"));
  }

  public static void main(String[] args) {
    smallTest();
    if (args.length == 1) {
      System.out.println(isWellFormed(args[0]));
    }
  }
}
