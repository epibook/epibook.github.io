package com.epi;

/*

@slug
RPN

@summary
Reverse-Polish Notation (RPN) is a way of writing arithmetical expression that
is in certain respects simpler than the usual 2 + 3 * 4.
The following strings satisfy are examples of RPN : ``$1729$'',
``$3,4,+,2,\times, 1,+$'',
``$1,1,+,-2,\times$'', ``$-641,6,/,28,/$''.
An RPN expression can be evaluated uniquely to an integer.

@problem
Write a program that takes an arithmetical expression in RPN
and returns the number that the expression evaluates to.
*/

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
        final int y = intermediateResults.removeFirst();
        final int x = intermediateResults.removeFirst();
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
