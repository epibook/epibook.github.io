// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <limits>
#include <memory>
#include <vector>

#include "./BST_prototype.h"

using std::make_unique;
using std::numeric_limits;
using std::unique_ptr;
using std::vector;

struct Interval;
void RangeLookupInBSTHelper(const unique_ptr<BSTNode<int>>&, const Interval&,
                            vector<int>*);

// @include
struct Interval {
  int left, right;
};

vector<int> RangeLookupInBST(const unique_ptr<BSTNode<int>>& tree,
                             const Interval& interval) {
  vector<int> result;
  RangeLookupInBSTHelper(tree, interval, &result);
  return result;
}

void RangeLookupInBSTHelper(const unique_ptr<BSTNode<int>>& tree,
                            const Interval& interval, vector<int>* result) {
  if (tree == nullptr) {
    return;
  }
  if (interval.left <= tree->data && tree->data <= interval.right) {
    // tree->data lies in the interval.
    RangeLookupInBSTHelper(tree->left, interval, result);
    result->emplace_back(tree->data);
    RangeLookupInBSTHelper(tree->right, interval, result);
  } else if (interval.left > tree->data) {
    RangeLookupInBSTHelper(tree->right, interval, result);
  } else {  // interval.right > tree->data
    RangeLookupInBSTHelper(tree->left, interval, result);
  }
}
// @exclude

int main(int argc, char* argv[]) {
  //          19
  //     7          43
  //   3   11    23   47
  // 2  5    17   37    53
  //        13  29  41
  //             31
  unique_ptr<BSTNode<int>> tree = make_unique<BSTNode<int>>(BSTNode<int>{19});
  tree->left = make_unique<BSTNode<int>>(BSTNode<int>{7});
  tree->left->left = make_unique<BSTNode<int>>(BSTNode<int>{3});
  tree->left->left->left = make_unique<BSTNode<int>>(BSTNode<int>{2});
  tree->left->left->right = make_unique<BSTNode<int>>(BSTNode<int>{5});
  tree->left->right = make_unique<BSTNode<int>>(BSTNode<int>{11});
  tree->left->right->right = make_unique<BSTNode<int>>(BSTNode<int>{17});
  tree->left->right->right->left =
      make_unique<BSTNode<int>>(BSTNode<int>{13});
  tree->right = make_unique<BSTNode<int>>(BSTNode<int>{43});
  tree->right->left = make_unique<BSTNode<int>>(BSTNode<int>{23});
  tree->right->left->right = make_unique<BSTNode<int>>(BSTNode<int>{37});
  tree->right->left->right->left =
      make_unique<BSTNode<int>>(BSTNode<int>{29});
  tree->right->left->right->left->right =
      make_unique<BSTNode<int>>(BSTNode<int>{31});
  tree->right->left->right->right =
      make_unique<BSTNode<int>>(BSTNode<int>{41});
  tree->right->right = make_unique<BSTNode<int>>(BSTNode<int>{47});
  tree->right->right->right = make_unique<BSTNode<int>>(BSTNode<int>{53});
  auto result = RangeLookupInBST(tree, {16, 31});
  sort(result.begin(), result.end());
  vector<int> golden_result = {17, 19, 23, 29, 31};
  assert(equal(result.begin(), result.end(), golden_result.begin(),
               golden_result.end()));

  result = RangeLookupInBST(tree, {38, 39});
  assert(0 == result.size());

  result = RangeLookupInBST(tree, {38, 42});
  assert((1 == result.size()) && (41 == result[0]));

  result = RangeLookupInBST(tree, {-1, 1});
  assert(0 == result.size());

  result = RangeLookupInBST(
      tree, {numeric_limits<int>::max() - 1, numeric_limits<int>::max()});
  assert(0 == result.size());

  return 0;
}
