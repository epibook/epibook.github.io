package com.epi;

import com.epi.BinaryTreeWithParentPrototype.BinaryTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InorderTraversalWithParent {
  // @include
  public static List<Integer> inOrderTraversal(BinaryTree<Integer> tree) {
    BinaryTree<Integer> prev = null, curr = tree;
    List<Integer> result = new ArrayList<>();

    while (curr != null) {
      BinaryTree<Integer> next;
      if (curr.getParent() == prev) {
        // We came down to curr from prev.
        if (curr.getLeft() != null) { // Keep going left.
          next = curr.getLeft();
        } else {
          result.add(curr.getData());
          // Done with left, so go right if right is not empty.
          // Otherwise, go up.
          next = (curr.getRight() != null) ? curr.getRight() : curr.getParent();
        }
      } else if (curr.getLeft() == prev) {
        result.add(curr.getData());
        // Done with left, so go right if right is not empty. Otherwise, go up.
        next = (curr.getRight() != null) ? curr.getRight() : curr.getParent();
      } else { // Done with both children, so move up.
        next = curr.getParent();
      }

      prev = curr;
      curr = next;
    }
    return result;
  }
  // @exclude

  public static void main(String[] args) {
    //      3
    //    2   5
    //  1    4 6
    BinaryTree<Integer> root = new BinaryTree<>(3, null, null);
    root.setLeft(new BinaryTree<>(2, null, null));
    root.getLeft().setParent(root);
    root.getLeft().setLeft(new BinaryTree<>(1, null, null));
    root.getLeft().getLeft().setParent(root.getLeft());
    root.setRight(new BinaryTree<>(5, null, null));
    root.getRight().setParent(root);
    root.getRight().setLeft(new BinaryTree<>(4, null, null));
    root.getRight().getLeft().setParent(root.getRight());
    root.getRight().setRight(new BinaryTree<>(6, null, null));
    root.getRight().getRight().setParent(root.getRight());

    List<Integer> result = inOrderTraversal(root);
    List<Integer> goldenRes = Arrays.asList(1, 2, 3, 4, 5, 6);
    assert(goldenRes.equals(result));
  }
}
