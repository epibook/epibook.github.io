package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PowerSet {
  // @include
  private static final double LOG_2 = Math.log(2);

  public static List<List<Integer>> generatePowerSet(List<Integer> inputSet) {
    List<List<Integer>> powerSet = new ArrayList<>();
    for (int intForSubset = 0; intForSubset < (1 << inputSet.size());
         ++intForSubset) {
      int bitArray = intForSubset;
      List<Integer> subset = new ArrayList<>();
      while (bitArray != 0) {
        subset.add(
            inputSet.get((int)(Math.log(bitArray & ~(bitArray - 1)) / LOG_2)));
        bitArray &= bitArray - 1;
      }
      powerSet.add(subset);
    }
    return powerSet;
  }
  // @exclude

  private static void simpleTest() {
    List<List<Integer>> goldenResult = Arrays.asList(
        new ArrayList<Integer>(), Arrays.asList(0), Arrays.asList(1),
        Arrays.asList(0, 1), Arrays.asList(2), Arrays.asList(0, 2),
        Arrays.asList(1, 2), Arrays.asList(0, 1, 2));
    List<List<Integer>> result = generatePowerSet(Arrays.asList(0, 1, 2));
    assert(result.equals(goldenResult));
  }

  public static void main(String[] args) {
    simpleTest();
    List<Integer> S = new ArrayList<>();
    if (args.length >= 1) {
      for (String arg : args) {
        S.add(Integer.parseInt(arg));
      }
    } else {
      Random r = new Random();
      int count = r.nextInt(10) + 1;
      for (int i = 0; i < count; ++i) {
        S.add(i);
      }
    }
    List<List<Integer>> powerSet = generatePowerSet(S);
    for (List<Integer> oneSet : powerSet) {
      for (Integer ele : oneSet) {
        System.out.print(ele + " ");
      }
      System.out.println();
    }
  }
}
