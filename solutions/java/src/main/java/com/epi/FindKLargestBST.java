package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class FindKLargestBST {
  // @include
  public static List<Integer> findKLargestInBST(
      BinaryTree<Integer> root, int k) {
    List<Integer> kElements = new ArrayList<>();
    findKLargestInBSTHelper(root, k, kElements);
    return kElements;
  }

  private static void findKLargestInBSTHelper(
      BinaryTree<Integer> r, int k, List<Integer> kElements) {
    // Performs reverse inorder traversal.
    if (r != null && kElements.size() < k) {
      findKLargestInBSTHelper(r.getRight(), k, kElements);
      if (kElements.size() < k) {
        kElements.add(r.getData());
        findKLargestInBSTHelper(r.getLeft(), k, kElements);
      }
    }
  }
  // @exclude

  public static void main(String[] args) {
    // 3
    // 2 5
    // 1 4 6
    BinaryTree<Integer> root = new BinaryTree<>(3);
    root.setLeft(new BinaryTree<>(2));
    root.getLeft().setLeft(new BinaryTree<>(1));
    root.setRight(new BinaryTree<>(5));
    root.getRight().setLeft(new BinaryTree<>(4));
    root.getRight().setRight(new BinaryTree<>(6));
    Random r = new Random();
    int k;
    if (args.length == 1) {
      k = Integer.parseInt(args[0]);
    } else {
      k = r.nextInt(6) + 1;
    }
    System.out.println("k = " + k);
    List<Integer> ans = findKLargestInBST(root, k);
    System.out.println(ans);
    for (int i = 1; i < ans.size(); ++i) {
      assert (ans.get(i - 1) >= ans.get(i));
    }

  }
}
