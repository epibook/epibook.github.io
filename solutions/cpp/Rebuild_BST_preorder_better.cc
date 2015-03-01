// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <limits>
#include <memory>
#include <vector>

#include "./BST_prototype.h"

using std::cout;
using std::endl;
using std::numeric_limits;
using std::unique_ptr;
using std::vector;

unique_ptr<BSTNode<int>> RebuildBSTFromPreorderHelper(
    const vector<int>&, int, int, int*);

// @include
unique_ptr<BSTNode<int>> RebuildBSTFromPreorder(
    const vector<int>& preorder_sequence) {
  int idx = 0;
  return RebuildBSTFromPreorderHelper(
      preorder_sequence, numeric_limits<int>::min(),
      numeric_limits<int>::max(), &idx);
}

unique_ptr<BSTNode<int>> RebuildBSTFromPreorderHelper(
    const vector<int>& preorder_sequence, int min, int max, int* idx) {
  if (*idx == preorder_sequence.size()) {
    return nullptr;
  }

  int curr = preorder_sequence[*idx];
  if (curr < min || curr > max) {
    return nullptr;
  }
  ++*idx;
  return unique_ptr<BSTNode<int>>(new BSTNode<int>{
      curr, RebuildBSTFromPreorderHelper(preorder_sequence, min, curr, idx),
      RebuildBSTFromPreorderHelper(preorder_sequence, curr, max, idx)});
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
  vector<int> preorder;
  preorder.emplace_back(3);
  preorder.emplace_back(2);
  preorder.emplace_back(1);
  preorder.emplace_back(5);
  preorder.emplace_back(4);
  preorder.emplace_back(6);
  unique_ptr<BSTNode<int>> tree(RebuildBSTFromPreorder(preorder));
  CheckAns<int>(tree, numeric_limits<int>::min());
  return 0;
}
