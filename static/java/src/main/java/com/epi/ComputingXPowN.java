package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class ComputingXPowN {
  // @include
  public static List<Integer> shortestAdditionChain(int n) {
    if (n == 1) {
      return Arrays.asList(1);
    }

    Queue<List<Integer>> additionChains = new LinkedList<>();
    // Constructs the initial additionChain with one node whose value is 1.
    additionChains.add(Arrays.asList(1));
    while (!additionChains.isEmpty()) {
      List<Integer> candidateAdditionChain = additionChains.remove();
      // Tries all possible combinations in candidateAdditionChain.
      for (int a : candidateAdditionChain) {
        int sum
            = a + candidateAdditionChain.get(candidateAdditionChain.size() - 1);
        if (sum > n) {
          break; // No possible solution for candidateAdditionChain.
        }

        List<Integer> newAdditionChain
            = new ArrayList<>(candidateAdditionChain);
        newAdditionChain.add(sum);

        if (sum == n) {
          return newAdditionChain;
        }
        additionChains.add(newAdditionChain);
      }
    }
    // @exclude
    // This line should never be called.
    throw new IllegalStateException("Program logic broken!");
    // @include
  }
  // @exclude

  private static void smallTest() {
    List<Integer> res = shortestAdditionChain(88);
    List<Integer> goldenRes = Arrays.asList(1, 2, 3, 4, 7, 11, 22, 44, 88);
    assert(res.equals(goldenRes));
    res = shortestAdditionChain(67);
    goldenRes = Arrays.asList(1, 2, 3, 4, 8, 16, 32, 35, 67);
    assert(res.equals(goldenRes));
  }

  public static void main(String[] args) {
    smallTest();
    Random r = new Random();
    int n;
    if (args.length == 1) {
      n = Integer.parseInt(args[0]);
    } else {
      n = r.nextInt(100);
    }
    System.out.println("n = " + n);
    List<Integer> minExp = shortestAdditionChain(n);
    System.out.println(minExp);
    System.out.println("size = " + minExp.size());
  }
}
