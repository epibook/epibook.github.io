package com.epi;

import java.util.ArrayList;
import java.util.List;

public class TreeDiameter {
  // @include
  public static class TreeNode { List<Edge> edges = new ArrayList<>(); }

  private static class Edge {
    public TreeNode root;
    public Double length;

    public Edge(TreeNode root, Double length) {
      this.root = root;
      this.length = length;
    }
  }

  private static class HeightAndDiameter {
    public Double height;
    public Double diameter;

    public HeightAndDiameter(Double height, Double diameter) {
      this.height = height;
      this.diameter = diameter;
    }
  }

  public static double computeDiameter(TreeNode T) {
    return T != null ? computeHeightAndDiameter(T).diameter : 0.0;
  }

  // Returns (height, diameter) pair.
  private static HeightAndDiameter computeHeightAndDiameter(TreeNode r) {
    double diameter = Double.MIN_VALUE;
    double[] height = {0.0, 0.0}; // Stores the max two heights.
    for (Edge e : r.edges) {
      HeightAndDiameter heightDiameter = computeHeightAndDiameter(e.root);
      if (heightDiameter.height + e.length > height[0]) {
        height[1] = height[0];
        height[0] = heightDiameter.height + e.length;
      } else if (heightDiameter.height + e.length > height[1]) {
        height[1] = heightDiameter.height + e.length;
      }
      diameter = Math.max(diameter, heightDiameter.diameter);
    }
    return new HeightAndDiameter(height[0],
                                 Math.max(diameter, height[0] + height[1]));
  }
  // @exclude

  public static void main(String[] args) {
    TreeNode r = null;
    assert(0.0 == computeDiameter(r));
    r = new TreeNode();
    r.edges.add(new Edge(new TreeNode(), 10.0));
    r.edges.get(0).root.edges.add(new Edge(new TreeNode(), 50.0));
    r.edges.add(new Edge(new TreeNode(), 20.0));
    assert(80 == computeDiameter(r));
    System.out.println(computeDiameter(r));
    r.edges.get(0).root.edges.add(new Edge(new TreeNode(), 100.0));
    assert(150 == computeDiameter(r));
    System.out.println(computeDiameter(r));
  }
}
