// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

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

vector<int> result;

// @include
void PrintBSTInSortedOrder(const unique_ptr<BSTNode<int>>& r) {
  stack<const BSTNode<int>*> s;
  const BSTNode<int>* curr = r.get();

  while (!s.empty() || curr) {
    if (curr) {
      s.push(curr);
      curr = curr->left.get();
    } else {
      curr = s.top();
      s.pop();
      cout << curr->data << endl;
      // @exclude
      result.emplace_back(curr->data);
      // @include
      curr = curr->right.get();
    }
  }
}
// @exclude

int main(int argc, char* argv[]) {
  //      3
  //    2   5
  //  1    4 6
  unique_ptr<BSTNode<int>> root =
      unique_ptr<BSTNode<int>>(new BSTNode<int>{3, nullptr});
  root->left = unique_ptr<BSTNode<int>>(new BSTNode<int>{2, nullptr});
  root->left->left = unique_ptr<BSTNode<int>>(new BSTNode<int>{1, nullptr});
  root->right = unique_ptr<BSTNode<int>>(new BSTNode<int>{5, nullptr});
  root->right->left = unique_ptr<BSTNode<int>>(new BSTNode<int>{4, nullptr});
  root->right->right = unique_ptr<BSTNode<int>>(new BSTNode<int>{6, nullptr});
  // should output 1 2 3 4 5 6
  PrintBSTInSortedOrder(root);
  vector<int> golden_res = {1, 2, 3, 4, 5, 6};
  assert(golden_res.size() == result.size());
  assert(equal(golden_res.begin(), golden_res.end(), result.begin()));
  return 0;
}
