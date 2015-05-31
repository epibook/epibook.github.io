package com.epi;

import com.epi.BinarySearchTreePrototypeTemplate.BSTNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FindKLargestBST {
  // @include
  public static List<Integer> findKLargestInBST(BSTNode<Integer> tree, int k) {
    List<Integer> kLargestElements = new ArrayList<>();
    findKLargestInBSTHelper(tree, k, kLargestElements);
    return kLargestElements;
  }

  private static void findKLargestInBSTHelper(BSTNode<Integer> tree, int k,
                                              List<Integer> kLargestElements) {
    // Perform reverse inorder traversal.
    if (tree != null && kLargestElements.size() < k) {
      findKLargestInBSTHelper(tree.getRight(), k, kLargestElements);
      if (kLargestElements.size() < k) {
        kLargestElements.add(tree.getData());
        findKLargestInBSTHelper(tree.getLeft(), k, kLargestElements);
      }
    }
  }
  // @exclude

  public static void main(String[] args) {
    // 3
    // 2 5
    // 1 4 6
    BSTNode<Integer> tree = new BSTNode<>(3);
    tree.setLeft(new BSTNode<>(2));
    tree.getLeft().setLeft(new BSTNode<>(1));
    tree.setRight(new BSTNode<>(5));
    tree.getRight().setLeft(new BSTNode<>(4));
    tree.getRight().setRight(new BSTNode<>(6));
    Random r = new Random();
    int k;
    if (args.length == 1) {
      k = Integer.parseInt(args[0]);
    } else {
      k = r.nextInt(6) + 1;
    }
    System.out.println("k = " + k);
    List<Integer> ans = findKLargestInBST(tree, k);
    System.out.println(ans);
    for (int i = 1; i < ans.size(); ++i) {
      assert(ans.get(i - 1) >= ans.get(i));
    }
  }
}
