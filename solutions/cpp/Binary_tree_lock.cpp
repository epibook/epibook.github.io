// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

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

  void Lock() {
    if (numChildreLocks_ == 0 && !locked_) {
      // Make sure all parents do not lock.
      shared_ptr<BinaryTreeNode> n = parent_;
      while (n) {
        if (n->locked_) {
          return;
        }
        n = n->parent_;
      }

      // Lock itself and update its parents.
      locked_ = true;
      n = parent_;
      while (n) {
        ++n->numChildreLocks_;
        n = n->parent_;
      }
    }
  }

  void Unlock() {
    if (locked_) {
      // Unlock itself and update its parents.
      locked_ = false;
      shared_ptr<BinaryTreeNode> n = parent_;
      while (n) {
        --n->numChildreLocks_;
        n = n->parent_;
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

  bool locked_;
  int numChildreLocks_;
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
  // Should output false.
  assert(!root->IsLocked());
  cout << boolalpha << root->IsLocked() << endl;
  root->Lock();
  // Should output true.
  assert(root->IsLocked());
  cout << boolalpha << root->IsLocked() << endl;
  root->Unlock();
  root->left()->Lock();
  root->Lock();
  // Should output false.
  assert(!root->IsLocked());
  cout << boolalpha << root->IsLocked() << endl;
  root->right()->Lock();
  // Should output true.
  assert(root->right()->IsLocked());
  cout << boolalpha << root->right()->IsLocked() << endl;
  return 0;
}
