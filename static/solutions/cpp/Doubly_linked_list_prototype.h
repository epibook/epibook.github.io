// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#ifndef SOLUTIONS_DOUBLY_LINKED_LIST_PROTOTYPE_H_
#define SOLUTIONS_DOUBLY_LINKED_LIST_PROTOTYPE_H_

#include <memory>

using std::shared_ptr;

// @include
template <typename T>
struct ListNode {
  T data;
  shared_ptr<ListNode<T>> prev, next;
};
// @exclude
#endif  // SOLUTIONS_DOUBLY_LINKED_LIST_PROTOTYPE_H_
