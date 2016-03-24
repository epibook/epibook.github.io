package com.epi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenerateParentheses {
  // @include
  public static List<String> generateBalancedParentheses(int numPairs) {
    List<String> result = new ArrayList<>();
    directedGenerateBalancedParentheses(numPairs, numPairs, "", result);
    return result;
  }

  private static void directedGenerateBalancedParentheses(
      int numLeftParensNeeded, int numRightParensNeeded, String validPrefix,
      List<String> result) {
    if (numLeftParensNeeded == 0 && numRightParensNeeded == 0) {
      result.add(validPrefix);
      return;
    }

    if (numLeftParensNeeded > 0) { // Able to insert '('.
      directedGenerateBalancedParentheses(numLeftParensNeeded - 1,
                                          numRightParensNeeded,
                                          validPrefix + "(", result);
    }
    if (numLeftParensNeeded < numRightParensNeeded) { // Able to insert ')'.
      directedGenerateBalancedParentheses(numLeftParensNeeded,
                                          numRightParensNeeded - 1,
                                          validPrefix + ")", result);
    }
  }
  // @exclude

  private static void smallTest() {
    List<String> result = generateBalancedParentheses(1);
    assert(result.size() == 1 && result.get(0).equals("()"));
    result = generateBalancedParentheses(2);
    assert(
        result.size() == 2
        && ((result.get(0).equals("(())") && result.get(1).equals("()()"))
            || (result.get(0).equals("()()") && result.get(1).equals("(())"))));
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
    List<String> result = generateBalancedParentheses(n);
    System.out.println(result);
  }
}
