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

unique_ptr<BSTNode<int>> RebuildBSTFromPostorderHelper(const vector<int>&,
                                                       int, int);

// @include
// Given a postorder traversal of a BST, return its root.
unique_ptr<BSTNode<int>> RebuildBSTFromPostorder(
    const vector<int>& postorder) {
  return RebuildBSTFromPostorderHelper(postorder, 0, postorder.size());
}

// Build a BST based on postorder[start : end - 1], return its root.
unique_ptr<BSTNode<int>> RebuildBSTFromPostorderHelper(
    const vector<int>& postorder, int start, int end) {
  if (start < end) {
    int x = start;
    while (x < end && postorder[x] < postorder[end - 1]) {
      ++x;
    }
    return make_unique<BSTNode<int>>(
        BSTNode<int>{postorder[end - 1],
                     RebuildBSTFromPostorderHelper(postorder, start, x),
                     RebuildBSTFromPostorderHelper(postorder, x, end - 1)});
  }
  return nullptr;
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
  // preorder [1, 2, 4, 6, 5, 3]
  vector<int> postorder = {1, 2, 4, 6, 5, 3};
  unique_ptr<BSTNode<int>> root(RebuildBSTFromPostorder(postorder));
  CheckAns(root, numeric_limits<int>::min());
  return 0;
}
