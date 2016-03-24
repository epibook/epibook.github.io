// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <memory>

using std::boolalpha;
using std::cout;
using std::endl;
using std::make_shared;
using std::shared_ptr;

// @include
class BinaryTreeNode {
 public:
  bool IsLocked() const { return locked_; }

  bool Lock() {
    // We cannot lock if any of this node's descendants are locked.
    if (numLockedDescendants_ > 0 || locked_) {
      return false;
    }

    // We cannot lock if any of this node's ancestors are locked.
    for (auto iter = parent_; iter != nullptr; iter = iter->parent_) {
      if (iter->locked_) {
        return false;
      }
    }

    // Lock this node and increments all its ancestors's descendant lock
    // counts.
    locked_ = true;
    for (auto iter = parent_; iter != nullptr; iter = iter->parent_) {
      ++iter->numLockedDescendants_;
    }
    return true;
  }

  void Unlock() {
    if (locked_) {
      // Unlocks itself and decrements its ancestors's descendant lock counts.
      locked_ = false;
      for (auto iter = parent_; iter != nullptr; iter = iter->parent_) {
        --iter->numLockedDescendants_;
      }
    }
  }

  // @exclude
  shared_ptr<BinaryTreeNode>& left() { return left_; }

  shared_ptr<BinaryTreeNode>& right() { return right_; }

  shared_ptr<BinaryTreeNode>& parent() { return parent_; }
  // @include
 private:
  shared_ptr<BinaryTreeNode> left_, right_, parent_;

  bool locked_ = false;
  int numLockedDescendants_ = 0;
};
// @exclude

int main(int argc, char* argv[]) {
  auto root = make_shared<BinaryTreeNode>(BinaryTreeNode());
  root->left() = make_shared<BinaryTreeNode>(BinaryTreeNode());
  root->left()->parent() = root;
  root->right() = make_shared<BinaryTreeNode>(BinaryTreeNode());
  root->right()->parent() = root;
  root->left()->left() = make_shared<BinaryTreeNode>(BinaryTreeNode());
  root->left()->left()->parent() = root->left();
  root->left()->right() = make_shared<BinaryTreeNode>(BinaryTreeNode());
  root->left()->right()->parent() = root->left();

  assert(!root->IsLocked());
  cout << boolalpha << root->IsLocked() << endl;

  assert(root->Lock());
  assert(root->IsLocked());
  cout << boolalpha << root->IsLocked() << endl;
  assert(!root->left()->Lock());
  assert(!root->left()->IsLocked());
  assert(!root->right()->Lock());
  assert(!root->right()->IsLocked());
  assert(!root->left()->left()->Lock());
  assert(!root->left()->left()->IsLocked());
  assert(!root->left()->right()->Lock());
  assert(!root->left()->right()->IsLocked());

  root->Unlock();
  assert(root->left()->Lock());
  assert(!root->Lock());
  assert(!root->left()->left()->Lock());
  assert(!root->IsLocked());

  cout << boolalpha << root->IsLocked() << endl;
  assert(root->right()->Lock());
  assert(root->right()->IsLocked());
  cout << boolalpha << root->right()->IsLocked() << endl;
  return 0;
}
