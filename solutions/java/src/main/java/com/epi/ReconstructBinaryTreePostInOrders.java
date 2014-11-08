package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.epi.BinaryTreeUtils.*;

public class ReconstructBinaryTreePostInOrders {
  // @include
  public static BinaryTree<Integer> reconstructPostInOrders(int[] post,
                                                            int[] in) {
    Map<Integer, Integer> inEntryIdxMap = new HashMap<Integer, Integer>();
    for (int i = 0; i < in.length; ++i) {
      inEntryIdxMap.put(in[i], i);
    }
    return reconstructPostInOrdersHelper(post, 0, post.length, in, 0,
                                         in.length, inEntryIdxMap);
  }

  private static BinaryTree<Integer> reconstructPostInOrdersHelper(
      int[] post, int postS, int postE, int[] in, int inS, int inE,
      Map<Integer, Integer> inEntryIdxMap) {
    if (postE <= postS || inE <= inS) {
      return null;
    }
    int idx = inEntryIdxMap.get(post[postE - 1]);
    int leftTreeSize = idx - inS;

    return new BinaryTree<>(post[postE - 1],
        // Recursively build the left subtree.
        reconstructPostInOrdersHelper(post, postS, postS + leftTreeSize, in,
                                      inS, idx, inEntryIdxMap),
        // Recursively build the right subtree.
        reconstructPostInOrdersHelper(post, postS + leftTreeSize, postE - 1,
                                      in, idx + 1, inE, inEntryIdxMap));
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
      List<Integer> post = generatePostOrder(root);
      int[] postOrder = new int[post.size()];
      for (int i = 0; i < postOrder.length; ++i) {
        postOrder[i] = post.get(i);
      }
      List<Integer> in = generateInOrder(root);
      int[] inOrder = new int[in.size()];
      for (int i = 0; i < inOrder.length; ++i) {
        inOrder[i] = in.get(i);
      }
      BinaryTree<Integer> res = reconstructPostInOrders(postOrder, inOrder);
      assert (root.equals(res));
    }
  }
}
