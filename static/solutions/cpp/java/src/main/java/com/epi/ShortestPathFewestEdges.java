package com.epi;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

public class ShortestPathFewestEdges {
  // @include
  private static class VertexWithDistance {
    public GraphVertex vertex;
    public Integer distance;

    public VertexWithDistance(GraphVertex vertex, Integer distance) {
      this.vertex = vertex;
      this.distance = distance;
    }
  }

  private static class DistanceWithFewestEdges {
    public Integer distance;
    public Integer minNumEdges;

    public DistanceWithFewestEdges(Integer distance, Integer minNumEdges) {
      this.distance = distance;
      this.minNumEdges = minNumEdges;
    }
  }

  public static class GraphVertex implements Comparable<GraphVertex> {
    public DistanceWithFewestEdges distanceWithFewestEdges
        = new DistanceWithFewestEdges(Integer.MAX_VALUE, 0);
    public List<VertexWithDistance> edges = new ArrayList<>();
    public int id; // The id of this vertex.
    public GraphVertex pred = null; // The predecessor in the shortest path.

    @Override
    public int compareTo(GraphVertex o) {
      if (distanceWithFewestEdges.distance
          != o.distanceWithFewestEdges.distance) {
        return Integer.compare(distanceWithFewestEdges.distance,
                               o.distanceWithFewestEdges.distance);
      }
      if (distanceWithFewestEdges.minNumEdges
          != o.distanceWithFewestEdges.minNumEdges) {
        return Integer.compare(distanceWithFewestEdges.minNumEdges,
                               o.distanceWithFewestEdges.minNumEdges);
      }
      return Integer.compare(id, o.id);
    }

    @Override
    public boolean equals(Object obj) {
      if (obj == null || !(obj instanceof GraphVertex)) {
        return false;
      }
      if (this == obj) {
        return true;
      }
      GraphVertex that = (GraphVertex)obj;
      return id == that.id
          && distanceWithFewestEdges.distance.equals(
                 that.distanceWithFewestEdges.distance)
          && distanceWithFewestEdges.minNumEdges.equals(
                 that.distanceWithFewestEdges.minNumEdges);
    }

    @Override
    public int hashCode() {
      return Objects.hash(distanceWithFewestEdges.distance,
                          distanceWithFewestEdges.minNumEdges);
    }
  }

  public static void dijkstraShortestPath(GraphVertex s, GraphVertex t) {
    // Initialization of the distance of starting point.
    s.distanceWithFewestEdges = new DistanceWithFewestEdges(0, 0);
    SortedSet<GraphVertex> nodeSet = new TreeSet<>();
    nodeSet.add(s);

    while (!nodeSet.isEmpty()) {
      // Extracts the minimum distance vertex from heap.
      GraphVertex u = nodeSet.first();
      if (u.equals(t)) {
        break;
      }
      nodeSet.remove(nodeSet.first());

      // Relax neighboring vertices of u.
      for (VertexWithDistance v : u.edges) {
        int vDistance = u.distanceWithFewestEdges.distance + v.distance;
        int vNumEdges = u.distanceWithFewestEdges.minNumEdges + 1;
        if (v.vertex.distanceWithFewestEdges.distance > vDistance
            || (v.vertex.distanceWithFewestEdges.distance == vDistance
                && v.vertex.distanceWithFewestEdges.minNumEdges > vNumEdges)) {
          nodeSet.remove(v.vertex);
          v.vertex.pred = u;
          v.vertex.distanceWithFewestEdges
              = new DistanceWithFewestEdges(vDistance, vNumEdges);
          nodeSet.add(v.vertex);
        }
      }
    }

    // Outputs the shortest path with fewest edges.
    outputShortestPath(t);
  }

  private static void outputShortestPath(GraphVertex v) {
    if (v != null) {
      outputShortestPath(v.pred);
      System.out.print(v.id + " ");
    }
  }
  // @exclude

