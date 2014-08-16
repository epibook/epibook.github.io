// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <memory>

#include "./Binary_tree_prototype.h"

using std::cout;
using std::endl;
using std::unique_ptr;

// @include
BinaryTreeNode<int>* LCA(const unique_ptr<BinaryTreeNode<int>>& T,
                         const unique_ptr<BinaryTreeNode<int>>& a,
                         const unique_ptr<BinaryTreeNode<int>>& b) {
  if (!T) {  // Empty subtree.
    return nullptr;
  } else if (T == a || T == b) {
    return T.get();
  }

  auto* l_res = LCA(T->left, a, b), *r_res = LCA(T->right, a, b);
  if (l_res && r_res) {
    return T.get();  // Found a and b in different subtrees.
  }
  return l_res ? l_res : r_res;
}
// @exclude

int main(int argc, char* argv[]) {
  //      3
  //    2   5
  //  1    4 6
  unique_ptr<BinaryTreeNode<int>> root = unique_ptr<BinaryTreeNode<int>>(
      new BinaryTreeNode<int>{3, nullptr, nullptr});
  root->left = unique_ptr<BinaryTreeNode<int>>(
      new BinaryTreeNode<int>{2, nullptr, nullptr});
  root->left->left = unique_ptr<BinaryTreeNode<int>>(
      new BinaryTreeNode<int>{1, nullptr, nullptr});
  root->right = unique_ptr<BinaryTreeNode<int>>(
      new BinaryTreeNode<int>{5, nullptr, nullptr});
  root->right->left = unique_ptr<BinaryTreeNode<int>>(
      new BinaryTreeNode<int>{4, nullptr, nullptr});
  root->right->right = unique_ptr<BinaryTreeNode<int>>(
      new BinaryTreeNode<int>{6, nullptr, nullptr});
  // should output 3
  auto* x = LCA(root, root->left, root->right);
  assert(x->data == 3);
  cout << x->data << endl;
  // should output 5
  x = LCA(root, root->right->left, root->right->right);
  assert(x->data == 5);
  cout << x->data << endl;
  // should output 5
  x = LCA(root, root->right, root->right->right);
  assert(x->data == 5);
  cout << x->data << endl;
  // should output 3
  x = LCA(root, root, root->left->left);
  assert(x->data == 3);
  cout << x->data << endl;
  // should output 2
  x = LCA(root, root->left, root->left->left);
  assert(x->data == 2);
  cout << x->data << endl;
  return 0;
}
