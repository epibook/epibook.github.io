package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CelebrityFinding {
  // @include
  public static int celebrityFinding(List<List<Boolean>> F) {
    // Start checking the relation from F.get(0).get(1).
    int i = 0, j = 1;
    while (j < F.size()) {
      if (F.get(i).get(j)) {
        i = j++; // All candidates j' < j are not celebrity candidates.
      } else { // F.get(i).get(j) == false.
        ++j; // i is still a celebrity candidate but j is not.
      }
    }
    return i;
  }
  // @exclude

  private static void directedTest() {
    List<List<Boolean>> F
        = Arrays.asList(Arrays.asList(false, true, false, true, false),
                        Arrays.asList(false, false, true, true, false),
                        Arrays.asList(false, false, false, true, true),
                        Arrays.asList(false, false, false, false, false),
                        Arrays.asList(true, false, false, true, false));
    assert(celebrityFinding(F) == 3);
  }

  public static void main(String[] args) {
    directedTest();
    Random gen = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n;
      if (args.length > 0) {
        n = Integer.parseInt(args[0]);
      } else {
        n = gen.nextInt(1000) + 1;
      }
      List<List<Boolean>> graph = new ArrayList<>(n);
      for (int i = 0; i < n; ++i) {
        graph.add(new ArrayList(n));
        for (int j = 0; j < n; ++j) {
          graph.get(i).add(gen.nextBoolean());
        }
        graph.get(i).set(i, false);
      }
      int celebrity = gen.nextInt(n);
      for (int i = 0; i < n; ++i) {
        graph.get(i).set(celebrity, true);
      }
      for (int j = 0; j < n; ++j) {
        graph.get(celebrity).set(j, false);
      }
      System.out.println(celebrityFinding(graph));
      assert celebrity == celebrityFinding(graph);
    }
  }
}
