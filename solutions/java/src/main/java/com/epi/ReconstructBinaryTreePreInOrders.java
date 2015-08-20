package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTreeNode;

import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.epi.BinaryTreeUtils.*;

public class ReconstructBinaryTreePreInOrders {
  // @include
  public static BinaryTreeNode<Integer> binaryTreeFromPreorderInorder(
      int[] preorder, int[] inorder) {
    Map<Integer, Integer> nodeToInorderIdx = new HashMap<Integer, Integer>();
    for (int i = 0; i < inorder.length; ++i) {
      nodeToInorderIdx.put(inorder[i], i);
    }
    return binaryTreeFromPreorderInorderHelper(preorder, 0, preorder.length, 0,
                                               inorder.length, nodeToInorderIdx);
  }

  // Builds the subtree with preorder[preorderStart : preorderEnd - 1] and
  // inorder[inorderStart : inorderEnd - 1].
  private static BinaryTreeNode<Integer> binaryTreeFromPreorderInorderHelper(
      int[] preorder, int preorderStart, int preorderEnd, int inorderStart,
      int inorderEnd, Map<Integer, Integer> nodeToInorderIdx) {
    if (preorderEnd <= preorderStart || inorderEnd <= inorderStart) {
      return null;
    }
    int rootInorderIdx = nodeToInorderIdx.get(preorder[preorderStart]);
    int leftSubtreeSize = rootInorderIdx - inorderStart;

    return new BinaryTreeNode<>(
        preorder[preorderStart],
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
    int[] inOrder = new int[] {1};
    int[] preOrder = new int[] {1};
    BinaryTreeNode<Integer> res =
        binaryTreeFromPreorderInorder(preOrder, inOrder);
    assert(res.getData() == 1);

    inOrder = new int[] {1, 2};
    preOrder = new int[] {2, 1};
    res = binaryTreeFromPreorderInorder(preOrder, inOrder);
    assert(res.getData() == 2);
    assert(res.getLeft().getData() == 1);
    assert(res.getRight() == null);

    int N = 100;
    inOrder = new int[N];
    preOrder = new int[N];
    for (int i = 0; i < N; i++) {
      inOrder[i] = i;
      preOrder[i] = (N - 1) - i;
    }

    res = binaryTreeFromPreorderInorder(preOrder, inOrder);
    assert(res.getData() == N - 1);
    assert(res.getLeft().getData() == N - 2);
    assert(res.getRight() == null);
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
      List<Integer> preorder = generatePreOrder(root);
      int[] preOrder = new int[preorder.size()];
      for (int i = 0; i < preOrder.length; ++i) {
        preOrder[i] = preorder.get(i);
      }
      List<Integer> inorder = generateInOrder(root);
      int[] inOrder = new int[inorder.size()];
      for (int i = 0; i < inOrder.length; ++i) {
        inOrder[i] = inorder.get(i);
      }
      BinaryTreeNode<Integer> res =
          binaryTreeFromPreorderInorder(preOrder, inOrder);
      assert(root.equals(res));
    }
  }
}
