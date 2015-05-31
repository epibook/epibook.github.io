package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTreeNode;

import java.util.*;
import java.util.Stack;

import static com.epi.BinaryTreeUtils.generatePostOrder;

public class BinaryTreePostorderTraversalIterative {
  // @include
  // We use stack and previous node pointer to simulate postorder traversal.
  public static List<Integer> postOrderTraversal(BinaryTreeNode<Integer> tree) {
    if (tree == null) { // Empty tree.
      return Collections.emptyList();
    }

    Stack<BinaryTreeNode<Integer>> pathStack = new Stack<>();
    BinaryTreeNode<Integer> prev = null;
    pathStack.push(tree);
    List<Integer> postorderSequence = new ArrayList<>();
    while (!pathStack.isEmpty()) {
      BinaryTreeNode<Integer> curr = pathStack.peek();
      if (prev == null || prev.getLeft() == curr || prev.getRight() == curr) {
        // We came down to curr from prev.
        if (curr.getLeft() != null) { // Traverse left.
          pathStack.push(curr.getLeft());
        } else if (curr.getRight() != null) { // Traverse right.
          pathStack.push(curr.getRight());
        } else { // Leaf node, so visit current node.
          postorderSequence.add(curr.getData());
          pathStack.pop();
        }
      } else if (curr.getLeft() == prev) {
        // Done with left, so now traverse right.
        if (curr.getRight() != null) { // Visit right.
          pathStack.push(curr.getRight());
        } else { // // No right child, so visit curr.
          postorderSequence.add(curr.getData());
          pathStack.pop();
        }
      } else {
        // Finished traversing left and right, so visit curr.
        postorderSequence.add(curr.getData());
        pathStack.pop();
      }
      prev = curr;
    }
    return postorderSequence;
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
    List<Integer> res = postOrderTraversal(tree);
    List<Integer> goldenRes = generatePostOrder(tree);
    assert(res.size() == goldenRes.size() &&
           Arrays.deepEquals(res.toArray(), goldenRes.toArray()));
  }
}
