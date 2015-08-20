// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <memory>

#include "./Binary_tree_prototype.h"

using std::cout;
using std::endl;
using std::pair;
using std::unique_ptr;

pair<int, BinaryTreeNode<int>*> LCAHelper(
    const unique_ptr<BinaryTreeNode<int>>&,
    const unique_ptr<BinaryTreeNode<int>>&,
    const unique_ptr<BinaryTreeNode<int>>&);

// @include
BinaryTreeNode<int>* LCA(const unique_ptr<BinaryTreeNode<int>>& tree,
                         const unique_ptr<BinaryTreeNode<int>>& node0,
                         const unique_ptr<BinaryTreeNode<int>>& node1) {
  return LCAHelper(tree, node0, node1).second;
}

// Returns a pair of int and node pointer; int field is 0, 1, or 2 depending
// on how many of node0 and node1 are present in tree. If both are present in
// tree, the node pointer is a common ancestor. It may not be the LCA
// initially, but it will be LCA when the algorithm terminates.
pair<int, BinaryTreeNode<int>*> LCAHelper(
    const unique_ptr<BinaryTreeNode<int>>& tree,
    const unique_ptr<BinaryTreeNode<int>>& node0,
    const unique_ptr<BinaryTreeNode<int>>& node1) {
  if (tree == nullptr) {
    return {0, nullptr};
  }

  auto left_result = LCAHelper(tree->left, node0, node1);
  if (left_result.first == 2) {  // Found both nodes in the left subtree.
    return left_result;
  }
  auto right_result = LCAHelper(tree->right, node0, node1);
  if (right_result.first == 2) {  // Found both nodes in the right subtree.
    return right_result;
  }
  int num_target_nodes =
      left_result.first + right_result.first + (tree == node0 || tree == node1);
  return {num_target_nodes, num_target_nodes == 2 ? tree.get() : nullptr};
}
// @exclude

int main(int argc, char* argv[]) {
  //      3
  //    2   5
  //  1    4 6
  unique_ptr<BinaryTreeNode<int>> tree = unique_ptr<BinaryTreeNode<int>>(
      new BinaryTreeNode<int>{3, nullptr, nullptr});
  tree->left = unique_ptr<BinaryTreeNode<int>>(
      new BinaryTreeNode<int>{2, nullptr, nullptr});
  tree->left->left = unique_ptr<BinaryTreeNode<int>>(
      new BinaryTreeNode<int>{1, nullptr, nullptr});
  tree->right = unique_ptr<BinaryTreeNode<int>>(
      new BinaryTreeNode<int>{5, nullptr, nullptr});
  tree->right->left = unique_ptr<BinaryTreeNode<int>>(
      new BinaryTreeNode<int>{4, nullptr, nullptr});
  tree->right->right = unique_ptr<BinaryTreeNode<int>>(
      new BinaryTreeNode<int>{6, nullptr, nullptr});
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
  return 0;
}
