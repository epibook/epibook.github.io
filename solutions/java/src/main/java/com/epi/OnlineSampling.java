package com.epi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static com.epi.utils.Utils.simplePrint;

public class OnlineSampling {
  // @include
  // Returns a random k-sized subset of {0, 1, ..., n - 1}.
  public static List<Integer> randomSubset(int n, int k) {
    Map<Integer, Integer> changedElements = new HashMap<>();
    Random randIdxGen = new Random();
    for (int i = 0; i < k; ++i) {
      // Generate random int in [i, n - 1].
      int randIdx = i + randIdxGen.nextInt(n - i);
      Integer ptr1 = changedElements.get(randIdx);
      Integer ptr2 = changedElements.get(i);
      if (ptr1 == null && ptr2 == null) {
        changedElements.put(randIdx, i);
        changedElements.put(i, randIdx);
      } else if (ptr1 == null && ptr2 != null) {
        changedElements.put(randIdx, ptr2);
        changedElements.put(i, randIdx);
      } else if (ptr1 != null && ptr2 == null) {
        changedElements.put(i, ptr1);
        changedElements.put(randIdx, i);
      } else {
        changedElements.put(i, ptr1);
        changedElements.put(randIdx, ptr2);
      }
    }

    List<Integer> result = new ArrayList<>(k);
    for (int i = 0; i < k; ++i) {
      result.add(changedElements.get(i));
    }
    return result;
  }
  // @exclude

  public static void main(String[] args) {
    Random gen = new Random();
    int n, k;
    if (args.length == 1) {
      n = Integer.parseInt(args[0]);
      k = gen.nextInt(n) + 1;
    } else if (args.length == 2) {
      n = Integer.parseInt(args[0]);
      k = Integer.parseInt(args[1]);
    } else {
      n = gen.nextInt(10000);
      k = gen.nextInt(n) + 1;
    }

    System.out.println(String.format("n = %d k = %d", n, k));
    for (int i = 0; i < 100; ++i) {
      List<Integer> res = randomSubset(n, k);
      System.out.print("result = ");
      simplePrint(res);
      System.out.println();
    }
  }
}
