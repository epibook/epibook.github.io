// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <memory>

#include "./Binary_tree_prototype.h"

using std::cout;
using std::endl;
using std::make_unique;
using std::unique_ptr;

struct Status;
Status LCAHelper(const unique_ptr<BinaryTreeNode<int>>&,
                 const unique_ptr<BinaryTreeNode<int>>&,
                 const unique_ptr<BinaryTreeNode<int>>&);

// @include
struct Status {
  int num_target_nodes;
  BinaryTreeNode<int>* ancestor;
};

BinaryTreeNode<int>* LCA(const unique_ptr<BinaryTreeNode<int>>& tree,
                         const unique_ptr<BinaryTreeNode<int>>& node0,
                         const unique_ptr<BinaryTreeNode<int>>& node1) {
  return LCAHelper(tree, node0, node1).ancestor;
}

// Returns an object consisting of an int and a node. The int field is
// 0, 1, or 2 depending on how many of {node0, node1} are present in
// the tree. If both are present in the tree, when ancestor is
// assigned to a non-null value, it is the LCA.
Status LCAHelper(const unique_ptr<BinaryTreeNode<int>>& tree,
                 const unique_ptr<BinaryTreeNode<int>>& node0,
                 const unique_ptr<BinaryTreeNode<int>>& node1) {
  if (tree == nullptr) {
    return {0, nullptr};
  }

  auto left_result = LCAHelper(tree->left, node0, node1);
  if (left_result.num_target_nodes ==
      2) {  // Found both nodes in the left subtree.
    return left_result;
  }
  auto right_result = LCAHelper(tree->right, node0, node1);
  if (right_result.num_target_nodes ==
      2) {  // Found both nodes in the right subtree.
    return right_result;
  }
  int num_target_nodes = left_result.num_target_nodes +
                         right_result.num_target_nodes + (tree == node0) +
                         (tree == node1);
  return {num_target_nodes, num_target_nodes == 2 ? tree.get() : nullptr};
}
// @exclude

int main(int argc, char* argv[]) {
  //      3
  //    2   5
  //  1    4 6
  unique_ptr<BinaryTreeNode<int>> tree = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{3, nullptr, nullptr});
  tree->left = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{2, nullptr, nullptr});
  tree->left->left = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{1, nullptr, nullptr});
  tree->right = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{5, nullptr, nullptr});
  tree->right->left = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{4, nullptr, nullptr});
  tree->right->right = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{6, nullptr, nullptr});
  // should output 3
  auto* x = LCA(tree, tree->left, tree->right);
  assert(x->data == 3);
  cout << x->data << endl;
  // should output 5
  x = LCA(tree, tree->right->left, tree->right->right);
  assert(x->data == 5);
  cout << x->data << endl;
  // should output 5
  x = LCA(tree, tree->right, tree->right->right);
  assert(x->data == 5);
  cout << x->data << endl;
  // should output 3
  x = LCA(tree, tree->left->left, tree->right->right);
  assert(x->data == 3);
  cout << x->data << endl;
  // should output 3
  x = LCA(tree, tree->left->left, tree);
  assert(x->data == 3);
  cout << x->data << endl;
  // should output 2
  x = LCA(tree, tree->left, tree->left);
  assert(x->data == 2);
  cout << x->data << endl;
  return 0;
}
