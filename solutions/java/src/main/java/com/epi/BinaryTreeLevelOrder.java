package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BinaryTreeLevelOrder {
  private static List<List<Integer>> results = new ArrayList<>();

  private static List<Integer> oneLineResult = new ArrayList<>();

  // @include
  public static void printBinaryTreeDepthOrder(BinaryTree<Integer> root) {
    LinkedList<BinaryTree<Integer>> processingNodes = new LinkedList<>();
    processingNodes.push(root);
    int numNodesCurrentLevel = processingNodes.size();
    while (!processingNodes.isEmpty()) {
      BinaryTree<Integer> curr = processingNodes.pollLast();
      --numNodesCurrentLevel;
      if (curr == null) {
        continue;
      }
      System.out.print(curr.getData() + " ");
      // @exclude
      oneLineResult.add(curr.getData());
      // @include

      // Defer the null checks to the null test above.
      processingNodes.push(curr.getLeft());
      processingNodes.push(curr.getRight());
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
    // 3
    // 2 5
    // 1 4 6
    BinaryTree<Integer> root = new BinaryTree<>(3);
    root.setLeft(new BinaryTree<>(2));
    root.getLeft().setLeft(new BinaryTree<>(1));
    root.setRight(new BinaryTree<>(5));
    root.getRight().setLeft(new BinaryTree<>(4));
    root.getRight().setRight(new BinaryTree<>(6));
    // should output 3
    //               2 5
    //               1 4 6
    printBinaryTreeDepthOrder(root);
    List<List<Integer>> goldenRes = new ArrayList<>();
    goldenRes.add(Arrays.asList(3));
    goldenRes.add(Arrays.asList(2, 5));
    goldenRes.add(Arrays.asList(1, 4, 6));
    assert (goldenRes.equals(results));
  }
}
