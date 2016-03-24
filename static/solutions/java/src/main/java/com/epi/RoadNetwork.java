package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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

    // clang-format off
    @Override
    public String toString() { return x + " " + y + " " + distance; }
    // clang-format on
    // @include
  }

  public static HighwaySection findBestProposals(List<HighwaySection> H,
                                                 List<HighwaySection> P,
                                                 int n) {
    // G stores the shortest path distances between all pairs of vertices.
    List<List<Double>> G = new ArrayList<>(n);
    for (int i = 0; i < n; ++i) {
      G.add(new ArrayList(Collections.nCopies(n, Double.MAX_VALUE)));
    }
    for (int i = 0; i < n; ++i) {
      G.get(i).set(i, 0.0);
    }
    // Builds an undirected graph G based on existing highway sections H.
    for (HighwaySection h : H) {
      G.get(h.x).set(h.y, h.distance);
      G.get(h.y).set(h.x, h.distance);
    }

    // Performs Floyd Warshall to build the shortest path between vertices.
    floydWarshall(G);

    // Examines each proposal for shorter distance for all pairs.
    double bestDistanceSaving = Double.MIN_VALUE;
    HighwaySection bestProposal = new HighwaySection(-1, -1, 0.0); // Default.
    for (HighwaySection p : P) {
      double proposalSaving = 0.0;
      for (int a = 0; a < n; ++a) {
        for (int b = 0; b < n; ++b) {
          double saving = G.get(a).get(b) - (G.get(a).get(p.x) + p.distance
                                             + G.get(p.y).get(b));
          proposalSaving += saving > 0.0 ? saving : 0.0;
        }
      }
      if (proposalSaving > bestDistanceSaving) {
        bestDistanceSaving = proposalSaving;
        bestProposal = p;
      }
    }
    return bestProposal;
  }

  private static void floydWarshall(List<List<Double>> G) {
    for (int k = 0; k < G.size(); ++k) {
      for (int i = 0; i < G.size(); ++i) {
        for (int j = 0; j < G.size(); ++j) {
          if (G.get(i).get(k) != Double.MAX_VALUE
              && G.get(k).get(j) != Double.MAX_VALUE
              && G.get(i).get(j) > G.get(i).get(k) + G.get(k).get(j)) {
            G.get(i).set(j, G.get(i).get(k) + G.get(k).get(j));
          }
        }
      }
    }
  }
  // @exclude

  private static void simpleTest() {
    List<HighwaySection> H = Arrays.asList(new HighwaySection(0, 1, 10),
                                           new HighwaySection(1, 2, 10),
                                           new HighwaySection(2, 3, 10));
    List<HighwaySection> P = Arrays.asList(new HighwaySection(0, 3, 1),
                                           new HighwaySection(0, 2, 2),
                                           new HighwaySection(0, 1, 3));

    HighwaySection t = findBestProposals(H, P, 4);
    assert(t.x == 0 && t.y == 3 && t.distance == 1.0);
  }

  public static void main(String[] args) {
    simpleTest();
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

      HighwaySection t = findBestProposals(H, P, n);
      System.out.println(t);
    }
  }
}
