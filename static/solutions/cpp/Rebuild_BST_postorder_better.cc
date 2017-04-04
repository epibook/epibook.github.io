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

unique_ptr<BSTNode<int>> RebuildBSTFromPostorderHelper(const vector<int>&,
                                                       int, int, int*);

// @include
unique_ptr<BSTNode<int>> RebuildBSTFromPostorder(
    const vector<int>& postorder_sequence) {
  int root_idx = postorder_sequence.size() - 1;
  return RebuildBSTFromPostorderHelper(postorder_sequence,
                                       numeric_limits<int>::min(),
                                       numeric_limits<int>::max(), &root_idx);
}

// Builds a BST from postorder_sequence on keys in (lower_bound :
// upper_bound).
unique_ptr<BSTNode<int>> RebuildBSTFromPostorderHelper(
    const vector<int>& postorder_sequence, int lower_bound, int upper_bound,
    int* root_idx) {
  if (*root_idx < 0) {
    return nullptr;
  }

  int root = postorder_sequence[*root_idx];
  if (root < lower_bound || root > upper_bound) {
    return nullptr;
  }
  --*root_idx;
  auto right_subtree = RebuildBSTFromPostorderHelper(postorder_sequence, root,
                                                     upper_bound, root_idx);
  auto left_subtree = RebuildBSTFromPostorderHelper(
      postorder_sequence, lower_bound, root, root_idx);
  return make_unique<BSTNode<int>>(
      BSTNode<int>{root, move(left_subtree), move(right_subtree)});
}
// @exclude

template <typename T>
void CheckAns(const unique_ptr<BSTNode<T>>& n, int pre) {
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
  // preorder [1, 2, 4, 6, 5, 3]
  vector<int> postorder = {1, 2, 4, 6, 5, 3};
  unique_ptr<BSTNode<int>> root(RebuildBSTFromPostorder(postorder));
  CheckAns(root, numeric_limits<int>::min());
  return 0;
}
