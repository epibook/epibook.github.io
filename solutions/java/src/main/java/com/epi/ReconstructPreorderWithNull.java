package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

import java.util.*;

import static com.epi.BinaryTreeUtils.generateRandBinaryTree;

public class ReconstructPreorderWithNull {
  // @include
  private static Integer idx;

  public static BinaryTree<Integer> reconstructPreorder(
      Integer[] preorder) {
    idx = 0;
    return reconstructPreorderHelper(preorder);
  }

  private static BinaryTree<Integer> reconstructPreorderHelper(
      Integer[] preorder) {
    Integer subtreeKey = preorder[idx];
    ++idx;
    if (subtreeKey == null) {
      return null;
    }
    // Note that ReconstructPreorderHelper updates idx. So the order of
    // following two calls are critical.
    BinaryTree<Integer> leftSubtree = reconstructPreorderHelper(preorder);
    BinaryTree<Integer> rightSubtree = reconstructPreorderHelper(preorder);
    return new BinaryTree<>(subtreeKey, leftSubtree, rightSubtree);
  }
  // @exclude

  private static <T> void genPreorderWithNull(BinaryTree<T> n,
                                              List<T> p) {
    if (n == null) {
      p.add(null);
      return;
    }

    p.add(n.getData());
    genPreorderWithNull(n.getLeft(), p);
    genPreorderWithNull(n.getRight(), p);
  }

  public static void main(String[] args) {
    Random r = new Random();
    for (int times = 0; times < 1000; ++times) {
      System.out.println(times);
      int n;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = r.nextInt(10000) + 1;
      }
      BinaryTree<Integer> root = generateRandBinaryTree(n, false);
      List<Integer> p = new ArrayList<>();
      genPreorderWithNull(root, p);
      Integer[] preOrder = new Integer[p.size()];
      for (int i = 0; i < preOrder.length; ++i) {
        preOrder[i] = p.get(i);
      }
      idx = 0;
      BinaryTree<Integer> x = reconstructPreorder(preOrder);
      assert (root.equals(x));
    }
  }
}
