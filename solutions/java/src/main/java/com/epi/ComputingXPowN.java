package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class ComputingXPowN {
  // @include
  public static List<Integer> getShortestStraightLineProgram(int n) {
    if (n == 1) {
      return Arrays.asList(1);
    }

    // SLP is acronym for straight line program.
    Queue<List<Integer>> SLPs = new LinkedList<>();
    // Constructs the initial SLP with one node whose value is 1.
    SLPs.add(Arrays.asList(1));
    while (!SLPs.isEmpty()) {
      List<Integer> candidateSLP = SLPs.remove();
      // Tries all possible combinations in candidateSLP.
      for (int a : candidateSLP) {
        int sum = a + candidateSLP.get(candidateSLP.size() - 1);
        if (sum > n) {
          break; // No possible solution for candidateSLP.
        }

        List<Integer> newSLP = new ArrayList<>(candidateSLP);
        newSLP.add(sum);

        if (sum == n) {
          return newSLP;
        }
        SLPs.add(newSLP);
      }
    }
    // @exclude
    // This line should never be called.
    throw new IllegalStateException("Program logic broken!");
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
