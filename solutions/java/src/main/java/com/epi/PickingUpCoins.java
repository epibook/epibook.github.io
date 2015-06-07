package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PickingUpCoins {
  // @include
  public static int pickUpCoins(List<Integer> C) {
    int[][] T = new int[C.size()][C.size()];
    for (int[] t : T) {
      Arrays.fill(t, -1);
    }
    return pickUpCoinsHelper(C, 0, C.size() - 1, T);
  }

  private static int pickUpCoinsHelper(List<Integer> C, int a, int b, int[][] T) {
    if (a > b) {
      return 0; // Base condition.
    }

    if (T[a][b] == -1) {
      T[a][b] = Math.max(C.get(a) +
                             Math.min(pickUpCoinsHelper(C, a + 2, b, T),
                                      pickUpCoinsHelper(C, a + 1, b - 1, T)),
                         C.get(b) +
                             Math.min(pickUpCoinsHelper(C, a + 1, b - 1, T),
                                      pickUpCoinsHelper(C, a, b - 2, T)));
    }
    return T[a][b];
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
