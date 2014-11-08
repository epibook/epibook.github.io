package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.epi.BinaryTreeUtils.*;

public class ReconstructBinaryTreePreInOrders {
  // @include
  public static BinaryTree<Integer> reconstructPreInOrders(int[] pre,
                                                           int[] in) {
    Map<Integer, Integer> inEntryIdxMap = new HashMap<Integer, Integer>();
    for (int i = 0; i < in.length; ++i) {
      inEntryIdxMap.put(in[i], i);
    }
    return reconstructPreInOrdersHelper(pre, 0, pre.length, in, 0, in.length,
                                        inEntryIdxMap);
  }

  // Reconstructs the binary tree from pre[pre_s : pre_e - 1] and
  // in[in_s : in_e - 1].
  private static BinaryTree<Integer> reconstructPreInOrdersHelper(
      int[] pre, int preS, int preE, int[] in, int inS, int inE,
      Map<Integer, Integer> inEntryIdxMap) {
    if (preE <= preS || inE <= inS) {
      return null;
    }
    int idx = inEntryIdxMap.get(pre[preS]);
    int leftTreeSize = idx - inS;

    return new BinaryTree<>(pre[preS],
        // Recursively builds the left subtree.
        reconstructPreInOrdersHelper(pre, preS + 1, preS + 1 + leftTreeSize,
                                     in, inS, idx, inEntryIdxMap),
        // Recursively builds the right subtree.
        reconstructPreInOrdersHelper(pre, preS + 1 + leftTreeSize, preE, in,
                                     idx + 1, inE, inEntryIdxMap));
  }
  // @exclude

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
      BinaryTree<Integer> root = generateRandBinaryTree(n, true);
      List<Integer> pre = generatePreOrder(root);
      int[] preOrder = new int[pre.size()];
      for (int i = 0; i < preOrder.length; ++i) {
        preOrder[i] = pre.get(i);
      }
      List<Integer> in = generateInOrder(root);
      int[] inOrder = new int[in.size()];
      for (int i = 0; i < inOrder.length; ++i) {
        inOrder[i] = in.get(i);
      }
      BinaryTree<Integer> res = reconstructPreInOrders(preOrder, inOrder);
      assert (root.equals(res));
    }
  }
}
