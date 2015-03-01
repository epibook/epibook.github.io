package com.epi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class TwoForAll {
  // @include
  public static class GraphVertex {
    public int d, l; // discovery and leaving time.
    public List<GraphVertex> edges;

    // @exclude
    public GraphVertex() {
      d = 0;
      l = Integer.MAX_VALUE;
      edges = new ArrayList<>();
    }

    @Override
    public String toString() {
      return edges.toString();
    }
    // @include
  }

  public static boolean isGraphTwoForAll(List<GraphVertex> G) {
    if (!G.isEmpty()) {
      return DFS(G.get(0), null, 0);
    }
    return true;
  }

  private static boolean DFS(GraphVertex cur, GraphVertex pre, int time) {
    cur.d = ++time;
    cur.l = Integer.MAX_VALUE;
    for (GraphVertex next : cur.edges) {
      if (next != pre) {
        if (next.d != 0) { // back edge.
          cur.l = Math.min(cur.l, next.d);
        } else { // forward edge.
          if (!DFS(next, cur, time)) {
            return false;
          }
          cur.l = Math.min(cur.l, next.l);
        }
      }
    }
    return (pre == null || cur.l < cur.d);
  }
  // @exclude

  private static void
  dfsExclusion(GraphVertex cur, GraphVertex a, GraphVertex b) {
    cur.d = 1;
    for (GraphVertex next : cur.edges) {
      if (next.d == 0
          && ((cur != a && cur != b) || (next != a && next != b))) {
        dfsExclusion(next, a, b);
      }
    }
  }

  // O(n^2) check answer.
  private static boolean checkAnswer(List<GraphVertex> G) {
    // marks all vertices as white.
    for (GraphVertex n : G) {
      n.d = 0;
    }

    for (GraphVertex g : G) {
      for (GraphVertex t : g.edges) {
        dfsExclusion(g, g, t);
        int count = 0;
        for (GraphVertex n : G) {
          if (n.d == 1) {
            ++count;
            n.d = 0;
          }
        }
        if (count != G.size()) {
          return false;
        }
      }
    }
    return true;
  }

  public static void main(String[] args) {
    Random r = new Random();
    for (int times = 0; times < 1000; ++times) {
      List<GraphVertex> G = new ArrayList<>();
      int n;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = r.nextInt(100) + 2;
      }
      for (int i = 0; i < n; ++i) {
        G.add(new GraphVertex());
      }
      int m = r.nextInt(n * (n - 1) / 2) + 1;
      boolean[][] isEdgeExist = new boolean[n][n];
      // Make the graph become connected.
      for (int i = 1; i < n; ++i) {
        G.get(i - 1).edges.add(G.get(i));
        G.get(i).edges.add(G.get(i - 1));
        isEdgeExist[i - 1][i] = isEdgeExist[i][i - 1] = true;
      }

      // Generate edges randomly.
      m -= (n - 1);
      while (m-- > 0) {
        int a, b;
        do {
          a = r.nextInt(n);
          b = r.nextInt(n);
        } while (a == b || isEdgeExist[a][b]);
        isEdgeExist[a][b] = isEdgeExist[b][a] = true;
        G.get(a).edges.add(G.get(b));
        G.get(b).edges.add(G.get(a));
      }
      // System.out.println(G);

      boolean res = isGraphTwoForAll(G);
      System.out.println(res);
      assert (checkAnswer(G) == res);
    }
  }
}
