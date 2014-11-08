// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <memory>

#include "./Binary_tree_prototype.h"

using std::boolalpha;
using std::cout;
using std::endl;
using std::unique_ptr;

bool CheckSymmetric(const unique_ptr<BinaryTreeNode<int>>&,
                       const unique_ptr<BinaryTreeNode<int>>&);

// @include
bool IsSymmetric(const unique_ptr<BinaryTreeNode<int>>& tree) {
  return tree == nullptr || CheckSymmetric(tree->left, tree->right);
}

bool CheckSymmetric(const unique_ptr<BinaryTreeNode<int>>& subtree_0,
                       const unique_ptr<BinaryTreeNode<int>>& subtree_1) {
  if (subtree_0 == nullptr && subtree_1 == nullptr) {
    return true;
  } else if (subtree_0 != nullptr && subtree_1 != nullptr) {
    return subtree_0->data == subtree_1->data &&
           CheckSymmetric(subtree_0->left, subtree_1->right) &&
           CheckSymmetric(subtree_0->right, subtree_1->left);
  }
  // One subtree is empty, and the other is not.
  return false;
}
// @exclude

int main(int argc, char* argv[]) {
  // Non symmetric tree test.
  //      3
  //    2   5
  //  1    4 6
  auto non_symm_root =
      unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>());
  non_symm_root->left =
      unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>());
  non_symm_root->left->left =
      unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>());
  non_symm_root->right =
      unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>());
  non_symm_root->right->left =
      unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>());
  non_symm_root->right->right =
      unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>());
  assert(!IsSymmetric(non_symm_root));
  cout << boolalpha << IsSymmetric(non_symm_root) << endl;
  // Symmetric tree test.
  unique_ptr<BinaryTreeNode<int>> symm_root =
      unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>());
  symm_root->left = unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>());
  symm_root->right = unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>());
  assert(IsSymmetric(symm_root) == true);
  cout << boolalpha << IsSymmetric(symm_root) << endl;
  // Empty tree test.
  symm_root = nullptr;
  assert(IsSymmetric(symm_root) == true);
  cout << boolalpha << IsSymmetric(symm_root) << endl;
  return 0;
}
