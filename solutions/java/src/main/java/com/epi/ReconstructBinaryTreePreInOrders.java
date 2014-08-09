package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

import java.util.List;
import java.util.Random;

import static com.epi.BinaryTreeUtils.*;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class ReconstructBinaryTreePreInOrders {
  // @include
  public static <T> BinaryTree<T> reconstructPreInOrders(List<T> pre,
                                                         List<T> in) {
    return reconstructPreInOrdersHelper(pre, 0, pre.size(), in, 0, in.size());
  }

  private static <T> BinaryTree<T> reconstructPreInOrdersHelper(
      List<T> pre, int preS, int preE, List<T> in, int inS, int inE) {
    if (preE > preS && inE > inS) {
      int it = in.subList(inS, inE).indexOf(pre.get(preS));
      it = it < 0 ? inE : (it + inS);
      int leftTreeSize = it - inS;

      return new BinaryTree<>(pre.get(preS), reconstructPreInOrdersHelper(pre,
          preS + 1, preS + 1 + leftTreeSize, in, inS, it),
          reconstructPreInOrdersHelper(pre, preS + 1 + leftTreeSize, preE, in,
              it + 1, inE)
      );
    }
    return null;
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
      List<Integer> in = generateInOrder(root);
      BinaryTree<Integer> res = reconstructPreInOrders(pre, in);
      assert (root.equals(res));
    }
  }
}
