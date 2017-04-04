package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
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

  public static int optimumSubjectToCapacity(List<Item> items, int capacity) {
    // V[i][j] holds the optimum value when we choose from items[0 : i] and have
    // a capacity of j.
    int[][] V = new int[items.size()][capacity + 1];
    for (int[] v : V) {
      Arrays.fill(v, -1);
    }
    return optimumSubjectToItemAndCapacity(items, items.size() - 1, capacity,
                                           V);
  }

  // Returns the optimum value when we choose from items[0 : k] and have a
  // capacity of available_capacity.
  private static int optimumSubjectToItemAndCapacity(List<Item> items, int k,
                                                     int availableCapacity,
                                                     int[][] V) {
    if (k < 0) {
      // No items can be chosen.
      return 0;
    }

    if (V[k][availableCapacity] == -1) {
      int withoutCurrItem
          = optimumSubjectToItemAndCapacity(items, k - 1, availableCapacity, V);
      int withCurrItem
          = availableCapacity < items.get(k).weight
                ? 0
                : items.get(k).value
                      + optimumSubjectToItemAndCapacity(
                            items, k - 1,
                            availableCapacity - items.get(k).weight, V);
      V[k][availableCapacity] = Math.max(withoutCurrItem, withCurrItem);
    }
    return V[k][availableCapacity];
  }
  // @exclude

  private static void smallTest() {
    // The example in the book.
    List<Item> items = Arrays.asList(
        new Item(20, 65), new Item(8, 35), new Item(60, 245), new Item(55, 195),
        new Item(40, 65), new Item(70, 150), new Item(85, 275),
        new Item(25, 155), new Item(30, 120), new Item(65, 320),
        new Item(75, 75), new Item(10, 40), new Item(95, 200),
        new Item(50, 100), new Item(40, 220), new Item(10, 99));
    assert(695 == optimumSubjectToCapacity(items, 130));

    items = Arrays.asList(new Item(5, 60), new Item(3, 50), new Item(4, 70),
                          new Item(2, 30));
    assert(80 == optimumSubjectToCapacity(items, 5));
  }

  public static void main(String[] args) {
    smallTest();
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
    System.out.println("all value = " + optimumSubjectToCapacity(items, W));
  }
}
