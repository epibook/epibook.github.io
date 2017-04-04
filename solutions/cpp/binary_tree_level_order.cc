// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <memory>
#include <queue>
#include <vector>

#include "./Binary_tree_prototype.h"

using std::cout;
using std::endl;
using std::equal;
using std::make_unique;
using std::move;
using std::queue;
using std::unique_ptr;
using std::vector;

// @include
vector<vector<int>> BinaryTreeDepthOrder(
    const unique_ptr<BinaryTreeNode<int>>& tree) {
  queue<BinaryTreeNode<int>*> curr_depth_nodes({tree.get()});
  vector<vector<int>> result;

  while (!curr_depth_nodes.empty()) {
    queue<BinaryTreeNode<int>*> next_depth_nodes;
    vector<int> this_level;
    while (!curr_depth_nodes.empty()) {
      auto curr = curr_depth_nodes.front();
      curr_depth_nodes.pop();
      if (curr) {
        this_level.emplace_back(curr->data);

        // Defer the null checks to the null test above.
        next_depth_nodes.emplace(curr->left.get());
        next_depth_nodes.emplace(curr->right.get());
      }
    }

    if (!this_level.empty()) {
      result.emplace_back(this_level);
    }
    curr_depth_nodes = next_depth_nodes;
  }
  return result;
}
// @exclude

int main(int argc, char* argv[]) {
  //      3
  //    2   5
  //  1    4 6
  // 10
  // 13
  unique_ptr<BinaryTreeNode<int>> tree = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{3, nullptr, nullptr});
  tree->left = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{2, nullptr, nullptr});
  tree->left->left = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{1, nullptr, nullptr});
  tree->left->left->left = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{10, nullptr, nullptr});
  tree->left->left->left->right = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{13, nullptr, nullptr});
  tree->right = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{5, nullptr, nullptr});
  tree->right->left = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{4, nullptr, nullptr});
  tree->right->right = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{6, nullptr, nullptr});
  // should output 3
  //               2 5
  //               1 4 6
  //               10
  //               13
  auto result = BinaryTreeDepthOrder(tree);
  vector<vector<int>> golden_res = {{3}, {2, 5}, {1, 4, 6}, {10}, {13}};
  assert(equal(golden_res.begin(), golden_res.end(), result.begin(),
               result.end()));
  return 0;
}
