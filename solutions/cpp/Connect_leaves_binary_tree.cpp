// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <list>
#include <memory>

#include "./Binary_tree_prototype.h"

using std::cout;
using std::endl;
using std::list;
using std::unique_ptr;

// @include
list<const unique_ptr<BinaryTreeNode<int>>*> ConnectLeaves(
    const unique_ptr<BinaryTreeNode<int>>& tree) {
  list<const unique_ptr<BinaryTreeNode<int>>*> leaves;
  if (tree != nullptr) {
    if (tree->left == nullptr && tree->right == nullptr) {
      leaves.emplace_back(&tree);
    } else {
      // First do the left subtree, and then do the right subtree.
      leaves.splice(leaves.end(), ConnectLeaves(tree->left));
      leaves.splice(leaves.end(), ConnectLeaves(tree->right));
    }
  }
  return leaves;
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
  auto L = ConnectLeaves(root);
  list<int> output;
  // should output 1, 4, 6
  for (const auto* l : L) {
    output.push_back((*l)->data);
    cout << (*l)->data << endl;
  }
  list<int> golden_res = {1, 4, 6};
  assert(output.size() == golden_res.size());
  assert(equal(output.begin(), output.end(), golden_res.begin()));
  return 0;
}
