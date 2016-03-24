// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <limits>
#include <memory>
#include <vector>

#include "./BST_prototype.h"

using std::cout;
using std::endl;
using std::make_unique;
using std::numeric_limits;
using std::unique_ptr;
using std::vector;

unique_ptr<BSTNode<int>> RebuildBSTFromPreorderOnValueRange(
    const vector<int>&, int, int, int*);

// @include
unique_ptr<BSTNode<int>> RebuildBSTFromPreorder(
    const vector<int>& preorder_sequence) {
  int root_idx = 0;
  return RebuildBSTFromPreorderOnValueRange(
      preorder_sequence, numeric_limits<int>::min(),
      numeric_limits<int>::max(), &root_idx);
}

// Builds a BST on the subtree rooted at root_idx from preorder_sequence on
// keys in (lower_bound, upper_bound).
unique_ptr<BSTNode<int>> RebuildBSTFromPreorderOnValueRange(
    const vector<int>& preorder_sequence, int lower_bound, int upper_bound,
    int* root_idx_pointer) {
  int& root_idx = *root_idx_pointer;
  if (root_idx == preorder_sequence.size()) {
    return nullptr;
  }

  int root = preorder_sequence[root_idx];
  if (root < lower_bound || root > upper_bound) {
    return nullptr;
  }
  ++root_idx;
  // Note that RebuildBSTFromPreorderOnValueRange updates root_idx. So the
  // order of following two calls are critical.
  auto left_subtree = RebuildBSTFromPreorderOnValueRange(
      preorder_sequence, lower_bound, root, root_idx_pointer);
  auto right_subtree = RebuildBSTFromPreorderOnValueRange(
      preorder_sequence, root, upper_bound, root_idx_pointer);
  return make_unique<BSTNode<int>>(
      BSTNode<int>{root, move(left_subtree), move(right_subtree)});
}
// @exclude

template <typename T>
void CheckAns(const unique_ptr<BSTNode<T>>& n, const T& pre) {
  if (n) {
    CheckAns(n->left, pre);
    assert(pre <= n->data);
    cout << n->data << endl;
    CheckAns(n->right, n->data);
  }
}

int main(int argc, char* argv[]) {
  //      3
  //    2   5
  //  1    4  6
  // should output 1, 2, 3, 4, 5, 6
  // preorder [3, 2, 1, 5, 4, 6]
  vector<int> preorder = {3, 2, 1, 5, 4, 6};
  unique_ptr<BSTNode<int>> tree(RebuildBSTFromPreorder(preorder));
  CheckAns<int>(tree, numeric_limits<int>::min());
  assert(3 == tree->data);
  assert(2 == tree->left->data);
  assert(1 == tree->left->left->data);
  assert(5 == tree->right->data);
  assert(4 == tree->right->left->data);
  assert(6 == tree->right->right->data);
  return 0;
}
