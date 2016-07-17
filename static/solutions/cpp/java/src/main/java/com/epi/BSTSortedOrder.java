package com.epi;

import com.epi.BinarySearchTreePrototypeTemplate.BSTNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class BSTSortedOrder {
  // @include
  public static List<Integer> BSTInSortedOrder(BSTNode<Integer> tree) {
    Deque<BSTNode<Integer>> s = new LinkedList<>();
    BSTNode<Integer> curr = tree;
    List<Integer> result = new ArrayList<>();

    while (!s.isEmpty() || curr != null) {
      if (curr != null) {
        s.addFirst(curr);
        // Going left.
        curr = curr.left;
      } else {
        // Going up.
        curr = s.removeFirst();
        result.add(curr.data);
        // Going right.
        curr = curr.right;
      }
    }
    return result;
  }
  // @exclude

  private static void simpleTest() {
    BSTNode<Integer> tree = new BSTNode<>(43);
    List<Integer> result = BSTInSortedOrder(tree);
    List<Integer> goldenResult = Arrays.asList(43);
    assert(result.equals(goldenResult));
    tree.left = new BSTNode<>(23);
    result = BSTInSortedOrder(tree);
    goldenResult = Arrays.asList(23, 43);
    assert(result.equals(goldenResult));
  }

  public static void main(String[] args) {
    simpleTest();
    //        43
    //    23     47
    //      37      53
    //    29  41
    //     31
    BSTNode<Integer> tree = new BSTNode<>(43);
    tree.left = new BSTNode<>(23);
    tree.left.right = new BSTNode<>(37);
    tree.left.right.left = new BSTNode<>(29);
    tree.left.right.left.right = new BSTNode<>(31);
    tree.left.right.right = new BSTNode<>(41);
    tree.right = new BSTNode<>(47);
    tree.right.right = new BSTNode<>(53);
    List<Integer> result = BSTInSortedOrder(tree);
    List<Integer> goldenResult = Arrays.asList(23, 29, 31, 37, 41, 43, 47, 53);
    assert(result.equals(goldenResult));
  }
}
