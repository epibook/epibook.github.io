package com.epi;

import java.util.Deque;
import java.util.LinkedList;

public class RPN {
  // @include
  public static int eval(String RPNExpression) {
    Deque<Integer> intermediateResults = new LinkedList<>();
    String delimiter = ",";
    String[] symbols = RPNExpression.split(delimiter);
    for (String token : symbols) {
      if (token.length() == 1 && "+-*/".contains(token)) {
        int y = intermediateResults.removeFirst();
        int x = intermediateResults.removeFirst();
        switch (token.charAt(0)) {
          case '+':
            intermediateResults.addFirst(x + y);
            break;
          case '-':
            intermediateResults.addFirst(x - y);
            break;
          case '*':
            intermediateResults.addFirst(x * y);
            break;
          case '/':
            intermediateResults.addFirst(x / y);
            break;
          default:
            throw new IllegalArgumentException("Malformed RPN at :" + token);
        }
      } else { // token is a number.
        intermediateResults.addFirst(Integer.parseInt(token));
      }
    }
    return intermediateResults.removeFirst();
  }
  // @exclude

  public static void main(String[] args) {
    assert(0 == eval("2,-10,/"));
    assert(-5 == eval("-10,2,/"));
    assert(5 == eval("-10,-2,/"));
    assert(-5 == eval("5,10,-"));
    assert(6 == eval("-10,-16,-"));
    assert(12 == eval("10,2,+"));
    assert(15 == eval("1,2,+,3,4,*,+"));
    assert(42 == eval("1,2,3,4,5,+,*,+,+,3,4,*,+"));
    assert(-6 == eval("1,2,3,4,5,+,*,+,+,3,4,*,+,-7,/"));
  }
}
