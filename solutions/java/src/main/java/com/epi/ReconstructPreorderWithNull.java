package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

import java.util.*;
import java.util.LinkedList;

import static com.epi.BinaryTreeUtils.generateRandBinaryTree;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class ReconstructPreorderWithNull {
  // @include
  public static BinaryTree<Integer> reconstructPreorder(
      List<Integer> preorder) {
    Deque<BinaryTree<Integer>> s = new LinkedList<>();
    for (int i = preorder.size() - 1; i >= 0; i--) {
      if (preorder.get(i) == null) {
        s.push(null);
      } else { // Non-null.
        BinaryTree<Integer> l = s.pop();
        BinaryTree<Integer> r = s.pop();
        s.push(new BinaryTree<>(preorder.get(i), l, r));
      }
    }
    return s.peek();
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
      BinaryTree<Integer> x = reconstructPreorder(p);
      assert (root.equals(x));
    }
  }
}
