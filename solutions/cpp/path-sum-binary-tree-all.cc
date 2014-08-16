// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <memory>
#include <vector>

#include "./Binary_tree_prototype.h"

using std::cout;
using std::endl;
using std::unique_ptr;
using std::vector;

void all_path_sum_helper(const unique_ptr<BinaryTreeNode<int>>& root, int sum,
                         vector<int>* path, vector<vector<int>>* result);

// @include
vector<vector<int>> all_path_sum(const unique_ptr<BinaryTreeNode<int>>& root,
                                 int sum) {
  vector<int> path;
  vector<vector<int>> result;
  all_path_sum_helper(root, sum, &path, &result);
  return result;
}

void all_path_sum_helper(const unique_ptr<BinaryTreeNode<int>>& root, int sum,
                         vector<int>* path, vector<vector<int>>* result) {
  if (root) {
    path->emplace_back(root->data), sum -= root->data;
    if (!root->left && !root->right) {
      if (sum == 0) {
        result->emplace_back(*path);
      }
    } else {
      all_path_sum_helper(root->left, sum, path, result);
      all_path_sum_helper(root->right, sum, path, result);
    }
    path->pop_back();
  }
}
// @exclude

int main(int argc, char** argv) {
  //      3
  //    2   5
  //  1    4 6
  unique_ptr<BinaryTreeNode<int>> root =
      unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>{3, nullptr, nullptr});
  root->left =
      unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>{2, nullptr, nullptr});
  root->left->left =
      unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>{1, nullptr, nullptr});
  root->right =
      unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>{5, nullptr, nullptr});
  root->right->left =
      unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>{4, nullptr, nullptr});
  root->right->right =
      unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>{6, nullptr, nullptr});
  auto result = all_path_sum(root, 6);
  for (const auto& a : result) {
    for (int v : a) {
      cout << v << " ";
    }
    cout << endl;
  }
  return 0;
}
