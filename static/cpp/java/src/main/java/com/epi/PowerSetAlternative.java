package com.epi;

import java.util.*;
import java.util.LinkedList;

public class PowerSetAlternative {
  // @include
  public static List<List<Integer>> generatePowerSet(List<Integer> S) {
    List<List<Integer>> powerSet = new ArrayList<>();
    List<Integer> oneSet = new ArrayList<>();
    generatePowerSetHelper(S, 0, oneSet, powerSet);
    return powerSet;
  }

  private static void generatePowerSetHelper(List<Integer> S, int idx,
                                             List<Integer> oneSet,
                                             List<List<Integer>> powerSet) {
    powerSet.add(new ArrayList<>(oneSet));
    for (int i = idx; i < S.size(); ++i) {
      oneSet.add(S.get(i));
      generatePowerSetHelper(S, i + 1, oneSet, powerSet);
      oneSet.remove(oneSet.size() - 1);
    }
  }
  // @exclude

  private static void simpleTest() {
    List<List<Integer>> goldenResult = Arrays.asList(
        new ArrayList<Integer>(),
        Arrays.asList(0),
        Arrays.asList(0, 1),
        Arrays.asList(0, 1, 2),
        Arrays.asList(0, 2),
        Arrays.asList(1),
        Arrays.asList(1, 2),
        Arrays.asList(2));
    List<List<Integer>> result = generatePowerSet(Arrays.asList(0, 1, 2));
    assert (result.equals(goldenResult));
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
      List<List<Integer>> powerSet = generatePowerSet(S);
      for (List<Integer> oneSet : powerSet) {
        for (Integer ele : oneSet) {
          System.out.print(ele + " ");
        }
        System.out.println();
      }
    }
  }
}
