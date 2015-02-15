// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <limits>
#include <memory>

#include "./Binary_tree_prototype.h"

using std::boolalpha;
using std::cout;
using std::endl;
using std::numeric_limits;
using std::unique_ptr;

bool AreKeysInRange(const unique_ptr<BinaryTreeNode<int>>& tree, int low_range,
                    int high_range);

// @include
bool IsBinaryTreeBST(const unique_ptr<BinaryTreeNode<int>>& tree) {
  return AreKeysInRange(tree, numeric_limits<int>::min(),
                        numeric_limits<int>::max());
}

bool AreKeysInRange(const unique_ptr<BinaryTreeNode<int>>& tree, int low_range,
                    int high_range) {
  if (tree == nullptr) {
    return true;
  } else if (tree->data < low_range || tree->data > high_range) {
    return false;
  }

  return AreKeysInRange(tree->left, low_range, tree->data) &&
         AreKeysInRange(tree->right, tree->data, high_range);
}
// @exclude

int main(int argc, char* argv[]) {
  //      3
  //    2   5
  //  1    4 6
  auto tree = unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>{3});
  tree->left = unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>{2});
  tree->left->left = unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>{1});
  tree->right = unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>{5});
  tree->right->left = unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>{4});
  tree->right->right = unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>{6});
  // should output true.
  assert(IsBinaryTreeBST(tree) == true);
  cout << boolalpha << IsBinaryTreeBST(tree) << endl;
  //      10
  //    2   5
  //  1    4 6
  tree->data = 10;
  // should output false.
  assert(!IsBinaryTreeBST(tree));
  cout << boolalpha << IsBinaryTreeBST(tree) << endl;
  // should output true.
  assert(IsBinaryTreeBST(nullptr) == true);
  cout << boolalpha << IsBinaryTreeBST(nullptr) << endl;
  return 0;
}
