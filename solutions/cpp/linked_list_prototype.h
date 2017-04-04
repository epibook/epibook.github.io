// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#ifndef SOLUTIONS_CPP_LINKED_LIST_PROTOTYPE_H_
#define SOLUTIONS_CPP_LINKED_LIST_PROTOTYPE_H_

#include <memory>

using std::shared_ptr;

// @include
template <typename T>
struct ListNode {
  T data;

  // We need to use a shared pointer since multiple nodes may point to a
  // single node. For example, in the following list, two nodes, 1 and 4,
  // point to node 2.
  // 0->1->2->3->4
  //       ^     |
  //       |_____|
  shared_ptr<ListNode<T>> next;
};
// @exclude
#endif  // SOLUTIONS_CPP_LINKED_LIST_PROTOTYPE_H_
