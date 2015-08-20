package com.epi;

import java.util.*;

public class AddOperatorsInString {
  // @include
  public static boolean expressionSynthesis(List<Integer> digits, int target) {
    List<Character> operators = new ArrayList<>();
    List<Integer> operands = new ArrayList<>();
    return directedExpressionSynthesis(digits, target, 0, 0, operands, operators);
  }

  private static boolean directedExpressionSynthesis(List<Integer> digits,
                                                     int target, int currentTerm,
                                                     int offset,
                                                     List<Integer> operands,
                                                     List<Character> operators) {
    currentTerm = currentTerm * 10 + digits.get(offset);
    if (offset == digits.size() - 1) {
      operands.add(currentTerm);
      if (evaluate(operands, operators) == target) { // Found a match.
        // @exclude
        int operandIdx = 0;
        System.out.print(operands.get(operandIdx));
        for (Character oper : operators) {
          ++operandIdx;
          System.out.print(" " + oper + " " + operands.get(operandIdx));
        }
        System.out.println(" = " + target);
        // @include
        return true;
      }
      operands.remove(operands.size() - 1);
      return false;
    }

    // No operator.
    if (directedExpressionSynthesis(digits, target, currentTerm, offset + 1,
                                    operands, operators)) {
      return true;
    }
    // Adds operator '*'.
    operands.add(currentTerm);
    operators.add('*');
    if (directedExpressionSynthesis(digits, target, 0, offset + 1, operands,
                                    operators)) {
      return true;
    }
    operators.remove(operators.size() - 1);
    operands.remove(operands.size() - 1);
    // Adds operator '+'.
    operands.add(currentTerm);
    if (target - evaluate(operands, operators) <=
        remainingInt(digits, offset + 1)) {
      operators.add('+');
      if (directedExpressionSynthesis(digits, target, 0, offset + 1, operands,
                                      operators)) {
        return true;
      }
      operators.remove(operators.size() - 1);
    }
    operands.remove(operands.size() - 1);
    return false;
  }

  // Calculates the int represented by digits[idx : digits.size() - 1].
  private static int remainingInt(List<Integer> digits, int idx) {
    int val = 0;
    for (int i = idx; i < digits.size(); ++i) {
      val = val * 10 + digits.get(idx);
    }
    return val;
  }

  private static int evaluate(List<Integer> operands, List<Character> operators) {
    Stack<Integer> intermediateOperands = new Stack<>();
    int operandIdx = 0;
    intermediateOperands.push(operands.get(operandIdx++));
    // Evaluates '*' first.
    for (char oper : operators) {
      if (oper == '*') {
        intermediateOperands.push(intermediateOperands.pop() *
                                  operands.get(operandIdx++));
      } else {
        intermediateOperands.push(operands.get(operandIdx++));
      }
    }

    // Evaluates '+' second.
    int sum = 0;
    while (!intermediateOperands.empty()) {
      sum += intermediateOperands.pop();
    }
    return sum;
  }
  // @exclude

  public static void main(String[] args) {
    List<Integer> A = Arrays.asList(2, 3, 4);
    int k = 4;
    assert !expressionSynthesis(A, k);
    A = Arrays.asList(1, 2, 3, 4);
    k = 11;
    assert expressionSynthesis(A, k);
    A = Arrays.asList(1, 2, 3, 2, 5, 3, 7, 8, 5, 9);
    k = 995;
    assert expressionSynthesis(A, k);
    A = Arrays.asList(5, 2, 3, 4, 1);
    k = 20;
    assert expressionSynthesis(A, k);
  }
}
