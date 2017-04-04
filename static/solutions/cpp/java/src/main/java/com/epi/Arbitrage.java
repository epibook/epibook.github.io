// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Arbitrage {
  // @include
  public static boolean isArbitrageExist(List<List<Double>> G) {
    // Transforms each edge in G.
    for (List<Double> edgeList : G) {
      for (int i = 0; i < edgeList.size(); i++) {
        edgeList.set(i, -Math.log10(edgeList.get(i)));
      }
    }

    // Uses Bellman-Ford to find negative weight cycle.
    return bellmanFord(G, 0);
  }

  private static boolean bellmanFord(List<List<Double>> G, int source) {
    List<Double> disToSource
        = new ArrayList<>(Collections.nCopies(G.size(), Double.MAX_VALUE));
    disToSource.set(source, 0.0);

    for (int times = 1; times < G.size(); ++times) {
      boolean haveUpdate = false;
      for (int i = 0; i < G.size(); ++i) {
        for (int j = 0; j < G.get(i).size(); ++j) {
          if (disToSource.get(i) != Double.MAX_VALUE
              && disToSource.get(j) > disToSource.get(i) + G.get(i).get(j)) {
            haveUpdate = true;
            disToSource.set(j, disToSource.get(i) + G.get(i).get(j));
          }
        }
      }

      // No update in this iteration means no negative cycle.
      if (!haveUpdate) {
        return false;
      }
    }

    // Detects cycle if there is any further update.
    for (int i = 0; i < G.size(); ++i) {
      for (int j = 0; j < G.get(i).size(); ++j) {
        if (disToSource.get(i) != Double.MAX_VALUE
            && disToSource.get(i) > disToSource.get(i) + G.get(i).get(j)) {
          return true;
        }
      }
    }
    return false;
  }
  // @exclude

  public static void main(String[] args) {
    Random r = new Random();
    int n, m;
    if (args.length == 1) {
      n = Integer.parseInt(args[0]);
      m = r.nextInt(n * (n - 1) / 2) + 1;
    } else if (args.length == 2) {
      n = Integer.parseInt(args[0]);
      m = Integer.parseInt(args[1]);
    } else {
      n = r.nextInt(100) + 1;
      m = r.nextInt(n * (n - 1) / 2) + 1;
    }
    List<List<Double>> G = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      List<Double> newList = new ArrayList<>();
      for (int j = 0; j < n; j++) {
        newList.add(0.0);
      }
      G.add(newList);
    }
    // Assume the input is a complete graph.
    for (int i = 0; i < G.size(); ++i) {
      G.get(i).set(i, 1.0);
      for (int j = i + 1; j < G.size(); ++j) {
        G.get(i).set(j, r.nextDouble());
        G.get(j).set(i, 1.0 / G.get(i).get(j));
      }
    }
    boolean res = isArbitrageExist(G);
    System.out.println(res);

    List<List<Double>> g = new ArrayList<>();
    for (int i = 0; i < 3; i++) {
      List<Double> newList = new ArrayList<>();
      for (int j = 0; j < 3; j++) {
        newList.add(0.0);
      }
      g.add(newList);
    }
    g.get(0).set(1, 2.0);
    g.get(1).set(0, 0.5);
    g.get(0).set(2, 1.0);
    g.get(2).set(0, 1.0);
    g.get(1).set(2, 4.0);
    g.get(2).set(1, 0.25);
    res = isArbitrageExist(g);
    assert res;
    System.out.println(res);
  }
}
