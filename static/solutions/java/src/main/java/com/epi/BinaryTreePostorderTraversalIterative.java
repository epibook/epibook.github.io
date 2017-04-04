package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.LinkedList;

import static com.epi.BinaryTreeUtils.generatePostorder;

public class BinaryTreePostorderTraversalIterative {
  // @include
  // We use stack and previous node pointer to simulate postorder traversal.
  public static List<Integer> postorderTraversal(BinaryTreeNode<Integer> tree) {
    if (tree == null) { // Empty tree.
      return Collections.emptyList();
    }

    Deque<BinaryTreeNode<Integer>> path = new LinkedList<>();
    BinaryTreeNode<Integer> prev = null;
    path.addFirst(tree);
    List<Integer> postorderSequence = new ArrayList<>();
    while (!path.isEmpty()) {
      BinaryTreeNode<Integer> curr = path.peekFirst();
      if (prev == null || prev.left == curr || prev.right == curr) {
        // We came down to curr from prev.
        if (curr.left != null) { // Traverse left.
          path.addFirst(curr.left);
        } else if (curr.right != null) { // Traverse right.
          path.addFirst(curr.right);
        } else { // Leaf node, so visit current node.
          postorderSequence.add(curr.data);
          path.removeFirst();
        }
      } else if (curr.left == prev) {
        // Done with left, so now traverse right.
        if (curr.right != null) { // Visit right.
          path.addFirst(curr.right);
        } else { // // No right child, so visit curr.
          postorderSequence.add(curr.data);
          path.removeFirst();
        }
      } else {
        // Finished traversing left and right, so visit curr.
        postorderSequence.add(curr.data);
        path.removeFirst();
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
    tree.left = new BinaryTreeNode<>(2);
    tree.left.left = new BinaryTreeNode<>(1);
    tree.right = new BinaryTreeNode<>(5);
    tree.right.left = new BinaryTreeNode<>(4);
    tree.right.right = new BinaryTreeNode<>(6);
    List<Integer> res = postorderTraversal(tree);
    List<Integer> goldenRes = generatePostorder(tree);
    assert(res.size() == goldenRes.size()
           && Arrays.deepEquals(res.toArray(), goldenRes.toArray()));
  }
}
