package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PowerSetAlternative {
  // @include
  public static List<List<Integer>> generatePowerSet(List<Integer> inputSet) {
    List<List<Integer>> powerSet = new ArrayList<>();
    directedPowerSet(inputSet, 0, new ArrayList<Integer>(), powerSet);
    return powerSet;
  }

  // Generate all subsets whose intersection with inputSet[0], ...,
  // inputSet[toBeSelected - 1] is exactly selectedSoFar.
  private static void directedPowerSet(List<Integer> inputSet, int toBeSelected,
                                       List<Integer> selectedSoFar,
                                       List<List<Integer>> powerSet) {
    if (toBeSelected == inputSet.size()) {
      powerSet.add(new ArrayList<>(selectedSoFar));
      return;
    }
    // Generate all subsets that contain inputSet[toBeSelected].
    selectedSoFar.add(inputSet.get(toBeSelected));
    directedPowerSet(inputSet, toBeSelected + 1, selectedSoFar, powerSet);
    // Generate all subsets that do not contain inputSet[toBeSelected].
    selectedSoFar.remove(selectedSoFar.size() - 1);
    directedPowerSet(inputSet, toBeSelected + 1, selectedSoFar, powerSet);
  }
  // @exclude

  private static void simpleTest() {
    List<List<Integer>> goldenResult = Arrays.asList(
        Arrays.asList(0, 1, 2), Arrays.asList(0, 1), Arrays.asList(0, 2),
        Arrays.asList(0), Arrays.asList(1, 2), Arrays.asList(1),
        Arrays.asList(2), new ArrayList<Integer>());
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
