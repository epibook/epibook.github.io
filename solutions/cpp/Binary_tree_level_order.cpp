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
using std::queue;
using std::unique_ptr;
using std::vector;

vector<vector<int>> results;
vector<int> one_line_result;

// @include
void PrintBinaryTreeDepthOrder(const unique_ptr<BinaryTreeNode<int>>& r) {
  // Prevents empty tree.
  if (!r) {
    return;
  }

  queue<BinaryTreeNode<int>*> q;
  q.emplace(r.get());
  size_t count = q.size();
  while (!q.empty()) {
    cout << q.front()->data << ' ';
    // @exclude
    one_line_result.emplace_back(q.front()->data);
    // @include
    if (q.front()->left) {
      q.emplace(q.front()->left.get());
    }
    if (q.front()->right) {
      q.emplace(q.front()->right.get());
    }
    q.pop();
    if (--count == 0) {  // Finish printing nodes in the current depth.
      cout << endl;
      count = q.size();
      // @exclude
      results.emplace_back(one_line_result);
      one_line_result.clear();
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
  assert(golden_res.size() == results.size() && equal(golden_res.begin(), golden_res.end(), results.begin()));
  return 0;
}
