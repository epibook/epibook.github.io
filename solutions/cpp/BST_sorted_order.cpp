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
void PrintBSTInSortedOrder(const unique_ptr<BSTNode<int>>& root) {
  stack<const BSTNode<int>*> s;
  const auto* curr = root.get();

  while (!s.empty() || curr) {
    if (curr) {
      s.push(curr);
      // @exclude
      stack<const BSTNode<int>*> copy_s(s);
      while (!copy_s.empty()) {
        cout << copy_s.top()->data << ' ';
        copy_s.pop();
      }
      cout << endl;
      // @include
      // Going left.
      curr = curr->left.get();
    } else {  
      // Going up.
      curr = s.top();
      s.pop();
      // @exclude
      stack<const BSTNode<int>*> copy_s(s);
      while (!copy_s.empty()) {
        cout << copy_s.top()->data << ' ';
        copy_s.pop();
      }
      cout << endl;
      // @include
      cout << curr->data << endl;
      // @exclude
      result.emplace_back(curr->data);
      // @include
      // Going right.
      curr = curr->right.get();
    }
  }
}
// @exclude

int main(int argc, char* argv[]) {
  //        43
  //    23     47
  //      37      53
  //    29  41
  //     31
  unique_ptr<BSTNode<int>> root =
      unique_ptr<BSTNode<int>>(new BSTNode<int>{43, nullptr});
  root->left = unique_ptr<BSTNode<int>>(new BSTNode<int>{23, nullptr});
  root->left->right = unique_ptr<BSTNode<int>>(new BSTNode<int>{37, nullptr});
  root->left->right->left = unique_ptr<BSTNode<int>>(new BSTNode<int>{29, nullptr});
  root->left->right->left->right = unique_ptr<BSTNode<int>>(new BSTNode<int>{31, nullptr});
  root->left->right->right = unique_ptr<BSTNode<int>>(new BSTNode<int>{41, nullptr});
  root->right = unique_ptr<BSTNode<int>>(new BSTNode<int>{47, nullptr});
  root->right->right = unique_ptr<BSTNode<int>>(new BSTNode<int>{53, nullptr});
  PrintBSTInSortedOrder(root);
  vector<int> golden_res = {23, 29, 31, 37, 41, 43, 47, 53};
  assert(golden_res.size() == result.size());
  assert(equal(golden_res.begin(), golden_res.end(), result.begin()));
  return 0;
}
