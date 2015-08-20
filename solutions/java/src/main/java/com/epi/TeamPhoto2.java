package com.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class TeamPhoto2 {
  private static class Player<HeightType extends Comparable<HeightType>>
      implements Comparable<Player<HeightType>> {
    public HeightType height;

    public Player(HeightType height) { this.height = height; }

    @Override
    public int compareTo(Player<HeightType> o) {
      return height.compareTo(o.height);
    }
  }

  private static class Team<HeightType extends Comparable<HeightType>> {
    private ArrayList<Player<HeightType>> members;

    public Team(List<HeightType> height) {
      members = new ArrayList<>();
      for (HeightType h : height) {
        members.add(new Player<>(h));
      }
    }

    public boolean lessThen(Team<HeightType> that) {
      List<Player<HeightType>> thisSorted = sortHeightMembers();
      List<Player<HeightType>> thatSorted = that.sortHeightMembers();
      for (int i = 0; i < thisSorted.size() && i < thatSorted.size(); ++i) {
        if (!(thisSorted.get(i).compareTo(thatSorted.get(i)) < 0)) {
          return false;
        }
      }
      return true;
    }

    private List<Player<HeightType>> sortHeightMembers() {
      List<Player<HeightType>> sortedMembers =
          (List<Player<HeightType>>)members.clone();
      Collections.sort(sortedMembers);
      return sortedMembers;
    }
  }

  // @include
  public static class GraphVertex {
    public List<GraphVertex> edges = new ArrayList<>();
    public int maxDistance = 1;
    public boolean visited = false;
  }

  public static int findLargestNumberTeams(List<GraphVertex> G) {
    LinkedList<GraphVertex> vertexOrder = buildTopologicalOrdering(G);
    return findLongestPath(vertexOrder);
  }

  private static LinkedList<GraphVertex> buildTopologicalOrdering(
      List<GraphVertex> G) {
    LinkedList<GraphVertex> vertexOrder = new LinkedList<>();
    for (GraphVertex g : G) {
      if (!g.visited) {
        DFS(g, vertexOrder);
      }
    }
    return vertexOrder;
  }

  private static int findLongestPath(LinkedList<GraphVertex> vertexOrder) {
    int maxDistance = 0;
    while (!vertexOrder.isEmpty()) {
      GraphVertex u = vertexOrder.peek();
      maxDistance = Math.max(maxDistance, u.maxDistance);
      for (GraphVertex v : u.edges) {
        v.maxDistance = Math.max(v.maxDistance, u.maxDistance + 1);
      }
      vertexOrder.pop();
    }
    return maxDistance;
  }

  private static void DFS(GraphVertex cur, LinkedList<GraphVertex> vertexOrder) {
    cur.visited = true;
    for (GraphVertex next : cur.edges) {
      if (!next.visited) {
        DFS(next, vertexOrder);
      }
    }
    vertexOrder.push(cur);
  }
  // @exclude

  public static void main(String[] args) {
    List<GraphVertex> G = new ArrayList<>(3);
    for (int i = 0; i < 3; i++) {
      G.add(new GraphVertex());
    }
    G.get(0).edges.add(G.get(2));
    G.get(1).edges.add(G.get(2));
    assert(2 == findLargestNumberTeams(G));
  }
}
