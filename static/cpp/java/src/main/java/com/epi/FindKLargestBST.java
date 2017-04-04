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
      findKLargestInBSTHelper(tree.right, k, kLargestElements);
      if (kLargestElements.size() < k) {
        kLargestElements.add(tree.data);
        findKLargestInBSTHelper(tree.left, k, kLargestElements);
      }
    }
  }
  // @exclude

  public static void main(String[] args) {
    // 3
    // 2 5
    // 1 4 6
    BSTNode<Integer> tree = new BSTNode<>(3);
    tree.left = new BSTNode<>(2);
    tree.left.left = new BSTNode<>(1);
    tree.right = new BSTNode<>(5);
    tree.right.left = new BSTNode<>(4);
    tree.right.right = new BSTNode<>(6);
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
    ans = findKLargestInBST(tree, 2);
    assert(ans.get(0) == 6);
    assert(ans.get(1) == 5);

    // 3
    // 3 4
    // 1 4 6
    tree = new BSTNode<>(3);
    tree.left = new BSTNode<>(3);
    tree.left.left = new BSTNode<>(1);
    tree.right = new BSTNode<>(4);
    tree.right.left = new BSTNode<>(4);
    tree.right.right = new BSTNode<>(6);
    ans = findKLargestInBST(tree, 3);
    assert(ans.get(0) == 6);
    assert(ans.get(1) == 4);
    assert(ans.get(2) == 4);
  }
}
