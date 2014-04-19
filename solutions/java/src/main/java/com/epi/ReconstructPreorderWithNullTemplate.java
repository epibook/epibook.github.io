package com.epi;

import static com.epi.BinaryTreeUtils.generateRandBinaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class ReconstructPreorderWithNullTemplate {
  // @include
  public static <T> BinaryTree<T> reconstructPreorder(ArrayList<T> preorder) {
    LinkedList<BinaryTree<T>> s = new LinkedList<BinaryTree<T>>();
    for (int i = preorder.size() - 1; i >= 0; i--) {
      if (preorder.get(i) == null) {
        s.push(null);
      } else {
        BinaryTree<T> l = s.pop();
        BinaryTree<T> r = s.pop();
        s.push(new BinaryTree<T>(preorder.get(i), l, r));
      }
    }
    return s.peek();
  }

  // @exclude

  private static <T> void genPreorderWithNull(BinaryTree<T> n, ArrayList<T> p) {
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
      ArrayList<Integer> p = new ArrayList<Integer>();
      genPreorderWithNull(root, p);
      BinaryTree<Integer> x = reconstructPreorder(p);
      assert (root.equals(x));
    }
  }
}
