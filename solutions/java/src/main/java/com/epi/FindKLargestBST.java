package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FindKLargestBST {
  // @include
  public static List<Integer> findKLargestInBST(
      BinaryTree<Integer> root, int k) {
    List<Integer> kLargestElements = new ArrayList<>();
    findKLargestInBSTHelper(root, k, kLargestElements);
    return kLargestElements;
  }

  private static void findKLargestInBSTHelper(
      BinaryTree<Integer> root, int k, List<Integer> kLargestElements) {
    // Perform reverse inorder traversal.
    if (root != null && kLargestElements.size() < k) {
      findKLargestInBSTHelper(root.getRight(), k, kLargestElements);
      if (kLargestElements.size() < k) {
        kLargestElements.add(root.getData());
        findKLargestInBSTHelper(root.getLeft(), k, kLargestElements);
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
