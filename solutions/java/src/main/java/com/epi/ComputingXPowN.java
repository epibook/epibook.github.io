package com.epi;

import java.util.*;
import java.util.LinkedList;

public class ComputingXPowN {
  // @include
  public static List<Integer> getShortestStraightLineProgram(int n) {
    if (n == 1) {
      return Arrays.asList(1);
    }

    LinkedList<ArrayList<Integer>> expLists = new LinkedList<>();
    // Constructs the initial list with one node whose value is 1.
    expLists.addLast(new ArrayList<Integer>() {
      { add(1); }
    });
    while (!expLists.isEmpty()) {
      List<Integer> exp = expLists.pop();
      // Tries all possible combinations in list exp.
      for (int a : exp) {
        int sum = a + exp.get(exp.size() - 1);
        if (sum > n) {
          break; // No possible solution.
        }

        ArrayList<Integer> newExp = new ArrayList<>(exp);
        newExp.add(sum);
        if (sum == n) {
          return newExp;
        }
        expLists.addLast(newExp);
      }
    }
    // @exclude
    // This line should never be called.
    throw new RuntimeException("unknown error");
    // @include
  }
  // @exclude

  public static void main(String[] args) {
    Random r = new Random();
    int n;
    if (args.length == 1) {
      n = Integer.parseInt(args[0]);
    } else {
      n = r.nextInt(100);
    }
    System.out.println("n = " + n);
    List<Integer> minExp = getShortestStraightLineProgram(n);
    System.out.println(minExp);
    System.out.println("size = " + minExp.size());
  }
}
