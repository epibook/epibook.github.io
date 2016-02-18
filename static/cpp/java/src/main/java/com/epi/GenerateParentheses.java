package com.epi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenerateParentheses {
  // @include
  public static List<String> generateParentheses(int n) {
    String s = "";
    List<String> result = new ArrayList<>();
    generateParenthesesHelper(2 * n, 0, s, result);
    return result;
  }

  private static void generateParenthesesHelper(int remained, int leftParens,
                                                String s, List<String> result) {
    if (remained == 0) {
      result.add(s);
      return;
    }

    if (leftParens < remained) { // Able to insert '('.
      generateParenthesesHelper(remained - 1, leftParens + 1, s + "(", result);
    }
    if (leftParens > 0) { // Able to insert ')'.
      generateParenthesesHelper(remained - 1, leftParens - 1, s + ")", result);
    }
  }
  // @exclude

  private static void smallTest() {
    List<String> result = generateParentheses(1);
    assert (result.size() == 1 && result.get(0).equals("()"));
    result = generateParentheses(2);
    assert (result.size() == 2 && ((result.get(0).equals("(())") && result.get(1)
        .equals("()()")) || (result.get(0).equals("()()") && result.get(1).equals(
        "(())"))));
  }

  public static void main(String[] args) {
    smallTest();
    Random r = new Random();
    int n;
    if (args.length == 1) {
      n = Integer.parseInt(args[0]);
    } else {
      n = r.nextInt(11);
    }
    System.out.println("n = " + n);
    List<String> result = generateParentheses(n);
    System.out.println(result);
  }
}
