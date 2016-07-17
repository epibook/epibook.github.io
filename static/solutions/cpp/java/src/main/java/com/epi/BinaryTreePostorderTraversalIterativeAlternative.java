package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static com.epi.BinaryTreeUtils.generatePostorder;

public class BinaryTreePostorderTraversalIterativeAlternative {
  // @include
  public static List<Integer> postorderTraversal(BinaryTreeNode<Integer> tree) {
    List<Integer> sequence = invertedPreorderTraversal(tree);
    Collections.reverse(sequence);
    return sequence;
  }

  private static List<Integer> invertedPreorderTraversal(
      BinaryTreeNode<Integer> tree) {
    Deque<BinaryTreeNode<Integer>> path = new LinkedList<>();
    path.addFirst(tree);
    List<Integer> result = new ArrayList<>();
    while (!path.isEmpty()) {
      BinaryTreeNode<Integer> curr = path.removeFirst();
      if (curr == null) {
        continue;
      }
      result.add(curr.data);
      path.addFirst(curr.left);
      path.addFirst(curr.right);
    }
    return result;
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
