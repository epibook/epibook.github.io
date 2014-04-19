package com.epi;

import java.util.ArrayList;
import java.util.Random;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class BinaryTreeUtils {
  public static BinaryTree<Integer> generateRandBinaryTree(int n,
      boolean isUnique) {
    Random r = new Random();
    ArrayList<BinaryTree<Integer>> l = new ArrayList<BinaryTree<Integer>>();
    BinaryTree<Integer> root = new BinaryTree<Integer>(isUnique ? n--
        : r.nextInt(Integer.MAX_VALUE));
    l.add(root);
    while (n-- > 0) {
      int x = r.nextInt(l.size());
      boolean addLeft = r.nextBoolean();
      BinaryTree<Integer> it = l.get(x);
      if (addLeft && it.getLeft() == null || !addLeft && it.getRight() == null) {
        it.setLeft(new BinaryTree<Integer>(isUnique ? n : r
            .nextInt(Integer.MAX_VALUE)));
        l.add(it.getLeft());
      } else {
        it.setRight(new BinaryTree<Integer>(isUnique ? n : r
            .nextInt(Integer.MAX_VALUE)));
        l.add(it.getRight());
      }
      if (it.getLeft() != null && it.getRight() != null) {
        l.remove(x);
      }
    }
    return root;
  }

  private static <T> void generatePreOrderHelper(BinaryTree<T> r,
      ArrayList<T> ret) {
    if (r != null) {
      ret.add(r.getData());
      generatePreOrderHelper(r.getLeft(), ret);
      generatePreOrderHelper(r.getRight(), ret);
    }
  }

  public static <T> ArrayList<T> generatePreOrder(BinaryTree<T> r) {
    ArrayList<T> ret = new ArrayList<T>();
    generatePreOrderHelper(r, ret);
    return ret;
  }

  private static <T> void generateInOrderHelper(BinaryTree<T> r,
      ArrayList<T> ret) {
    if (r != null) {
      generateInOrderHelper(r.getLeft(), ret);
      ret.add(r.getData());
      generateInOrderHelper(r.getRight(), ret);
    }
  }

  public static <T> ArrayList<T> generateInOrder(BinaryTree<T> r) {
    ArrayList<T> ret = new ArrayList<T>();
    generateInOrderHelper(r, ret);
    return ret;
  }

  private static <T> void generatePostOrderHelper(BinaryTree<T> r,
      ArrayList<T> ret) {
    if (r != null) {
      generatePostOrderHelper(r.getLeft(), ret);
      generatePostOrderHelper(r.getRight(), ret);
      ret.add(r.getData());
    }
  }

  public static <T> ArrayList<T> generatePostOrder(BinaryTree<T> r) {
    ArrayList<T> ret = new ArrayList<T>();
    generatePostOrderHelper(r, ret);
    return ret;
  }

}
