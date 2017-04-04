// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#ifndef SOLUTIONS_CPP_BINARY_TREE_UTILS_H_
#define SOLUTIONS_CPP_BINARY_TREE_UTILS_H_

#include <limits>
#include <list>
#include <memory>
#include <random>
#include <string>
#include <vector>

#include "./Binary_tree_prototype.h"

using std::default_random_engine;
using std::list;
using std::make_unique;
using std::numeric_limits;
using std::random_device;
using std::unique_ptr;
using std::string;
using std::uniform_int_distribution;
using std::vector;

template <typename T>
unique_ptr<BinaryTreeNode<T>> GenerateRandBinaryTree(int n,
                                                     bool is_unique = false) {
  if (!n) {
    return nullptr;
  }
  default_random_engine gen((random_device())());
  list<unique_ptr<BinaryTreeNode<T>>*> l;
  uniform_int_distribution<int> dis(0, n);
  auto root = make_unique<BinaryTreeNode<T>>(
      BinaryTreeNode<T>{(is_unique ? n-- : dis(gen))});
  l.emplace_back(&(root->left));
  l.emplace_back(&(root->right));
  while (n--) {
    uniform_int_distribution<int> x_dis(0, l.size() - 1);
    int x = x_dis(gen);
    typename list<unique_ptr<BinaryTreeNode<T>>*>::iterator it = l.begin();
    advance(it, x);
    **it = make_unique<BinaryTreeNode<T>>(
        BinaryTreeNode<T>{(is_unique ? n : dis(gen))});
    l.emplace_back(&((**it)->left));
    l.emplace_back(&((**it)->right));
    l.erase(it);
  }
  return root;
}

template <typename T>
void DeleteBinaryTree(unique_ptr<BinaryTreeNode<T>>* n) {
  if (n && *n) {
    if ((*n)->left.get()) {
      DeleteBinaryTree(&((*n)->left));
    }
    if ((*n)->right.get()) {
      DeleteBinaryTree(&((*n)->right));
    }
    n->reset(nullptr);
  }
}

template <typename T>
bool IsTwoBinaryTreesEqual(const unique_ptr<BinaryTreeNode<T>>& r1,
                           const unique_ptr<BinaryTreeNode<T>>& r2) {
  if (r1 && r2) {
    return IsTwoBinaryTreesEqual(r1->left, r2->left) &&
           IsTwoBinaryTreesEqual(r1->right, r2->right) &&
           r1->data == r2->data;
  } else if (!r1 && !r2) {
    return true;
  } else {
    return false;
  }
}

template <typename T>
void GeneratePreorderHelper(const unique_ptr<BinaryTreeNode<T>>& r,
                            vector<T>* ret) {
  if (r) {
    ret->emplace_back(r->data);
    GeneratePreorderHelper(r->left, ret);
    GeneratePreorderHelper(r->right, ret);
  }
}

template <typename T>
vector<T> GeneratePreorder(const unique_ptr<BinaryTreeNode<T>>& r) {
  vector<T> ret;
  GeneratePreorderHelper(r, &ret);
  return ret;
}

template <typename T>
void GenerateInorderHelper(const unique_ptr<BinaryTreeNode<T>>& r,
                           vector<T>* ret) {
  if (r) {
    GenerateInorderHelper(r->left, ret);
    ret->emplace_back(r->data);
    GenerateInorderHelper(r->right, ret);
  }
}

template <typename T>
vector<T> GenerateInorder(const unique_ptr<BinaryTreeNode<T>>& r) {
  vector<T> ret;
  GenerateInorderHelper(r, &ret);
  return ret;
}

template <typename T>
void GeneratePostorderHelper(const unique_ptr<BinaryTreeNode<T>>& r,
                             vector<T>* ret) {
  if (r) {
    GeneratePostorderHelper(r->left, ret);
    GeneratePostorderHelper(r->right, ret);
    ret->emplace_back(r->data);
  }
}

template <typename T>
vector<T> GeneratePostorder(const unique_ptr<BinaryTreeNode<T>>& r) {
  vector<T> ret;
  GeneratePostorderHelper(r, &ret);
  return ret;
}
#endif  // SOLUTIONS_CPP_BINARY_TREE_UTILS_H_
