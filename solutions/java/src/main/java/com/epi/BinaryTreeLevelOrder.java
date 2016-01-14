package com.epi;

/*
    @slug
    binary-tree-level-order

    @title
    Binary Tree in level order

    @problem
    Given a binary tree, return an array consisting of the keys at the same
level. Keys should appear in the order of the corresponding nodes' depths,
breaking ties from left to right.

    For example, you should return << 314>, <6, 6>, <271, 561, 2, 271>, <28, 0,
3, 1, 28>, <17, 401, 257>, <641>> for the binary tree in the figure.
    <p>

    <img src="/binary-tree.png"></img>

  The binary tree class is

<pre>
   class BinaryTreeNode<T> {
       public T data;
       public BinaryTreeNode<T> left, right;
   }

   public BinaryTreeNode(T data) { this.data = data; }

   public BinaryTreeNode(T data, BinaryTreeNode<T> left,
                          BinaryTreeNode<T> right) {
     this.data = data;
     this.left = left;
     this.right = right;
   }
</pre>
<p>

    @hint
    Start by solving this problem with a pair of queues.

*/

import com.epi.BinaryTreePrototypeTemplate.BinaryTreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeLevelOrder {
  // @include
  // @judge-include-display
  public static List<List<Integer>> binaryTreeDepthOrder(
      BinaryTreeNode<Integer> tree) {
    // @judge-exclude-display
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
    // @judge-include-display
  }
  // @judge-exclude-display
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
    if (!goldenRes.equals(result)) {
      System.err.println("Failed on input " + tree);
      System.err.println("Expected " + goldenRes);
      System.err.println("Your code produced " + result);
      System.exit(-1);
    } else {
      System.out.println("You passed all tests.");
    }
  }
}
