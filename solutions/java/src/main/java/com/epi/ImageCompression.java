package com.epi;

import java.util.*;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class ImageCompression {
  // @include
  public static class Point {
    public int i, j;

    public Point(int i, int j) {
      this.i = i;
      this.j = j;
    }

    public boolean isGreater(Point that) { return i > that.i || j > that.j; }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      Point point = (Point)o;

      if (i != point.i) {
        return false;
      }
      if (j != point.j) {
        return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      int result = i;
      result = 31 * result + j;
      return result;
    }

    public String toString() { return "(" + i + " " + j + ")"; }
  }

  public static class TreeNode {
    public int nodeNum; // stores the number of node in its subtree.

    public Point lowerLeft, upperRight;

    // Store the SW, NW, NE, and SE rectangles if color is mixed.
    public List<TreeNode> children = new ArrayList<>();

    public TreeNode(int nodeNum, Point lowerLeft, Point upperRight) {
      this.nodeNum = nodeNum;
      this.lowerLeft = lowerLeft;
      this.upperRight = upperRight;
    }
  }

  public static TreeNode calculateOptimal2DTree(int[][] image) {
    int[][] imageSum = new int[image.length][image[0].length];
    for (int i = 0; i < image.length; ++i) {
      int summ = 0;
      for (int j = 0; j < image[i].length; ++j) {
        summ += image[i][j];
        imageSum[i][j] = summ;
      }
      for (int j = 0; i > 0 && j < image[i].length; ++j) {
        imageSum[i][j] += imageSum[i - 1][j];
      }
    }

    Map<Point, Map<Point, TreeNode>> table = new HashMap<>();
    return calculateOptimal2DTreeHelper(
        image, imageSum, new Point(0, 0),
        new Point(image.length - 1, image[0].length - 1), table);
  }

  public static boolean isMonochromatic(int[][] imageSum, Point lowerLeft,
                                        Point upperRight) {
    int pixelSum = imageSum[upperRight.i][upperRight.j];
    if (lowerLeft.i >= 1) {
      pixelSum -= imageSum[lowerLeft.i - 1][upperRight.j];
    }
    if (lowerLeft.j >= 1) {
      pixelSum -= imageSum[upperRight.i][lowerLeft.j - 1];
    }
    if (lowerLeft.i >= 1 && lowerLeft.j >= 1) {
      pixelSum += imageSum[lowerLeft.i - 1][lowerLeft.j - 1];
    }
    return pixelSum == 0 || // totally white.
        pixelSum ==
            (upperRight.i - lowerLeft.i + 1) * // totally black.
                (upperRight.j - lowerLeft.j + 1);
  }

  public static TreeNode calculateOptimal2DTreeHelper(
      int[][] image, int[][] imageSum, Point lowerLeft, Point upperRight,
      Map<Point, Map<Point, TreeNode>> table) {
    // Illegal rectangle region, returns empty node.
    if (lowerLeft.isGreater(upperRight)) {
      return new TreeNode(0, lowerLeft, upperRight);
    }
    if (!table.containsKey(lowerLeft)) {
      table.put(lowerLeft, new HashMap<Point, TreeNode>());
    }
    if (!table.get(lowerLeft).containsKey(upperRight)) {
      if (isMonochromatic(imageSum, lowerLeft, upperRight)) {
        table.get(lowerLeft)
            .put(upperRight, new TreeNode(1, lowerLeft, upperRight));
      } else {
        TreeNode p = new TreeNode(Integer.MAX_VALUE, lowerLeft, upperRight);
        for (int s = lowerLeft.i; s <= upperRight.i + 1; ++s) {
          for (int t = lowerLeft.j; t <= upperRight.j + 1; ++t) {
            if ((s != lowerLeft.i && s != upperRight.i + 1) ||
                (t != lowerLeft.j && t != upperRight.j + 1)) {
              List<TreeNode> children = new ArrayList<>();
              // SW rectangle.
              children.add(calculateOptimal2DTreeHelper(
                  image, imageSum, lowerLeft, new Point(s - 1, t - 1), table));
              // NW rectangle.
              children.add(calculateOptimal2DTreeHelper(
                  image, imageSum, new Point(lowerLeft.i, t),
                  new Point(s - 1, upperRight.j), table));
              // NE rectangle.
              children.add(calculateOptimal2DTreeHelper(
                  image, imageSum, new Point(s, t), upperRight, table));
              // SE rectangle.
              children.add(calculateOptimal2DTreeHelper(
                  image, imageSum, new Point(s, lowerLeft.j),
                  new Point(upperRight.i, t - 1), table));

              int nodeNum = 1; // itself.
              List<TreeNode> toRemove = new ArrayList<>();
              for (TreeNode child : children) {
                nodeNum += child.nodeNum;
                // Remove the child contains no node.
                if (child.nodeNum == 0) {
                  toRemove.add(child);
                }
              }
              children.removeAll(toRemove);
              if (nodeNum < p.nodeNum) {
                p.nodeNum = nodeNum;
                p.children = children;
              }
            }
          }
        }
        table.get(lowerLeft).put(upperRight, p);
      }
    }
    return table.get(lowerLeft).get(upperRight);
  }
  // @exclude

  private static void recursivePrintTree(TreeNode r) {
    System.out.println(r.lowerLeft + "-" + r.upperRight);
    for (TreeNode ptr : r.children) {
      if (ptr != null) {
        recursivePrintTree(ptr);
      }
    }
  }

  public static void main(String[] args) {
    Random r = new Random();
    int m, n;
    if (args.length == 2) {
      m = Integer.parseInt(args[0]);
      n = Integer.parseInt(args[1]);
    } else {
      m = r.nextInt(20) + 1;
      n = r.nextInt(20) + 1;
    }
    int[][] image = new int[m][n];
    for (int i = 0; i < m; ++i) {
      for (int j = 0; j < n; ++j) {
        image[i][j] = r.nextInt(2);
      }
    }
    System.out.println(m + " " + n);
    for (int i = 0; i < m; ++i) {
      for (int j = 0; j < n; ++j) {
        System.out.print(image[i][j] + " ");
      }
      System.out.println();
    }
    System.out.println();
    TreeNode res = calculateOptimal2DTree(image);
    recursivePrintTree(res);
  }
}
