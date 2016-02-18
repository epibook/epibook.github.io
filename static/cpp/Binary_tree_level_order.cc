// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <queue>
#include <memory>
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
  queue<BinaryTreeNode<int>*> processing_nodes;
  processing_nodes.emplace(tree.get());
  int num_nodes_to_process_at_current_level = processing_nodes.size();
  vector<vector<int>> result;
  vector<int> one_level;

  while (!processing_nodes.empty()) {
    auto curr = processing_nodes.front();
    processing_nodes.pop();
    --num_nodes_to_process_at_current_level;
    if (curr) {
      one_level.emplace_back(curr->data);

      // Defer the null checks to the null test above.
      processing_nodes.emplace(curr->left.get());
      processing_nodes.emplace(curr->right.get());
    }
    // Done with the nodes at the current depth.
    if (!num_nodes_to_process_at_current_level) {
      num_nodes_to_process_at_current_level = processing_nodes.size();
      if (!one_level.empty()) {
        result.emplace_back(move(one_level));
      }
    }
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
  assert(golden_res.size() == result.size() &&
         equal(golden_res.begin(), golden_res.end(), result.begin()));
  return 0;
}
