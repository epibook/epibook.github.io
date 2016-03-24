package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTreeNode;

import java.util.List;

import static com.epi.BinaryTreeUtils.generateInorder;

public class ReconstructAlmostBst {
  // @include
  private static BinaryTreeNode<Integer> prev;

  private static class Inversion {
    public BinaryTreeNode<Integer> prev;
    public BinaryTreeNode<Integer> curr;

    public Inversion(BinaryTreeNode<Integer> prev,
                     BinaryTreeNode<Integer> curr) {
      this.prev = prev;
      this.curr = curr;
    }
  }

  public static void reconstructBST(BinaryTreeNode<Integer> almostBST) {
    Inversion inversion0 = new Inversion(null, null);
    Inversion inversion1 = new Inversion(null, null);
    prev = null;
    reconstructBSTHelper(almostBST, inversion0, inversion1);
    if (inversion1.curr != null) { // Swaps the out of order nodes.
      Integer temp = inversion0.prev.data;
      inversion0.prev.data = inversion1.curr.data;
      inversion1.curr.data = temp;
    } else {
      Integer temp = inversion0.prev.data;
      inversion0.prev.data = inversion0.curr.data;
      inversion0.curr.data = temp;
    }
  }

  private static void reconstructBSTHelper(BinaryTreeNode<Integer> almostBST,
                                           Inversion inversion0,
                                           Inversion inversion1) {
    if (almostBST == null) {
      return;
    }

    reconstructBSTHelper(almostBST.left, inversion0, inversion1);
    if (prev != null && prev.data > almostBST.data) {
      // Inversion detected.
      if (inversion0.prev == null && inversion0.curr == null) {
        inversion0.prev = prev;
        inversion0.curr = almostBST;
      } else {
        inversion1.prev = prev;
        inversion1.curr = almostBST;
      }
    }
    prev = almostBST; // Records the previous node as the current node.
    reconstructBSTHelper(almostBST.right, inversion0, inversion1);
  }
  // @exclude

  public static void main(String[] args) {
    // 3
    // 2 4
    // 1 5 6
    BinaryTreeNode<Integer> tree = new BinaryTreeNode<>(3);
    tree.left = new BinaryTreeNode<>(2);
    tree.left.left = new BinaryTreeNode<>(1);
    tree.right = new BinaryTreeNode<>(4);
    tree.right.left = new BinaryTreeNode<>(5);
    tree.right.right = new BinaryTreeNode<>(6);
    reconstructBST(tree);
    List<Integer> result = generateInorder(tree);
    System.out.println(result);
    for (int i = 1; i < result.size(); i++) {
      assert(result.get(i - 1) < result.get(i));
    }
  }
}
