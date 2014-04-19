package com.epi;

import static com.epi.BinaryTreeUtils.generatePreOrder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

public class BinaryTreePreorderTraversalIterative {
  // @include
  public static List<Integer> preOrderTraversal(final BinaryTree<Integer> root) {
    if (root == null) {
      return Collections.emptyList();
    }

    LinkedList<BinaryTree<Integer>> s = new LinkedList<BinaryTree<Integer>>();
    s.push(root);
    List<Integer> res = new ArrayList<Integer>();
    while (!s.isEmpty()) {
      BinaryTree<Integer> curr = s.pop();
      res.add(curr.getData());
      if (curr.getRight() != null) {
        s.push(curr.getRight());
      }
      if (curr.getLeft() != null) {
        s.push(curr.getLeft());
      }
    }
    return res;
  }

  // @exclude

  public static void main(String[] args) {
    // 3
    // 2 5
    // 1 4 6
    BinaryTree<Integer> root = new BinaryTree<Integer>(3);
    root.setLeft(new BinaryTree<Integer>(2));
    root.getLeft().setLeft(new BinaryTree<Integer>(1));
    root.setRight(new BinaryTree<Integer>(5));
    root.getRight().setLeft(new BinaryTree<Integer>(4));
    root.getRight().setRight(new BinaryTree<Integer>(6));
    List<Integer> res = preOrderTraversal(root);
    List<Integer> goldenRes = generatePreOrder(root);
    assert (res.size() == goldenRes.size() && Arrays.deepEquals(res.toArray(),
        goldenRes.toArray()));
  }
}
