// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <memory>
#include <stack>
#include <vector>

#include "./BST_prototype.h"

using std::cout;
using std::endl;
using std::stack;
using std::unique_ptr;
using std::vector;

// @include
vector<int> BSTInSortedOrder(const unique_ptr<BSTNode<int>>& tree) {
  stack<const BSTNode<int>*> s;
  const auto* curr = tree.get();
  vector<int> result;

  while (!s.empty() || curr) {
    if (curr) {
      s.push(curr);
      // Going left.
      curr = curr->left.get();
    } else {
      // Going up.
      curr = s.top();
      s.pop();
      result.emplace_back(curr->data);
      // Going right.
      curr = curr->right.get();
    }
  }
  return result;
}
// @exclude

int main(int argc, char* argv[]) {
  //        43
  //    23     47
  //      37      53
  //    29  41
  //     31
  unique_ptr<BSTNode<int>> tree =
      unique_ptr<BSTNode<int>>(new BSTNode<int>{43, nullptr});
  tree->left = unique_ptr<BSTNode<int>>(new BSTNode<int>{23, nullptr});
  tree->left->right = unique_ptr<BSTNode<int>>(new BSTNode<int>{37, nullptr});
  tree->left->right->left =
      unique_ptr<BSTNode<int>>(new BSTNode<int>{29, nullptr});
  tree->left->right->left->right =
      unique_ptr<BSTNode<int>>(new BSTNode<int>{31, nullptr});
  tree->left->right->right =
      unique_ptr<BSTNode<int>>(new BSTNode<int>{41, nullptr});
  tree->right = unique_ptr<BSTNode<int>>(new BSTNode<int>{47, nullptr});
  tree->right->right = unique_ptr<BSTNode<int>>(new BSTNode<int>{53, nullptr});
  auto result = BSTInSortedOrder(tree);
  vector<int> golden_res = {23, 29, 31, 37, 41, 43, 47, 53};
  assert(golden_res.size() == result.size() &&
         equal(golden_res.begin(), golden_res.end(), result.begin()));
  return 0;
}
