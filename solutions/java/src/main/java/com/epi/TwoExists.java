package com.epi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TwoExists {
  // @include
  public static class GraphVertex {
    public enum Color { WHITE, GRAY, BLACK }

    public Color color;
    public List<GraphVertex> edges;

    // @exclude
    public GraphVertex() {
      color = Color.WHITE;
      edges = new ArrayList<>();
    }

    @Override
    public String toString() {
      return edges.toString();
    }
    // @include
  }

  public static boolean isGraphMinimallyConnected(List<GraphVertex> G) {
    if (!G.isEmpty()) {
      return !hasCycle(G.get(0), null);
    }
    return true;
  }

  private static boolean hasCycle(GraphVertex cur, GraphVertex pre) {
    // Visiting a gray vertex means a cycle.
    if (cur.color == GraphVertex.Color.GRAY) {
      return true;
    }

    cur.color = GraphVertex.Color.GRAY; // marks current vertex as a gray one.
    // Traverse the neighbor vertices.
    for (GraphVertex next : cur.edges) {
      if (next != pre && next.color != GraphVertex.Color.BLACK) {
        if (hasCycle(next, cur)) {
          return true;
        }
      }
    }
    cur.color = GraphVertex.Color.BLACK; // marks current vertex as black.
    return false;
  }
  // @exclude

  private static void hasCycleExclusion(GraphVertex cur, GraphVertex a,
                                        GraphVertex b) {
    cur.color = GraphVertex.Color.BLACK;
    for (GraphVertex next : cur.edges) {
      if (next.color == GraphVertex.Color.WHITE &&
          ((cur != a && cur != b) || (next != a && next != b))) {
        hasCycleExclusion(next, a, b);
      }
    }
  }

  // O(n^2) check answer.
  private static boolean checkAnswer(List<GraphVertex> G) {
    // marks all vertices as white.
    for (GraphVertex n : G) {
      n.color = GraphVertex.Color.WHITE;
    }

    for (GraphVertex g : G) {
      for (GraphVertex t : g.edges) {
        hasCycleExclusion(g, g, t);
        int count = 0;
        for (GraphVertex n : G) {
          if (n.color == GraphVertex.Color.BLACK) {
            ++count;
            n.color = GraphVertex.Color.WHITE;
          }
        }
        if (count == G.size()) {
          return false;
        }
      }
    }
    return true;
  }

  private static void testUndirectedCycle() {
    int n = 3;
    List<GraphVertex> G = new ArrayList<>(n);
    for (int i = 0; i < n; i++) {
      G.add(new GraphVertex());
    }
    G.get(0).edges.add(G.get(2));
    G.get(1).edges.add(G.get(0));
    G.get(2).edges.add(G.get(1));
    G.get(2).edges.add(G.get(0));
    G.get(0).edges.add(G.get(1));
    G.get(1).edges.add(G.get(2));
    boolean res = isGraphMinimallyConnected(G);
    System.out.println(res);
    assert(checkAnswer(G) == res);
  }

  private static void testUndirectedStarTree() {
    int n = 4;
    List<GraphVertex> G = new ArrayList<>(n);
    for (int i = 0; i < n; i++) {
      G.add(new GraphVertex());
    }
    G.get(0).edges.add(G.get(1));
    G.get(1).edges.add(G.get(0));
    G.get(0).edges.add(G.get(2));
    G.get(2).edges.add(G.get(0));
    G.get(0).edges.add(G.get(3));
    G.get(3).edges.add(G.get(0));
    boolean res = isGraphMinimallyConnected(G);
    System.out.println(res);
    assert(checkAnswer(G) == res);
    assert(res);
  }

  private static void testUndirectedLineTree() {
    int n = 4;
    List<GraphVertex> G = new ArrayList<>(n);
    for (int i = 0; i < n; i++) {
      G.add(new GraphVertex());
    }
    G.get(0).edges.add(G.get(1));
    G.get(1).edges.add(G.get(0));
    G.get(1).edges.add(G.get(2));
    G.get(2).edges.add(G.get(1));
    G.get(2).edges.add(G.get(3));
    G.get(3).edges.add(G.get(2));
    boolean res = isGraphMinimallyConnected(G);
    System.out.println(res);
    assert(checkAnswer(G) == res);
    assert(res);
    G.get(1).edges.add(G.get(3));
    G.get(3).edges.add(G.get(1));
    res = isGraphMinimallyConnected(G);
    assert(!res);
  }

  private static void testUndirectedBinaryTree() {
    int n = 7;
    List<GraphVertex> G = new ArrayList<>(n);
    for (int i = 0; i < n; i++) {
      G.add(new GraphVertex());
    }
    G.get(0).edges.add(G.get(1));
    G.get(1).edges.add(G.get(0));
    G.get(0).edges.add(G.get(2));
    G.get(2).edges.add(G.get(0));
    G.get(1).edges.add(G.get(3));
    G.get(3).edges.add(G.get(1));
    G.get(1).edges.add(G.get(4));
    G.get(4).edges.add(G.get(1));
    G.get(2).edges.add(G.get(5));
    G.get(5).edges.add(G.get(2));
    G.get(2).edges.add(G.get(6));
    G.get(6).edges.add(G.get(2));
    boolean res = isGraphMinimallyConnected(G);
    System.out.println(res);
    assert(checkAnswer(G) == res);
    assert(res);
    G.get(4).edges.add(G.get(6));
    G.get(6).edges.add(G.get(4));
    res = isGraphMinimallyConnected(G);
    assert(!res);
  }

  private static void testUndirectedTwoSeparateCycles() {
    int n = 6;
    List<GraphVertex> G = new ArrayList<>(n);
    for (int i = 0; i < n; i++) {
      G.add(new GraphVertex());
    }
    G.get(0).edges.add(G.get(1));
    G.get(1).edges.add(G.get(0));
    G.get(1).edges.add(G.get(2));
    G.get(2).edges.add(G.get(1));
    G.get(2).edges.add(G.get(0));
    G.get(0).edges.add(G.get(2));
    G.get(3).edges.add(G.get(4));
    G.get(4).edges.add(G.get(3));
    G.get(4).edges.add(G.get(5));
    G.get(5).edges.add(G.get(4));
    G.get(5).edges.add(G.get(3));
    G.get(3).edges.add(G.get(5));
    boolean res = isGraphMinimallyConnected(G);
    // since we just check for a cycle, result is false.
    // this is ok, because we assume input is connected.
    assert(!res);
  }

  public static void main(String[] args) {
    testUndirectedCycle();
    testUndirectedStarTree();
    testUndirectedLineTree();
    testUndirectedBinaryTree();
    testUndirectedTwoSeparateCycles();
    Random r = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = r.nextInt(1999) + 2;
      }
      List<GraphVertex> G = new ArrayList<>(n);
      for (int i = 0; i < n; i++) {
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
      boolean res = isGraphMinimallyConnected(G);
      System.out.println(res);
      assert(checkAnswer(G) == res);
    }
  }
}
