package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

import java.util.*;
import java.util.LinkedList;

import static com.epi.BinaryTreeUtils.generatePreOrder;

public class BinaryTreePreorderTraversalIterative {
  // @include
  public static List<Integer> preOrderTraversal(BinaryTree<Integer> tree) {
    LinkedList<BinaryTree<Integer>> pathStack = new LinkedList<>();
    pathStack.push(tree);
    List<Integer> result = new ArrayList<>();
    while (!pathStack.isEmpty()) {
      BinaryTree<Integer> curr = pathStack.pop();
      if (curr == null) {
        continue;
      }
      result.add(curr.getData());
      pathStack.push(curr.getRight());
      pathStack.push(curr.getLeft());
    }
    return result;
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
    List<Integer> res = preOrderTraversal(root);
    List<Integer> goldenRes = generatePreOrder(root);
    assert (res.size() == goldenRes.size()
            && Arrays.deepEquals(res.toArray(), goldenRes.toArray()));
  }
}
