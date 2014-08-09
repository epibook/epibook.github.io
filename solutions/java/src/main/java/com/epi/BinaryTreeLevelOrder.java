package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class BinaryTreeLevelOrder {
  private static List<List<Integer>> results = new ArrayList<>();

  private static List<Integer> oneLineResult = new ArrayList<>();

  // @include
  public static void printBinaryTreeDepthOrder(BinaryTree<Integer> r) {
    // Prevent empty tree.
    if (r == null) {
      return;
    }

    LinkedList<BinaryTree<Integer>> q = new LinkedList<>();
    q.push(r);
    int count = q.size();
    while (!q.isEmpty()) {
      BinaryTree<Integer> front = q.pollLast();
      System.out.print(front.getData() + " ");
      // @exclude
      oneLineResult.add(front.getData());
      // @include
      if (front.getLeft() != null) {
        q.push(front.getLeft());
      }
      if (front.getRight() != null) {
        q.push(front.getRight());
      }
      if (--count == 0) { // Finish printing nodes in the current depth.
        System.out.println();
        count = q.size();
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
