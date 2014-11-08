package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

import java.util.*;
import java.util.LinkedList;

import static com.epi.BinaryTreeUtils.generatePostOrder;

public class BinaryTreePostorderTraversalIterative {
  // @include
  // We use stack and previous node pointer to simulate postorder traversal.
  public static List<Integer> postOrderTraversal(BinaryTree<Integer> tree) {
    if (tree == null) { // Empty tree.
      return Collections.emptyList();
    }

    LinkedList<BinaryTree<Integer>> pathStack = new LinkedList<>();
    BinaryTree<Integer> prev = null;
    pathStack.push(tree);
    List<Integer> postorderSequence = new ArrayList<>();
    while (!pathStack.isEmpty()) {
      BinaryTree<Integer> curr = pathStack.peek();
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
