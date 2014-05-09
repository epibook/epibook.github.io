package com.epi;

import static com.epi.BinaryTreeUtils.generateInOrder;

import java.util.List;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;
import com.epi.utils.Ref;

public class ReconstructAlmostBst {
  // @include
  public static void reconstructBST(BinaryTree<Integer> root) {
    Ref<BinaryTree<Integer>> p1 = new Ref<BinaryTree<Integer>>(null);
    Ref<BinaryTree<Integer>> p2 = new Ref<BinaryTree<Integer>>(null);
    Ref<BinaryTree<Integer>> prev = new Ref<BinaryTree<Integer>>(null);
    reconstructBSTHelper(root, p1, p2, prev);
    if (p1.value != null && p2.value != null) { // swaps the out of order nodes.
      Integer temp = p1.value.getData();
      p1.value.setData(p2.value.getData());
      p2.value.setData(temp);
    }
  }

  private static void reconstructBSTHelper(BinaryTree<Integer> root,
      Ref<BinaryTree<Integer>> p1, Ref<BinaryTree<Integer>> p2,
      Ref<BinaryTree<Integer>> prev) {
    if (root == null) {
      return;
    }

    reconstructBSTHelper(root.getLeft(), p1, p2, prev);
    // finds inversion.
    if (prev.value != null && prev.value.getData() > root.getData()) {
      p2.value = root; // assigns p2 as the current node.
      if (p1.value == null) {
        p1.value = prev.value; // assigns p1 as th first node.
      }
    }
    prev.value = root; // records the previous node as the current node.
    reconstructBSTHelper(root.getRight(), p1, p2, prev);
  }

  // @exclude

  public static void main(String[] args) {
    // 3
    // 2 4
    // 1 5 6
    BinaryTree<Integer> root = new BinaryTree<Integer>(3);
    root.setLeft(new BinaryTree<Integer>(2));
    root.getLeft().setLeft(new BinaryTree<Integer>(1));
    root.setRight(new BinaryTree<Integer>(4));
    root.getRight().setLeft(new BinaryTree<Integer>(5));
    root.getRight().setRight(new BinaryTree<Integer>(6));
    reconstructBST(root);
    List<Integer> res = generateInOrder(root);
    System.out.println(res);
    for (int i = 1; i < res.size(); i++) {
      assert (res.get(i - 1) < res.get(i));
    }
  }
}
