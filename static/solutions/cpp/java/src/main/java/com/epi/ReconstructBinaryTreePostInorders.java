package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static com.epi.BinaryTreeUtils.generateInorder;
import static com.epi.BinaryTreeUtils.generatePostorder;
import static com.epi.BinaryTreeUtils.generateRandBinaryTree;

public class ReconstructBinaryTreePostInorders {
  // @include
  public static BinaryTreeNode<Integer> reconstructPostInorders(
      List<Integer> post, List<Integer> in) {
    Map<Integer, Integer> inEntryIdxMap = new HashMap<Integer, Integer>();
    for (int i = 0; i < in.size(); ++i) {
      inEntryIdxMap.put(in.get(i), i);
    }
    return reconstructPostInordersHelper(post, 0, post.size(), 0, in.size(),
                                         inEntryIdxMap);
  }

  private static BinaryTreeNode<Integer> reconstructPostInordersHelper(
      List<Integer> post, int postS, int postE, int inS, int inE,
      Map<Integer, Integer> inEntryIdxMap) {
    if (postE <= postS || inE <= inS) {
      return null;
    }
    int idx = inEntryIdxMap.get(post.get(postE - 1));
    int leftTreeSize = idx - inS;

    return new BinaryTreeNode<>(
        post.get(postE - 1),
        // Recursively build the left subtree.
        reconstructPostInordersHelper(post, postS, postS + leftTreeSize, inS,
                                      idx, inEntryIdxMap),
        // Recursively build the right subtree.
        reconstructPostInordersHelper(post, postS + leftTreeSize, postE - 1,
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
      BinaryTreeNode<Integer> root = generateRandBinaryTree(n, true);
      List<Integer> post = generatePostorder(root);
      List<Integer> in = generateInorder(root);
      BinaryTreeNode<Integer> res = reconstructPostInorders(post, in);
      assert(root.equals(res));
    }
  }
}
