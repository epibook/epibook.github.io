// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#ifndef SOLUTIONS_CPP_POSTINGS_LIST_PROTOTYPE_H_
#define SOLUTIONS_CPP_POSTINGS_LIST_PROTOTYPE_H_

#include <memory>

using std::shared_ptr;

// @include
class PostingListNode {
 public:
  int order;
  shared_ptr<PostingListNode> next, jump;
};
// @exclude
#endif  // SOLUTIONS_CPP_POSTINGS_LIST_PROTOTYPE_H_
