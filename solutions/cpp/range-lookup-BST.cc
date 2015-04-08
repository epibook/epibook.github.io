// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <memory>
#include <vector>

#include "./BST_prototype.h"

using std::pair;
using std::unique_ptr;
using std::vector;

// @include
vector<int> RangeLookupInBST(const unique_ptr<BSTNode<int>>& tree,
                             const pair<int, int>& interval) {
  if (tree == nullptr) {
    return {};
  }

  if (interval.first <= tree->data && tree->data <= interval.second) {
    // tree->data lies in the interval.
    vector<int> result;
    vector<int> left_result = RangeLookupInBST(tree->left, interval);
    result.insert(result.end(), left_result.begin(), left_result.end());
    result.emplace_back(tree->data);
    vector<int> right_result = RangeLookupInBST(tree->right, interval);
    result.insert(result.end(), right_result.begin(), right_result.end());
    return result;
  } else if (interval.first > tree->data) {
    return RangeLookupInBST(tree->right, interval);
  } else {  // interval.second > tree->data
    return RangeLookupInBST(tree->left, interval);
  }
}
// @exclude

template <typename T>
bool EqualVector(const vector<T>& A, const vector<T>& B) {
  return A.size() == B.size() && equal(A.begin(), A.end(), B.begin());
}

int main(int argc, char* argv[]) {
  //          19
  //     7          43
  //   3   11    23   47
  // 2  5    17   37    53
  //        13  29  41
  //             31
  unique_ptr<BSTNode<int>> tree = unique_ptr<BSTNode<int>>(new BSTNode<int>{19});
  tree->left = unique_ptr<BSTNode<int>>(new BSTNode<int>{7});
  tree->left->left = unique_ptr<BSTNode<int>>(new BSTNode<int>{3});
  tree->left->left->left = unique_ptr<BSTNode<int>>(new BSTNode<int>{2});
  tree->left->left->right = unique_ptr<BSTNode<int>>(new BSTNode<int>{5});
  tree->left->right = unique_ptr<BSTNode<int>>(new BSTNode<int>{11});
  tree->left->right->right = unique_ptr<BSTNode<int>>(new BSTNode<int>{17});
  tree->left->right->right->left = unique_ptr<BSTNode<int>>(new BSTNode<int>{13});
  tree->right = unique_ptr<BSTNode<int>>(new BSTNode<int>{43});
  tree->right->left = unique_ptr<BSTNode<int>>(new BSTNode<int>{23});
  tree->right->left->right = unique_ptr<BSTNode<int>>(new BSTNode<int>{37});
  tree->right->left->right->left = unique_ptr<BSTNode<int>>(new BSTNode<int>{29});
  tree->right->left->right->left->right = unique_ptr<BSTNode<int>>(new BSTNode<int>{31});
  tree->right->left->right->right = unique_ptr<BSTNode<int>>(new BSTNode<int>{41});
  tree->right->right = unique_ptr<BSTNode<int>>(new BSTNode<int>{47});
  tree->right->right->right = unique_ptr<BSTNode<int>>(new BSTNode<int>{53});
  auto result = RangeLookupInBST(tree, {16, 31});
  sort(result.begin(), result.end());
  vector<int> golden_result = {17, 19, 23, 29, 31};
  assert(EqualVector(result, golden_result));
  return 0;
}
