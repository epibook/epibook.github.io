package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class ComputingXPowN {
  // @include
  public static List<Integer> getMinimumExpression(int n) {
    if (n == 1) {
      return Arrays.asList(1);
    }

    LinkedList<ArrayList<Integer>> expLists = new LinkedList<>();
    expLists.addLast(new ArrayList<Integer>() {
      {
        add(1);
      }
    }); // construct the initial list with one node
    // whose value is 1.
    while (!expLists.isEmpty()) {
      ArrayList<Integer> exp = expLists.pop();
      // Try all possible combinations in list exp.
      for (int a : exp) {
        int sum = a + exp.get(exp.size() - 1);
        if (sum > n) {
          break; // no possible solution.
        }

        ArrayList<Integer> newExp = new ArrayList<Integer>(exp);
        newExp.add(sum);
        if (sum == n) {
          return newExp;
        }
        expLists.addLast(newExp);
      }
    }
    // @exclude
    throw new RuntimeException("unknown error"); // this line should never be
                                                 // called.
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
    List<Integer> minExp = getMinimumExpression(n);
    System.out.println(minExp);
    System.out.println("size = " + minExp.size());
  }
}
