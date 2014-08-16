// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <memory>

#include "./Binary_tree_prototype.h"

using std::boolalpha;
using std::cout;
using std::endl;
using std::unique_ptr;

bool IsSymmetricHelper(const unique_ptr<BinaryTreeNode<int>>& l_T,
                       const unique_ptr<BinaryTreeNode<int>>& r_T);

// @include
bool IsSymmetric(const unique_ptr<BinaryTreeNode<int>>& T) {
  return !T || IsSymmetricHelper(T->left, T->right);
}

bool IsSymmetricHelper(const unique_ptr<BinaryTreeNode<int>>& l_T,
                       const unique_ptr<BinaryTreeNode<int>>& r_T) {
  if (!l_T && !r_T) {
    return true;
  } else if (l_T && r_T) {
    return l_T->data == r_T->data &&
           IsSymmetricHelper(l_T->left, r_T->right) &&
           IsSymmetricHelper(l_T->right, r_T->left);
  } else {  // (l_T && !r_T) || (!l_T && r_T)
    return false;
  }
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
