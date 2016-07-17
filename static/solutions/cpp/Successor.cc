// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <memory>

#include "./Binary_tree_with_parent_prototype.h"

using std::cout;
using std::endl;
using std::make_unique;
using std::unique_ptr;

// @include
BinaryTreeNode<int>* FindSuccessor(
    const unique_ptr<BinaryTreeNode<int>>& node) {
  auto* iter = node.get();
  if (iter->right != nullptr) {
    // Successor is the leftmost element in node's right subtree.
    iter = iter->right.get();
    while (iter->left) {
      iter = iter->left.get();
    }
    return iter;
  }

  // Find the closest ancestor whose left subtree contains node.
  while (iter->parent != nullptr && iter->parent->right.get() == iter) {
    iter = iter->parent;
  }
  // A return value of nullptr means node does not have successor, i.e., it is
  // the rightmost node in the tree.
  return iter->parent;
}
// @exclude

int main(int argc, char* argv[]) {
  //      3
  //    2   5
  //  1    4  6
  auto root = make_unique<BinaryTreeNode<int>>(BinaryTreeNode<int>{3});
  root->parent = nullptr;
  assert(FindSuccessor(root) == nullptr);
  root->left = make_unique<BinaryTreeNode<int>>(BinaryTreeNode<int>{2});
  root->left->parent = root.get();
  assert(FindSuccessor(root->left)->data == 3);

  root->left->left = make_unique<BinaryTreeNode<int>>(BinaryTreeNode<int>{1});
  root->left->left->parent = root->left.get();
  assert(FindSuccessor(root->left)->data == 3);
  assert(FindSuccessor(root->left->left)->data == 2);

  root->right = make_unique<BinaryTreeNode<int>>(BinaryTreeNode<int>{5});
  root->right->parent = root.get();
  root->right->left =
      make_unique<BinaryTreeNode<int>>(BinaryTreeNode<int>{4});
  root->right->left->parent = root->right.get();
  root->right->right =
      make_unique<BinaryTreeNode<int>>(BinaryTreeNode<int>{6});
  root->right->right->parent = root->right.get();
  // should output 6
  auto* node = FindSuccessor(root->right);
  assert(6 == node->data);
  if (node) {
    cout << node->data << endl;
  } else {
    cout << "null" << endl;
  }
  // should output "null"
  node = FindSuccessor(root->right->right);
  assert(!node);
  if (node) {
    cout << node->data << endl;
  } else {
    cout << "null" << endl;
  }
  return 0;
}
