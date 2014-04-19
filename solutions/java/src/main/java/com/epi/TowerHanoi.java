package com.epi;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class TowerHanoi {
  // @include
  private static void transfer(int n, ArrayList<LinkedList<Integer>> pegs,
      int from, int to, int use) {
    if (n > 0) {
      transfer(n - 1, pegs, from, use, to);
      pegs.get(to).push(pegs.get(from).pop());
      System.out.println("Move from peg " + from + " to peg " + to);
      transfer(n - 1, pegs, use, to, from);
    }
  }

  public static void moveTowerHanoi(int n) {
    ArrayList<LinkedList<Integer>> pegs = new ArrayList<LinkedList<Integer>>();
    for (int i = 0; i < 3; i++) {
      pegs.add(new LinkedList<Integer>());
    }

    // Initialize pegs.
    for (int i = n; i >= 1; --i) {
      pegs.get(0).push(i);
    }

    transfer(n, pegs, 0, 1, 2);
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
    moveTowerHanoi(n);
  }
}
