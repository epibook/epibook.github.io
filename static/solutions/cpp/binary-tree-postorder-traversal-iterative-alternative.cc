// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <memory>
#include <stack>
#include <vector>

#include "./Binary_tree_prototype.h"
#include "./Binary_tree_utils.h"

using std::cout;
using std::endl;
using std::equal;
using std::make_unique;
using std::stack;
using std::unique_ptr;
using std::vector;

vector<int> InvertedPreorderTraversal(const unique_ptr<BinaryTreeNode<int>>&);

// @include
vector<int> PostorderTraversal(const unique_ptr<BinaryTreeNode<int>>& tree) {
  vector<int> sequence = InvertedPreorderTraversal(tree);
  reverse(sequence.begin(), sequence.end());
  return sequence;
}

vector<int> InvertedPreorderTraversal(
    const unique_ptr<BinaryTreeNode<int>>& tree) {
  stack<BinaryTreeNode<int>*> path;
  path.emplace(tree.get());
  vector<int> result;
  while (!path.empty()) {
    auto* curr = path.top();
    path.pop();
    if (curr == nullptr) {
      continue;
    }
    result.emplace_back(curr->data);
    path.emplace(curr->left.get());
    path.emplace(curr->right.get());
  }
  return result;
}
// @exclude

int main(int argc, char** argv) {
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
  auto res = PostorderTraversal(tree), golden_res = generate_postorder(tree);
  assert(equal(res.cbegin(), res.cend(), golden_res.cbegin(),
               golden_res.cend()));
  return 0;
}
