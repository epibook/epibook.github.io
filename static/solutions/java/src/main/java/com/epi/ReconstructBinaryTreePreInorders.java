package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTreeNode;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static com.epi.BinaryTreeUtils.generateInorder;
import static com.epi.BinaryTreeUtils.generatePreorder;
import static com.epi.BinaryTreeUtils.generateRandBinaryTree;

public class ReconstructBinaryTreePreInorders {
  // @include
  public static BinaryTreeNode<Integer> binaryTreeFromPreorderInorder(
      List<Integer> preorder, List<Integer> inorder) {
    Map<Integer, Integer> nodeToInorderIdx = new HashMap<Integer, Integer>();
    for (int i = 0; i < inorder.size(); ++i) {
      nodeToInorderIdx.put(inorder.get(i), i);
    }
    return binaryTreeFromPreorderInorderHelper(
        preorder, 0, preorder.size(), 0, inorder.size(), nodeToInorderIdx);
  }

  // Builds the subtree with preorder.subList(preorderStart, preorderEnd) and
  // inorder.subList(inorderStart, inorderEnd).
  private static BinaryTreeNode<Integer> binaryTreeFromPreorderInorderHelper(
      List<Integer> preorder, int preorderStart, int preorderEnd,
      int inorderStart, int inorderEnd,
      Map<Integer, Integer> nodeToInorderIdx) {
    if (preorderEnd <= preorderStart || inorderEnd <= inorderStart) {
      return null;
    }
    int rootInorderIdx = nodeToInorderIdx.get(preorder.get(preorderStart));
    int leftSubtreeSize = rootInorderIdx - inorderStart;

    return new BinaryTreeNode<>(
        preorder.get(preorderStart),
        // Recursively builds the left subtree.
        binaryTreeFromPreorderInorderHelper(
            preorder, preorderStart + 1, preorderStart + 1 + leftSubtreeSize,
            inorderStart, rootInorderIdx, nodeToInorderIdx),
        // Recursively builds the right subtree.
        binaryTreeFromPreorderInorderHelper(
            preorder, preorderStart + 1 + leftSubtreeSize, preorderEnd,
            rootInorderIdx + 1, inorderEnd, nodeToInorderIdx));
  }
  // @exclude

  private static void simpleTest() {
    List<Integer> inorder = Arrays.asList(1);
    List<Integer> preorder = Arrays.asList(1);
    BinaryTreeNode<Integer> res
        = binaryTreeFromPreorderInorder(preorder, inorder);
    assert(res.data == 1);

    inorder = Arrays.asList(1, 2);
    preorder = Arrays.asList(2, 1);
    res = binaryTreeFromPreorderInorder(preorder, inorder);
    assert(res.data == 2);
    assert(res.left.data == 1);
    assert(res.right == null);

    int N = 100;
    inorder = new ArrayList<>(N);
    preorder = new ArrayList<>(N);
    for (int i = 0; i < N; i++) {
      inorder.add(i);
      preorder.add((N - 1) - i);
    }

    res = binaryTreeFromPreorderInorder(preorder, inorder);
    assert(res.data == N - 1);
    assert(res.left.data == N - 2);
    assert(res.right == null);
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
      BinaryTreeNode<Integer> root = generateRandBinaryTree(n, true);
      List<Integer> preorder = generatePreorder(root);
      List<Integer> inorder = generateInorder(root);
      BinaryTreeNode<Integer> res
          = binaryTreeFromPreorderInorder(preorder, inorder);
      assert(root.equals(res));
    }
  }
}
