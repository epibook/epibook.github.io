package com.epi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenerateParentheses {
  // @include
  public static List<String> generateParentheses(int n) {
    String s = "";
    List<String> res = new ArrayList<String>();
    generateParenthesesHelper(n << 1, 0, s, res);
    return res;
  }

  private static void generateParenthesesHelper(int remained, int leftParens,
      String s, List<String> res) {
    if (remained == 0) {
      res.add(s);
      return;
    }

    if (leftParens < remained) { // is able to insert '('.
      generateParenthesesHelper(remained - 1, leftParens + 1, s + "(", res);
    }
    if (leftParens > 0) { // is able to insert ')'.
      generateParenthesesHelper(remained - 1, leftParens - 1, s + ")", res);
    }
  }

  // @exclude

  private static void smallTest() {
    List<String> res = generateParentheses(1);
    assert (res.size() == 1 && res.get(0).equals("()"));
    res = generateParentheses(2);
    assert (res.size() == 2 && ((res.get(0).equals("(())") && res.get(1)
        .equals("()()")) || (res.get(0).equals("()()") && res.get(1).equals(
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
    List<String> res = generateParentheses(n);
    System.out.println(res);
  }
}
