package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTreeNode;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.epi.BinaryTreeUtils.generateRandBinaryTree;

public class ReconstructPreorderWithNull {
  // @include
  // Global variable, tracks current subtree.
  private static Integer subtreeIdx;

  public static BinaryTreeNode<Integer> reconstructPreorder(
      List<Integer> preorder) {
    subtreeIdx = 0;
    return reconstructPreorderSubtree(preorder);
  }

  // Reconstructs the subtree that is rooted at subtreeIdx.
  private static BinaryTreeNode<Integer> reconstructPreorderSubtree(
      List<Integer> preorder) {
    Integer subtreeKey = preorder.get(subtreeIdx);
    ++subtreeIdx;
    if (subtreeKey == null) {
      return null;
    }
    // Note that reconstructPreorderSubtree updates subtreeIdx. So the order of
    // following two calls are critical.
    BinaryTreeNode<Integer> leftSubtree = reconstructPreorderSubtree(preorder);
    BinaryTreeNode<Integer> rightSubtree = reconstructPreorderSubtree(preorder);
    return new BinaryTreeNode<>(subtreeKey, leftSubtree, rightSubtree);
  }
  // @exclude

  private static <T> void genPreorderWithNull(BinaryTreeNode<T> n, List<T> p) {
    if (n == null) {
      p.add(null);
      return;
    }

    p.add(n.data);
    genPreorderWithNull(n.left, p);
    genPreorderWithNull(n.right, p);
  }

  private static void simpleTest() {
    List<Integer> preorder = Arrays.asList(1, null, null);
    BinaryTreeNode<Integer> result = reconstructPreorder(preorder);
    assert(result.data == 1);
    assert(result.left == null);
    assert(result.right == null);

    preorder = Arrays.asList(1, null, 2, null, null);
    result = reconstructPreorder(preorder);
    assert(result.data == 1);
    assert(result.left == null);
    assert(result.right.data == 2);

    preorder = Arrays.asList(1, null, 2, 3, null, null, null);
    result = reconstructPreorder(preorder);
    assert(result.data == 1);
    assert(result.left == null);
    assert(result.right.data == 2);
    assert(result.right.left.data == 3);
    assert(result.right.right == null);
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
      List<Integer> preorder = new ArrayList<>(p.size());
      for (int i = 0; i < p.size(); ++i) {
        preorder.add(p.get(i));
      }
      BinaryTreeNode<Integer> x = reconstructPreorder(preorder);
      assert(root.equals(x));
    }
  }
}
