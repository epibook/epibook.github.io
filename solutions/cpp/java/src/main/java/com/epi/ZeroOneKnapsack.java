package com.epi;

import com.epi.utils.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class ZeroOneKnapsack {
  private static List<Integer> randVector(int len) {
    List<Integer> ret = new ArrayList<>();
    Random r = new Random();
    while (len-- != 0) {
      ret.add(r.nextInt(100));
    }
    return ret;
  }

  // @include
  public static int knapsack(int w, List<Pair<Integer, Integer>> items) {
    int[] V = new int[w + 1];
    for (Pair<Integer, Integer> item : items) {
      for (int j = w; j >= item.getFirst(); --j) {
        V[j] = Math.max(V[j], V[j - item.getFirst()] + item.getSecond());
      }
    }
    return V[w];
  }
  // @exclude

  public static void main(String[] args) {
    Random r = new Random();
    List<Integer> weight = new ArrayList<>();
    List<Integer> value = new ArrayList<>();
    int n, W;
    if (args.length == 0) {
      n = r.nextInt(100) + 1;
      W = r.nextInt(1000) + 1;
      weight = randVector(n);
      value = randVector(n);
    } else if (args.length == 1) {
      n = Integer.parseInt(args[0]);
      W = r.nextInt(1000) + 1;
      weight = randVector(n);
      value = randVector(n);
    } else {
      n = Integer.parseInt(args[0]);
      W = Integer.parseInt(args[1]);
      for (int i = 0; i < n; ++i) {
        weight.add(Integer.parseInt(args[2 + i]));
      }
      for (int i = 0; i < n; ++i) {
        value.add(Integer.parseInt(args[2 + i + n]));
      }
    }
    System.out.println("Weight: " + weight);
    System.out.println("Value: " + value);
    List<Pair<Integer, Integer>> items = new ArrayList<>();
    for (int i = 0; i < weight.size(); ++i) {
      items.add(new Pair<>(weight.get(i), value.get(i)));
    }
    System.out.println("knapsack size = " + W);
    System.out.println("all value = " + knapsack(W, items));
  }
}
