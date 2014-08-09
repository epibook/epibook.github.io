package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

import java.util.*;
import java.util.LinkedList;

import static com.epi.BinaryTreeUtils.generatePostOrder;

public class BinaryTreePostorderTraversalIterativeAlternative {
  // @include
  public static List<Integer> postOrderTraversal(BinaryTree<Integer> root) {
    List<Integer> invPreRes = invertedPreOrderTraversal(root);
    Collections.reverse(invPreRes);
    return invPreRes;
  }

  private static List<Integer> invertedPreOrderTraversal(
      final BinaryTree<Integer> root) {
    if (root == null) {
      return Collections.emptyList();
    }

    LinkedList<BinaryTree<Integer>> s = new LinkedList<>();
    s.push(root);
    List<Integer> res = new ArrayList<>();
    while (!s.isEmpty()) {
      BinaryTree<Integer> curr = s.pop();
      res.add(curr.getData());
      if (curr.getLeft() != null) {
        s.push(curr.getLeft());
      }
      if (curr.getRight() != null) {
        s.push(curr.getRight());
      }
    }
    return res;
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
    List<Integer> res = postOrderTraversal(root);
    List<Integer> goldenRes = generatePostOrder(root);
    assert (res.size() == goldenRes.size() && Arrays.deepEquals(res.toArray(),
        goldenRes.toArray()));
  }
}
