package com.epi;

import static com.epi.BinaryTreeUtils.generateInOrder;
import static com.epi.BinaryTreeUtils.generatePostOrder;
import static com.epi.BinaryTreeUtils.generateRandBinaryTree;

import java.util.ArrayList;
import java.util.Random;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class ReconstructBinaryTreePostInOrdersTemplate {
  // @include
  private static <T> BinaryTree<T> reconstructPostInOrdersHelper(
      ArrayList<T> post, int postS, int postE, ArrayList<T> in, 
          int inS, int inE) {
    if (postE > postS && inE > inS) {
      int it = in.subList(inS, inE).indexOf(post.get(postE - 1));
      it = it < 0 ? inE : (it + inS);
      int leftTreeSize = it - inS;
      return new BinaryTree<T>(post.get(postE - 1),
      // Recursively build the left subtree.
          reconstructPostInOrdersHelper(post, postS, postS + leftTreeSize, in,
              inS, it),
          // Recursively build the right subtree.
          reconstructPostInOrdersHelper(post, postS + leftTreeSize, postE - 1,
              in, it + 1, inE));
    }
    return null;
  }

  public static <T> BinaryTree<T> reconstructPostInOrders(ArrayList<T> post,
      ArrayList<T> in) {
    return reconstructPostInOrdersHelper(post, 0, post.size(), in, 0, in.size());
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
      ArrayList<Integer> post = generatePostOrder(root);
      ArrayList<Integer> in = generateInOrder(root);
      BinaryTree<Integer> res = reconstructPostInOrders(post, in);
      assert (root.equals(res));
    }
  }
}
