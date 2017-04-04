// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <limits>
#include <memory>
#include <string>
#include <vector>

#include "./BST_prototype.h"

using std::cout;
using std::endl;
using std::make_unique;
using std::numeric_limits;
using std::unique_ptr;
using std::vector;

unique_ptr<BSTNode<int>> RebuildBSTFromPreorderHelper(const vector<int>&, int,
                                                      int);

// @include
unique_ptr<BSTNode<int>> RebuildBSTFromPreorder(
    const vector<int>& preorder_sequence) {
  return RebuildBSTFromPreorderHelper(preorder_sequence, 0,
                                      preorder_sequence.size());
}

// Builds a BST from preorder_sequence[start : end - 1].
unique_ptr<BSTNode<int>> RebuildBSTFromPreorderHelper(
    const vector<int>& preorder_sequence, int start, int end) {
  if (start >= end) {
    return nullptr;
  }
  int transition_point = start + 1;
  while (transition_point < end &&
         preorder_sequence[transition_point] < preorder_sequence[start]) {
    ++transition_point;
  }
  return make_unique<BSTNode<int>>(
      BSTNode<int>{preorder_sequence[start],
                   RebuildBSTFromPreorderHelper(preorder_sequence, start + 1,
                                                transition_point),
                   RebuildBSTFromPreorderHelper(preorder_sequence,
                                                transition_point, end)});
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
  CheckAns(tree, numeric_limits<int>::min());
  assert(3 == tree->data);
  assert(2 == tree->left->data);
  assert(1 == tree->left->left->data);
  assert(5 == tree->right->data);
  assert(4 == tree->right->left->data);
  assert(6 == tree->right->right->data);
  return 0;
}