  // DBH test
  private static void test() {
    List<GraphVertex> G = new ArrayList<>();
    for (int i = 0; i < 9; ++i) {
      G.add(new GraphVertex());
      G.get(i).id = i;
    }

    // G[0] is the source node that connects to 8 other nodes.
    G.get(0).edges.add(new VertexWithDistance(G.get(1), 13)); // 0-1
    G.get(1).edges.add(new VertexWithDistance(G.get(0), 13)); // 1-0

    G.get(0).edges.add(new VertexWithDistance(G.get(2), 24)); // 0-2
    G.get(2).edges.add(new VertexWithDistance(G.get(0), 24)); // 2-0

    G.get(0).edges.add(new VertexWithDistance(G.get(3), 28)); // 0-3
    G.get(3).edges.add(new VertexWithDistance(G.get(0), 28)); // 3-0

    G.get(0).edges.add(new VertexWithDistance(G.get(4), 25)); // 0-4
    G.get(4).edges.add(new VertexWithDistance(G.get(0), 25)); // 4-0

    G.get(0).edges.add(new VertexWithDistance(G.get(5), 30)); // 0-5
    G.get(5).edges.add(new VertexWithDistance(G.get(0), 30)); // 5-0

    G.get(0).edges.add(new VertexWithDistance(G.get(6), 31)); // 0-6
    G.get(6).edges.add(new VertexWithDistance(G.get(0), 31)); // 6-0

    G.get(0).edges.add(new VertexWithDistance(G.get(7), 10)); // 0-7
    G.get(7).edges.add(new VertexWithDistance(G.get(0), 10)); // 7-0

    G.get(0).edges.add(new VertexWithDistance(G.get(8), 29)); // 0-8
    G.get(8).edges.add(new VertexWithDistance(G.get(0), 29)); // 8-0

    G.get(1).edges.add(new VertexWithDistance(G.get(8), 7)); // 1-8
    G.get(8).edges.add(new VertexWithDistance(G.get(1), 7)); // 8-1

    G.get(2).edges.add(new VertexWithDistance(G.get(8), 1)); // 2-8
    G.get(8).edges.add(new VertexWithDistance(G.get(2), 1)); // 8-2

    G.get(7).edges.add(new VertexWithDistance(G.get(8), 16)); // 7-8
    G.get(8).edges.add(new VertexWithDistance(G.get(7), 16)); // 8-7

    int s = 0; // Source is G[0].
    int t = 2; // Destination is G[2].

    // Minimum distance path should be:
    // G[0] => G[1] => G[8] => G[2],
    // distance is: 13 + 7 + 1 = 21.

    dijkstraShortestPath(G.get(s), G.get(t));
    System.out.println("\nMin distance: "
                       + G.get(t).distanceWithFewestEdges.distance);
    assert(G.get(t).distanceWithFewestEdges.distance == 21);
    System.out.println("Number of edges: "
                       + G.get(t).distanceWithFewestEdges.minNumEdges);
    System.out.println("Number of edges: "
                       + G.get(t).distanceWithFewestEdges.minNumEdges);
    assert(G.get(t).distanceWithFewestEdges.minNumEdges == 3);
  }

  public static void main(String[] args) {
    Random r = new Random();
    int n;
    if (args.length == 1) {
      n = Integer.parseInt(args[0]);
    } else {
      n = r.nextInt(999) + 2;
    }
    List<GraphVertex> G = new ArrayList<>();
    for (int i = 0; i < n; ++i) {
      G.add(new GraphVertex());
    }
    int m = r.nextInt(n * (n - 1) / 2) + 1;
    boolean[][] isEdgeExist = new boolean[n][n];
    // Make the graph as connected.
    for (int i = 1; i < n; ++i) {
      int len = r.nextInt(100) + 1;
      G.get(i - 1).edges.add(new VertexWithDistance(G.get(i), len));
      G.get(i).edges.add(new VertexWithDistance(G.get(i - 1), len));
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
      int len = r.nextInt(100) + 1;
      G.get(a).edges.add(new VertexWithDistance(G.get(b), len));
      G.get(b).edges.add(new VertexWithDistance(G.get(a), len));
    }
    int s = r.nextInt(n);
    int t = r.nextInt(n);
    for (int i = 0; i < G.size(); ++i) {
      G.get(i).id = i;
      for (VertexWithDistance e : G.get(i).edges) {
        System.out.print(e.vertex.id + "-" + G.get(0).id + " " + e.distance
                         + ",");
      }
      System.out.println();
    }
    System.out.println("source = " + s + ", terminal = " + t);
    dijkstraShortestPath(G.get(s), G.get(t));
    System.out.println("\n" + G.get(t));
    test();
  }
}
