// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#ifndef SOLUTIONS_POSTINGS_LIST_PROTOTYPE_H_
#define SOLUTIONS_POSTINGS_LIST_PROTOTYPE_H_

#include <memory>

using std::shared_ptr;

// @include
template <typename T>
class ListNode {
  public:
    T data;
    shared_ptr<ListNode<T>> next, jump;
};
// @exclude
#endif  // SOLUTIONS_POSTINGS_LIST_PROTOTYPE_H_
