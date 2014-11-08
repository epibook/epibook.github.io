// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <queue>
#include <memory>
#include <vector>

#include "./Binary_tree_prototype.h"

using std::cout;
using std::endl;
using std::equal;
using std::move;
using std::queue;
using std::unique_ptr;
using std::vector;

vector<vector<int>> results;
vector<int> one_line_result;

// @include
void PrintBinaryTreeDepthOrder(const unique_ptr<BinaryTreeNode<int>>& root) {
  queue<BinaryTreeNode<int>*> processing_nodes;
  processing_nodes.emplace(root.get());
  size_t num_nodes_current_level = processing_nodes.size();
  while (!processing_nodes.empty()) {
    auto curr = processing_nodes.front();
    processing_nodes.pop();
    --num_nodes_current_level;
    if (!curr) {
      continue;
    }
    cout << curr->data << ' ';
    // @exclude
    one_line_result.emplace_back(curr->data);
    // @include

    // Defer the null checks to the null test above.
    processing_nodes.emplace(curr->left.get());
    processing_nodes.emplace(curr->right.get());
    // Done with the nodes at the current depth.
    if (!num_nodes_current_level) {
      cout << endl;
      num_nodes_current_level = processing_nodes.size();
      // @exclude
      results.emplace_back(move(one_line_result));
      // @include
    }
  }
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
  //               2 5
  //               1 4 6
  PrintBinaryTreeDepthOrder(root);
  vector<vector<int>> golden_res = {{3}, {2, 5}, {1, 4, 6}};
  assert(golden_res.size() == results.size() &&
         equal(golden_res.begin(), golden_res.end(), results.begin()));
  return 0;
}
