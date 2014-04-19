package com.epi;

import static com.epi.BinaryTreeUtils.generatePostOrder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

public class BinaryTreePostorderTraversalIterative {
  // @include
  // We use stack and previous node pointer to simulate postorder traversal.
  public static List<Integer> postOrderTraversal(final BinaryTree<Integer> root) {
    if (root == null) { // empty tree.
      return Collections.emptyList();
    }

    LinkedList<BinaryTree<Integer>> s = new LinkedList<BinaryTree<Integer>>();
    BinaryTree<Integer> prev = null;
    s.push(root);
    List<Integer> res = new ArrayList<Integer>();
    while (!s.isEmpty()) {
      BinaryTree<Integer> curr = s.peek();
      if (prev == null || prev.getLeft() == curr || prev.getRight() == curr) {
        // Going down.
        if (curr.getLeft() != null) { // visit left.
          s.push(curr.getLeft());
        } else if (curr.getRight() != null) { // visit right.
          s.push(curr.getRight());
        } else { // leaf node, then process current node.
          res.add(curr.getData());
          s.pop();
        }
      } else if (curr.getLeft() == prev) {
        // Going up, finished visiting left.
        if (curr.getRight() != null) { // visit right.
          s.push(curr.getRight());
        } else { // no right child, then process current node.
          res.add(curr.getData());
          s.pop();
        }
      } else { // curr->right.get() == prev.
        // Going up, finished visiting left and right.
        res.add(curr.getData());
        s.pop();
      }
      prev = curr;
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
    List<Integer> res = postOrderTraversal(root);
    List<Integer> goldenRes = generatePostOrder(root);
    assert (res.size() == goldenRes.size() && Arrays.deepEquals(res.toArray(),
        goldenRes.toArray()));
  }
}
