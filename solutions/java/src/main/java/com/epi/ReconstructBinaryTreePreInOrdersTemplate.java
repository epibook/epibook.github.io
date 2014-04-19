package com.epi;

import static com.epi.BinaryTreeUtils.generateInOrder;
import static com.epi.BinaryTreeUtils.generatePreOrder;
import static com.epi.BinaryTreeUtils.generateRandBinaryTree;

import java.util.ArrayList;
import java.util.Random;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class ReconstructBinaryTreePreInOrdersTemplate {
  // @include
  private static <T> BinaryTree<T> reconstructPreInOrdersHelper(
      ArrayList<T> pre, int preS, int preE, ArrayList<T> in, int inS, int inE) {
    if (preE > preS && inE > inS) {
      int it = in.subList(inS, inE).indexOf(pre.get(preS));
      it = it < 0 ? inE : (it + inS);
      int leftTreeSize = it - inS;

      return new BinaryTree<T>(pre.get(preS), reconstructPreInOrdersHelper(pre,
          preS + 1, preS + 1 + leftTreeSize, in, inS, it),
          reconstructPreInOrdersHelper(pre, preS + 1 + leftTreeSize, preE, in,
              it + 1, inE));
    }
    return null;
  }

  public static <T> BinaryTree<T> reconstructPreInOrders(ArrayList<T> pre,
      ArrayList<T> in) {
    return reconstructPreInOrdersHelper(pre, 0, pre.size(), in, 0, in.size());
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
      ArrayList<Integer> pre = generatePreOrder(root);
      ArrayList<Integer> in = generateInOrder(root);
      BinaryTree<Integer> res = reconstructPreInOrders(pre, in);
      assert (root.equals(res));
    }
  }
}
