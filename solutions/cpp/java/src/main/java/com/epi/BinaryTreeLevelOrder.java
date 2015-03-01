package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BinaryTreeLevelOrder {
  private static List<List<Integer>> results = new ArrayList<>();

  private static List<Integer> oneLineResult = new ArrayList<>();

  // @include
  public static void printBinaryTreeDepthOrder(BinaryTreeNode<Integer> tree) {
    LinkedList<BinaryTreeNode<Integer>> processingNodes = new LinkedList<>();
    processingNodes.push(tree);
    int numNodesCurrentLevel = processingNodes.size();
    while (!processingNodes.isEmpty()) {
      BinaryTreeNode<Integer> curr = processingNodes.pollLast();
      --numNodesCurrentLevel;
      if (curr != null) {
        System.out.print(curr.getData() + " ");
        // @exclude
        oneLineResult.add(curr.getData());
        // @include

        // Defer the null checks to the null test above.
        processingNodes.push(curr.getLeft());
        processingNodes.push(curr.getRight());
      }
      // Done with the nodes at the current depth.
      if (numNodesCurrentLevel == 0) {
        System.out.println();
        numNodesCurrentLevel = processingNodes.size();
        // @exclude
        results.add(new ArrayList(oneLineResult));
        oneLineResult.clear();
        // @include
      }
    }
  }
  // @exclude

  public static void main(String[] args) {
    //    3
    //   2 5
    //  1 4 6
    //10
    // 13
    BinaryTreeNode<Integer> tree = new BinaryTreeNode<>(3);
    tree.setLeft(new BinaryTreeNode<>(2));
    tree.getLeft().setLeft(new BinaryTreeNode<>(1));
    tree.getLeft().getLeft().setLeft(new BinaryTreeNode<>(10));
    tree.getLeft().getLeft().getLeft().setRight(new BinaryTreeNode<>(13));
    tree.setRight(new BinaryTreeNode<>(5));
    tree.getRight().setLeft(new BinaryTreeNode<>(4));
    tree.getRight().setRight(new BinaryTreeNode<>(6));
    // should output 3
    //               2 5
    //               1 4 6
    //               10
    //               13
    printBinaryTreeDepthOrder(tree);
    List<List<Integer>> goldenRes = new ArrayList<>();
    goldenRes.add(Arrays.asList(3));
    goldenRes.add(Arrays.asList(2, 5));
    goldenRes.add(Arrays.asList(1, 4, 6));
    goldenRes.add(Arrays.asList(10));
    goldenRes.add(Arrays.asList(13));
    goldenRes.add(new ArrayList());
    assert (goldenRes.equals(results));
  }
}
