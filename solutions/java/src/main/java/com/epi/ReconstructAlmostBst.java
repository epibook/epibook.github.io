package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

import java.util.List;

import static com.epi.BinaryTreeUtils.generateInOrder;

public class ReconstructAlmostBst {
  // @include
  private static BinaryTree<Integer> p1, p2;
  private static BinaryTree<Integer> pre;

  public static void reconstructBST(BinaryTree<Integer> root) {
    p1 = null;
    p2 = null;
    pre = null;
    reconstructBSTHelper(root);
    if (p1 != null && p2 != null) { // Swaps the out of order nodes.
      Integer temp = p1.getData();
      p1.setData(p2.getData());
      p2.setData(temp);
    }
  }

  private static void reconstructBSTHelper(BinaryTree<Integer> root) {
    if (root == null) {
      return;
    }

    reconstructBSTHelper(root.getLeft());
    // Finds inversion.
    if (pre != null && pre.getData() > root.getData()) {
      p2 = root; // Assigns p2 as the current node.
      if (p1 == null) {
        p1 = pre; // Assigns p1 as th first node.
      }
    }
    pre = root; // Records the previous node as the current node.
    reconstructBSTHelper(root.getRight());
  }
  // @exclude

  public static void main(String[] args) {
    // 3
    // 2 4
    // 1 5 6
    BinaryTree<Integer> root = new BinaryTree<>(3);
    root.setLeft(new BinaryTree<>(2));
    root.getLeft().setLeft(new BinaryTree<>(1));
    root.setRight(new BinaryTree<>(4));
    root.getRight().setLeft(new BinaryTree<>(5));
    root.getRight().setRight(new BinaryTree<>(6));
    reconstructBST(root);
    List<Integer> result = generateInOrder(root);
    System.out.println(result);
    for (int i = 1; i < result.size(); i++) {
      assert (result.get(i - 1) < result.get(i));
    }
  }
}
