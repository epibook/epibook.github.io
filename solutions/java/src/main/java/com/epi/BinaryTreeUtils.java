package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BinaryTreeUtils {
  public static BinaryTreeNode<Integer> generateRandBinaryTree(
      int n, boolean isUnique) {
    Random r = new Random();
    List<BinaryTreeNode<Integer>> l = new ArrayList<>();
    BinaryTreeNode<Integer> root
        = new BinaryTreeNode<>(isUnique ? n-- : r.nextInt(Integer.MAX_VALUE));
    l.add(root);
    while (n-- > 0) {
      int x = r.nextInt(l.size());
      boolean addLeft = r.nextBoolean();
      BinaryTreeNode<Integer> it = l.get(x);
      if (addLeft && it.left == null || !addLeft && it.right == null) {
        it.left
            = new BinaryTreeNode<>(isUnique ? n : r.nextInt(Integer.MAX_VALUE));
        l.add(it.left);
      } else {
        it.right
            = new BinaryTreeNode<>(isUnique ? n : r.nextInt(Integer.MAX_VALUE));
        l.add(it.right);
      }
      if (it.left != null && it.right != null) {
        l.remove(x);
      }
    }
    return root;
  }

  private static <T> void generatePreorderHelper(BinaryTreeNode<T> r,
                                                 List<T> ret) {
    if (r != null) {
      ret.add(r.data);
      generatePreorderHelper(r.left, ret);
      generatePreorderHelper(r.right, ret);
    }
  }

  public static <T> List<T> generatePreorder(BinaryTreeNode<T> r) {
    List<T> ret = new ArrayList<>();
    generatePreorderHelper(r, ret);
    return ret;
  }

  private static <T> void generateInorderHelper(BinaryTreeNode<T> r,
                                                List<T> ret) {
    if (r != null) {
      generateInorderHelper(r.left, ret);
      ret.add(r.data);
      generateInorderHelper(r.right, ret);
    }
  }

  public static <T> List<T> generateInorder(BinaryTreeNode<T> r) {
    List<T> ret = new ArrayList<>();
    generateInorderHelper(r, ret);
    return ret;
  }

  private static <T> void generatePostorderHelper(BinaryTreeNode<T> r,
                                                  List<T> ret) {
    if (r != null) {
      generatePostorderHelper(r.left, ret);
      generatePostorderHelper(r.right, ret);
      ret.add(r.data);
    }
  }

  public static <T> List<T> generatePostorder(BinaryTreeNode<T> r) {
    List<T> ret = new ArrayList<>();
    generatePostorderHelper(r, ret);
    return ret;
  }
}
