package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTreeNode;

import java.util.*;

import static com.epi.BinaryTreeUtils.generateRandBinaryTree;

public class ReconstructPreorderWithNull {
  // @include
  private static Integer idx;

  public static BinaryTreeNode<Integer> reconstructPreorder(Integer[] preorder) {
    idx = 0;
    return reconstructPreorderHelper(preorder);
  }

  private static BinaryTreeNode<Integer> reconstructPreorderHelper(
      Integer[] preorder) {
    Integer subtreeKey = preorder[idx];
    ++idx;
    if (subtreeKey == null) {
      return null;
    }
    // Note that ReconstructPreorderHelper updates idx. So the order of
    // following two calls are critical.
    BinaryTreeNode<Integer> leftSubtree = reconstructPreorderHelper(preorder);
    BinaryTreeNode<Integer> rightSubtree = reconstructPreorderHelper(preorder);
    return new BinaryTreeNode<>(subtreeKey, leftSubtree, rightSubtree);
  }
  // @exclude

  private static <T> void genPreorderWithNull(BinaryTreeNode<T> n, List<T> p) {
    if (n == null) {
      p.add(null);
      return;
    }

    p.add(n.getData());
    genPreorderWithNull(n.getLeft(), p);
    genPreorderWithNull(n.getRight(), p);
  }

  private static void simpleTest() {
    Integer[] preOrder = new Integer[] {1, null, null};
    BinaryTreeNode<Integer> result = reconstructPreorder(preOrder);
    assert(result.getData() == 1);
    assert(result.getLeft() == null);
    assert(result.getRight() == null);

    preOrder = new Integer[] {1, null, 2, null, null};
    result = reconstructPreorder(preOrder);
    assert(result.getData() == 1);
    assert(result.getLeft() == null);
    assert(result.getRight().getData() == 2);

    preOrder = new Integer[] {1, null, 2, 3, null, null, null};
    result = reconstructPreorder(preOrder);
    assert(result.getData() == 1);
    assert(result.getLeft() == null);
    assert(result.getRight().getData() == 2);
    assert(result.getRight().getLeft().getData() == 3);
    assert(result.getRight().getRight() == null);
  }

  public static void main(String[] args) {
    simpleTest();
    Random r = new Random();
    for (int times = 0; times < 1000; ++times) {
      System.out.println(times);
      int n;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = r.nextInt(10000) + 1;
      }
      BinaryTreeNode<Integer> root = generateRandBinaryTree(n, false);
      List<Integer> p = new ArrayList<>();
      genPreorderWithNull(root, p);
      Integer[] preOrder = new Integer[p.size()];
      for (int i = 0; i < preOrder.length; ++i) {
        preOrder[i] = p.get(i);
      }
      idx = 0;
      BinaryTreeNode<Integer> x = reconstructPreorder(preOrder);
      assert(root.equals(x));
    }
  }
}
