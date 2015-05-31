package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTreeNode;

import java.util.*;
import java.util.Stack;

import static com.epi.BinaryTreeUtils.generatePreOrder;

public class BinaryTreePreorderTraversalIterative {
  // @include
  public static List<Integer> preOrderTraversal(BinaryTreeNode<Integer> tree) {
    Stack<BinaryTreeNode<Integer>> pathStack = new Stack<>();
    pathStack.push(tree);
    List<Integer> result = new ArrayList<>();
    while (!pathStack.isEmpty()) {
      BinaryTreeNode<Integer> curr = pathStack.pop();
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
    BinaryTreeNode<Integer> tree = new BinaryTreeNode<>(3);
    tree.setLeft(new BinaryTreeNode<>(2));
    tree.getLeft().setLeft(new BinaryTreeNode<>(1));
    tree.setRight(new BinaryTreeNode<>(5));
    tree.getRight().setLeft(new BinaryTreeNode<>(4));
    tree.getRight().setRight(new BinaryTreeNode<>(6));
    List<Integer> res = preOrderTraversal(tree);
    List<Integer> goldenRes = generatePreOrder(tree);
    assert(res.size() == goldenRes.size() &&
           Arrays.deepEquals(res.toArray(), goldenRes.toArray()));
  }
}
