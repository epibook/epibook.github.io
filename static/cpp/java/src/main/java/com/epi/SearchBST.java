package com.epi;

import com.epi.BinarySearchTreePrototypeTemplate.BSTNode;

public class SearchBST {
  // @include
  public static BSTNode<Integer> searchBST(BSTNode<Integer> tree, int key) {
    if (tree == null || tree.data == key) {
      return tree;
    }
    return key < tree.data ? searchBST(tree.left, key)
                           : searchBST(tree.right, key);
  }
  // @exclude

  public static void main(String[] args) {
    //        43
    //    23     47
    //      37      53
    //    29  41
    //     31
    BSTNode<Integer> tree = new BSTNode<>(43);
    tree.left = new BSTNode<>(23);
    tree.left.right = new BSTNode<>(37);
    tree.left.right.left = new BSTNode<>(29);
    tree.left.right.left.right = new BSTNode<>(31);
    tree.left.right.right = new BSTNode<>(41);
    tree.right = new BSTNode<>(47);
    tree.right.right = new BSTNode<>(53);
    assert(tree == searchBST(tree, 43));
    assert(tree.left == searchBST(tree, 23));
    assert(tree.right == searchBST(tree, 47));
    assert(tree.right.right == searchBST(tree, 53));
    assert(tree.left.right.left == searchBST(tree, 29));
    assert(null == searchBST(tree, 1000));
  }
}
