package com.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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
  private static class Item {
    public Integer weight;
    public Integer value;

    public Item(Integer weight, Integer value) {
      this.weight = weight;
      this.value = value;
    }
  }

  public static int knapsack(int w, List<Item> items) {
    List<Integer> V = new ArrayList<>(Collections.nCopies(w + 1, 0));
    for (Item item : items) {
      for (int j = w; j >= item.weight; --j) {
        V.set(j, Math.max(V.get(j), V.get(j - item.weight) + item.value));
      }
    }
    return V.get(w);
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
    List<Item> items = new ArrayList<>();
    for (int i = 0; i < weight.size(); ++i) {
      items.add(new Item(weight.get(i), value.get(i)));
    }
    System.out.println("knapsack size = " + W);
    System.out.println("all value = " + knapsack(W, items));
  }
}
