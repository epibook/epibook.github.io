// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <memory>

#include "./Binary_tree_with_parent_prototype.h"

using std::cout;
using std::endl;
using std::make_unique;
using std::swap;
using std::unique_ptr;

int GetDepth(const BinaryTreeNode<int>*);

// @include
BinaryTreeNode<int>* LCA(const unique_ptr<BinaryTreeNode<int>>& node_0,
                         const unique_ptr<BinaryTreeNode<int>>& node_1) {
  auto *iter_0 = node_0.get(), *iter_1 = node_1.get();
  int depth_0 = GetDepth(iter_0), depth_1 = GetDepth(iter_1);
  // Makes iter_0 as the deeper node in order to simplify the code.
  if (depth_1 > depth_0) {
    swap(iter_0, iter_1);
  }
  // Ascends from the deeper node.
  int depth_diff = abs(depth_0 - depth_1);
  while (depth_diff--) {
    iter_0 = iter_0->parent;
  }

  // Now ascends both nodes until we reach the LCA.
  while (iter_0 != iter_1) {
    iter_0 = iter_0->parent, iter_1 = iter_1->parent;
  }
  return iter_0;
}

int GetDepth(const BinaryTreeNode<int>* node) {
  int depth = 0;
  while (node->parent) {
    ++depth, node = node->parent;
  }
  return depth;
}
// @exclude

int main(int argc, char* argv[]) {
  //      3
  //    2   5
  //  1    4 6
  auto root = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{3, nullptr, nullptr, nullptr});
  root->left = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{2, nullptr, nullptr, root.get()});
  root->left->left = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{1, nullptr, nullptr, root->left.get()});
  root->right = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{5, nullptr, nullptr, root.get()});
  root->right->left = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{4, nullptr, nullptr, root->right.get()});
  root->right->right = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{6, nullptr, nullptr, root->right.get()});

  // should output 3
  assert(LCA(root->left, root->right)->data == 3);
  cout << LCA(root->left, root->right)->data << endl;
  // should output 5
  assert(LCA(root->right->left, root->right->right)->data == 5);
  cout << LCA(root->right->left, root->right->right)->data << endl;
  // should output 3
  assert(LCA(root->left, root->right->left)->data == 3);
  cout << LCA(root->left, root->right->left)->data << endl;
  // should output 2
  assert(LCA(root->left, root->left->left)->data == 2);
  cout << LCA(root->left, root->left->left)->data << endl;
  return 0;
}
