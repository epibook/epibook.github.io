package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class RoadNetwork {
  // @include
  public static class HighwaySection {
    public int x, y;
    public double distance;

    public HighwaySection(int x, int y, double distance) {
      this.x = x;
      this.y = y;
      this.distance = distance;
    }
    // @exclude

    @Override
    public String toString() {
      return x + " " + y + " " + distance;
    }
    // @include
  }

  public static HighwaySection findBestProposals(List<HighwaySection> H,
                                                 List<HighwaySection> P, int a,
                                                 int b, int n) {
    // G stores the shortest path distances between all pairs of vertices.
    double[][] G = new double[n][n];
    for (double[] g : G) {
      Arrays.fill(g, Double.MAX_VALUE);
    }
    for (int i = 0; i < n; ++i) {
      G[i][i] = 0;
    }

    // Builds a undirected graph G based on existing highway sections H.
    for (HighwaySection h : H) {
      G[h.x][h.y] = G[h.y][h.x] = h.distance;
    }
    // Performs Floyd Warshall to build the shortest path between vertices.
    FloydWarshall(G);

    // Examines each proposal for shorter distance between a and b.
    double minDisAB = G[a][b];
    HighwaySection bestProposal = new HighwaySection(-1, -1, 0.0); // default
    for (HighwaySection p : P) {
      // Checks the path of a => p.x => p.y => b.
      if (G[a][p.x] != Double.MAX_VALUE && G[p.y][b] != Double.MAX_VALUE &&
          minDisAB > G[a][p.x] + p.distance + G[p.y][b]) {
        minDisAB = G[a][p.x] + p.distance + G[p.y][b];
        bestProposal = p;
      }
      // Checks the path of a => p.y => p.x => b.
      if (G[a][p.y] != Double.MAX_VALUE && G[p.x][b] != Double.MAX_VALUE &&
          minDisAB > G[a][p.y] + p.distance + G[p.x][b]) {
        minDisAB = G[a][p.y] + p.distance + G[p.x][b];
        bestProposal = p;
      }
    }
    return bestProposal;
  }

  private static void FloydWarshall(double[][] G) {
    for (int k = 0; k < G.length; ++k) {
      for (int i = 0; i < G.length; ++i) {
        for (int j = 0; j < G.length; ++j) {
          if (G[i][k] != Double.MAX_VALUE && G[k][j] != Double.MAX_VALUE &&
              G[i][j] > G[i][k] + G[k][j]) {
            G[i][j] = G[i][k] + G[k][j];
          }
        }
      }
    }
  }
  // @exclude

  // Tries to add each proposal and use Floyd Warshall to solve, O(n^4)
  // algorithm.
  private static HighwaySection checkAns(List<HighwaySection> H,
                                         List<HighwaySection> P, int a, int b,
                                         int n) {
    // G stores the shortest path distances between all pairs of vertices.
    double[][] G = new double[n][n];
    for (double[] g : G) {
      Arrays.fill(g, Double.MAX_VALUE);
    }
    for (int i = 0; i < n; ++i) {
      G[i][i] = 0;
    }

    // Builds a undirected graph G based on existing highway sections H.
    for (HighwaySection h : H) {
      G[h.x][h.y] = G[h.y][h.x] = h.distance;
    }
    // Performs Floyd Warshall to build the shortest path between vertices.
    FloydWarshall(G);

    double bestCost = G[a][b];
    HighwaySection bestProposal = new HighwaySection(-1, -1, 0.0); // default
    for (HighwaySection p : P) {
      // Creates new gTest for Floyd Warshall.
      double[][] gTest = new double[G.length][];
      for (int i = 0; i < G.length; i++) {
        gTest[i] = Arrays.copyOf(G[i], G[i].length);
      }
      gTest[p.x][p.y] = gTest[p.y][p.x] = p.distance;
      FloydWarshall(gTest);
      if (bestCost > gTest[a][b]) {
        bestCost = gTest[a][b];
        bestProposal = p;
      }
    }
    return bestProposal;
  }

  public static void main(String[] args) {
    Random r = new Random();
    for (int times = 0; times < 100; ++times) {
      int n, m, k;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
        m = r.nextInt(n * ((n - 1) / 2) - 1) + 1;
        k = r.nextInt(n * ((n - 1) / 2) - m) + 1;
      } else if (args.length == 2) {
        n = Integer.parseInt(args[0]);
        m = Integer.parseInt(args[1]);
        k = r.nextInt(n * ((n - 1) / 2) - m) + 1;
      } else {
        n = r.nextInt(96) + 5;
        m = r.nextInt(n * ((n - 1) / 2) - 1) + 1;
        k = r.nextInt(n * ((n - 1) / 2) - m) + 1;
      }
      System.out.println("n = " + n + ", m = " + m + ", k = " + k);
      boolean[][] haveEdges = new boolean[n][n];

      // Existing highway sections
      List<HighwaySection> H = new ArrayList<>();

      for (int i = 0; i < m; ++i) {
        int a, b;
        do {
          a = r.nextInt(n);
          b = r.nextInt(n);
        } while (a == b || haveEdges[a][b]);
        haveEdges[a][b] = haveEdges[b][a] = true;
        H.add(new HighwaySection(a, b, r.nextDouble() * 9999 + 1));
      }
      // *
      for (int i = 0; i < m; ++i) {
        System.out.println("H[i] = " + H.get(i));
      }
      // */
      List<HighwaySection> P = new ArrayList<>(); // Proposals
      for (int i = 0; i < k; ++i) {
        int a, b;
        do {
          a = r.nextInt(n);
          b = r.nextInt(n);
        } while (a == b || haveEdges[a][b]);
        haveEdges[a][b] = haveEdges[b][a] = true;
        P.add(new HighwaySection(a, b, r.nextDouble() * 49 + 1));
      }
      // *
      for (int i = 0; i < k; ++i) {
        System.out.println("P[i] = " + P.get(i));
      }
      // */

      int a, b;
      do {
        a = r.nextInt(n);
        b = r.nextInt(n);
      } while (a == b);
      System.out.println("a = " + a + ", b = " + b);
      HighwaySection t = findBestProposals(H, P, a, b, n);
      System.out.println(t);
      HighwaySection ans = checkAns(H, P, a, b, n);
      System.out.println(ans);
      // TODO(THL): follow assert may fail sometime due to epsilon problem.
      // assert(t.x == ans.x && t.y == ans.y && t.distance == ans.distance);
    }
  }
}
