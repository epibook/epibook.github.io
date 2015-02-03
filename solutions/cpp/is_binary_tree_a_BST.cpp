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

bool AreKeysInRange(const unique_ptr<BinaryTreeNode<int>>& root, int low_range,
                    int high_range);

// @include
bool IsBinaryTreeBST(const unique_ptr<BinaryTreeNode<int>>& root) {
  return AreKeysInRange(root, numeric_limits<int>::min(),
                        numeric_limits<int>::max());
}

bool AreKeysInRange(const unique_ptr<BinaryTreeNode<int>>& root, int low_range,
                    int high_range) {
  if (root == nullptr) {
    return true;
  } else if (root->data < low_range || root->data > high_range) {
    return false;
  }

  return AreKeysInRange(root->left, low_range, root->data) &&
         AreKeysInRange(root->right, root->data, high_range);
}
// @exclude

int main(int argc, char* argv[]) {
  //      3
  //    2   5
  //  1    4 6
  auto root = unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>{3});
  root->left = unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>{2});
  root->left->left = unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>{1});
  root->right = unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>{5});
  root->right->left = unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>{4});
  root->right->right = unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>{6});
  // should output true.
  assert(IsBinaryTreeBST(root) == true);
  cout << boolalpha << IsBinaryTreeBST(root) << endl;
  //      10
  //    2   5
  //  1    4 6
  root->data = 10;
  // should output false.
  assert(!IsBinaryTreeBST(root));
  cout << boolalpha << IsBinaryTreeBST(root) << endl;
  // should output true.
  assert(IsBinaryTreeBST(nullptr) == true);
  cout << boolalpha << IsBinaryTreeBST(nullptr) << endl;
  return 0;
}
