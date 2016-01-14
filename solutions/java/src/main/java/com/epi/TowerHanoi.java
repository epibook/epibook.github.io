package com.epi;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.LinkedList;
import java.util.Random;

public class TowerHanoi {
  private static int numSteps;
  // @include
  private static final int NUM_PEGS = 3;

  public static void computeTowerHanoi(int numRings) {
    List<Deque<Integer>> pegs = new ArrayList<>();
    for (int i = 0; i < NUM_PEGS; i++) {
      pegs.add(new LinkedList<Integer>());
    }
    // Initialize pegs.
    for (int i = numRings; i >= 1; --i) {
      pegs.get(0).addFirst(i);
    }
    computeTowerHanoiSteps(numRings, pegs, 0, 1, 2);
  }

  private static void computeTowerHanoiSteps(int numRingsToMove,
                                             List<Deque<Integer>> pegs,
                                             int fromPeg, int toPeg,
                                             int usePeg) {
    if (numRingsToMove > 0) {
      computeTowerHanoiSteps(numRingsToMove - 1, pegs, fromPeg, usePeg, toPeg);
      pegs.get(toPeg).addFirst(pegs.get(fromPeg).removeFirst());
      System.out.println("Move from peg " + fromPeg + " to peg " + toPeg);
      // @exclude
      numSteps++;
      // @include
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

    numSteps = 0;
    computeTowerHanoi(4);
    assert(15 == numSteps);

    numSteps = 0;
    computeTowerHanoi(1);
    assert(1 == numSteps);

    numSteps = 0;
    computeTowerHanoi(0);
    assert(0 == numSteps);

    numSteps = 0;
    computeTowerHanoi(10);
    assert(1023 == numSteps);
  }
}
