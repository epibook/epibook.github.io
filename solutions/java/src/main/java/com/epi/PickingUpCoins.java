package com.epi;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PickingUpCoins {
  // @include
  public static int pickUpCoins(List<Integer> C) {
    List<List<Integer>> T = new ArrayList<>(C.size());
    for (int i = 0; i < C.size(); ++i) {
      T.add(new ArrayList(Collections.nCopies(C.size(), -1)));
    }
    return pickUpCoinsHelper(C, 0, C.size() - 1, T);
  }

  private static int pickUpCoinsHelper(List<Integer> C, int a, int b,
                                       List<List<Integer>> T) {
    if (a > b) {
      return 0; // Base condition.
    }

    if (T.get(a).get(b) == -1) {
      T.get(a).set(
          b, Math.max(C.get(a)
                          + Math.min(pickUpCoinsHelper(C, a + 2, b, T),
                                     pickUpCoinsHelper(C, a + 1, b - 1, T)),
                      C.get(b)
                          + Math.min(pickUpCoinsHelper(C, a + 1, b - 1, T),
                                     pickUpCoinsHelper(C, a, b - 2, T))));
    }
    return T.get(a).get(b);
  }
  // @exclude

  public static void main(String[] args) {
    Random r = new Random();
    List<Integer> C = new ArrayList<>();
    if (args.length >= 1) {
      for (int i = 1; i < args.length; ++i) {
        C.add(Integer.parseInt(args[i]));
      }
    } else {
      int size = r.nextInt(1000) + 1;
      for (int i = 0; i < size; ++i) {
        C.add(r.nextInt(100));
      }
    }
    System.out.println(C);
    System.out.println(pickUpCoins(C));
  }
}
