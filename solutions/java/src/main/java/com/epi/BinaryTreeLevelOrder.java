package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeLevelOrder {
  // @include
  public static List<List<Integer>> binaryTreeDepthOrder(
      BinaryTreeNode<Integer> tree) {
    Queue<BinaryTreeNode<Integer>> processingNodes = new LinkedList<>();
    processingNodes.add(tree);
    int numNodesToProcessAtCurrentLevel = processingNodes.size();
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> oneLevel = new ArrayList<>();

    while (!processingNodes.isEmpty()) {
      BinaryTreeNode<Integer> curr = processingNodes.poll();
      --numNodesToProcessAtCurrentLevel;
      if (curr != null) {
        oneLevel.add(curr.data);

        // Defer the null checks to the null test above.
        processingNodes.add(curr.left);
        processingNodes.add(curr.right);
      }
      // Are we done with the nodes at the current depth?
      if (numNodesToProcessAtCurrentLevel == 0) {
        numNodesToProcessAtCurrentLevel = processingNodes.size();
        if (!oneLevel.isEmpty()) {
          result.add(new ArrayList(oneLevel));
          oneLevel.clear();
        }
      }
    }
    return result;
  }
  // @exclude

  public static void main(String[] args) {
    //      3
    //    2   5
    //  1    4 6
    // 10
    // 13
    BinaryTreeNode<Integer> tree = new BinaryTreeNode<>(3);
    tree.left = new BinaryTreeNode<>(2);
    tree.left.left = new BinaryTreeNode<>(1);
    tree.left.left.left = new BinaryTreeNode<>(10);
    tree.left.left.left.right = new BinaryTreeNode<>(13);
    tree.right = new BinaryTreeNode<>(5);
    tree.right.left = new BinaryTreeNode<>(4);
    tree.right.right = new BinaryTreeNode<>(6);
    List<List<Integer>> result = binaryTreeDepthOrder(tree);
    List<List<Integer>> goldenRes = Arrays.asList(
        Arrays.asList(3), Arrays.asList(2, 5), Arrays.asList(1, 4, 6),
        Arrays.asList(10), Arrays.asList(13));
    assert(goldenRes.equals(result));
  }
}
