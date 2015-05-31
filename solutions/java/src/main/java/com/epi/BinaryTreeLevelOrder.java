package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BinaryTreeLevelOrder {
  // @include
  public static List<List<Integer>> BinaryTreeDepthOrder(
      BinaryTreeNode<Integer> tree) {
    LinkedList<BinaryTreeNode<Integer>> processingNodes = new LinkedList<>();
    processingNodes.push(tree);
    int numNodesCurrentLevel = processingNodes.size();
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> oneLevel = new ArrayList<>();

    while (!processingNodes.isEmpty()) {
      BinaryTreeNode<Integer> curr = processingNodes.pollLast();
      --numNodesCurrentLevel;
      if (curr != null) {
        oneLevel.add(curr.getData());

        // Defer the null checks to the null test above.
        processingNodes.push(curr.getLeft());
        processingNodes.push(curr.getRight());
      }
      // Done with the nodes at the current depth.
      if (numNodesCurrentLevel == 0) {
        numNodesCurrentLevel = processingNodes.size();
        result.add(new ArrayList(oneLevel));
        oneLevel.clear();
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
    tree.setLeft(new BinaryTreeNode<>(2));
    tree.getLeft().setLeft(new BinaryTreeNode<>(1));
    tree.getLeft().getLeft().setLeft(new BinaryTreeNode<>(10));
    tree.getLeft().getLeft().getLeft().setRight(new BinaryTreeNode<>(13));
    tree.setRight(new BinaryTreeNode<>(5));
    tree.getRight().setLeft(new BinaryTreeNode<>(4));
    tree.getRight().setRight(new BinaryTreeNode<>(6));
    List<List<Integer>> result = BinaryTreeDepthOrder(tree);
    List<List<Integer>> goldenRes = new ArrayList<>();
    goldenRes.add(Arrays.asList(3));
    goldenRes.add(Arrays.asList(2, 5));
    goldenRes.add(Arrays.asList(1, 4, 6));
    goldenRes.add(Arrays.asList(10));
    goldenRes.add(Arrays.asList(13));
    goldenRes.add(new ArrayList());
    assert(goldenRes.equals(result));
  }
}
