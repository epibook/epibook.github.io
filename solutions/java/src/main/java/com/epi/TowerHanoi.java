package com.epi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class TowerHanoi {
  // @include
  private static final int NUM_PEGS = 3;

  public static void computeTowerHanoi(int numRings) {
    List<Stack<Integer>> pegs = new ArrayList<>();
    for (int i = 0; i < NUM_PEGS; i++) {
      pegs.add(new Stack<Integer>());
    }
    // Initialize pegs.
    for (int i = numRings; i >= 1; --i) {
      pegs.get(0).push(i);
    }

    computeTowerHanoiSteps(numRings, pegs, 0, 1, 2);
  }

  private static void computeTowerHanoiSteps(int numRingsToMove,
                                             List<Stack<Integer>> pegs,
                                             int fromPeg, int toPeg, int usePeg) {
    if (numRingsToMove > 0) {
      computeTowerHanoiSteps(numRingsToMove - 1, pegs, fromPeg, usePeg, toPeg);
      pegs.get(toPeg).push(pegs.get(fromPeg).pop());
      System.out.println("Move from peg " + fromPeg + " to peg " + toPeg);
      computeTowerHanoiSteps(numRingsToMove - 1, pegs, usePeg, toPeg, fromPeg);
    }
  }
  // @exclude

  public static void main(String[] args) {
    int n;
    if (args.length == 1) {
      n = Integer.parseInt(args[0]);
    } else {
      Random r = new Random();
      n = r.nextInt(10) + 1;
    }
    System.out.println("n = " + n);
    computeTowerHanoi(n);
  }
}
