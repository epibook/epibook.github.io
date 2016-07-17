// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <memory>

#include "Postings_list_prototype.h"

using std::make_shared;
using std::shared_ptr;

void SetJumpOrderHelper(const shared_ptr<PostingListNode>& L, int* order);

// @include
void SetJumpOrder(const shared_ptr<PostingListNode>& L) {
  SetJumpOrderHelper(L, new int(0));
}

void SetJumpOrderHelper(const shared_ptr<PostingListNode>& L, int* order) {
  if (L && L->order == -1) {
    L->order = (*order)++;
    SetJumpOrderHelper(L->jump, order);
    SetJumpOrderHelper(L->next, order);
  }
}
// @exclude

int main(int argc, char* argv[]) {
  shared_ptr<PostingListNode> L = nullptr, curr;
  curr = L;
  // build a linked list L->1->2->3->4->5->nullptr
  for (size_t i = 0; i < 5; ++i) {
    shared_ptr<PostingListNode> temp =
        make_shared<PostingListNode>(PostingListNode{-1, nullptr, nullptr});
    if (curr) {
      curr->next = temp;
      curr = temp;
    } else {
      curr = L = temp;
    }
  }
  L->jump = nullptr;  // no jump from 1
  L->next->jump = L->next->next->next;  // 2's jump points to 4
  L->next->next->jump = L;  // 3's jump points to 1
  L->next->next->next->jump = nullptr;  // no jump from 4
  L->next->next->next->next->jump =
      L->next->next->next->next;  // 5's jump points to 5
  shared_ptr<PostingListNode> temp = L;
  SetJumpOrder(L);
  // output the jump-first order, it should be 0, 1, 4, 2, 3
  assert(temp->order == 0);
  temp = temp->next;
  assert(temp->order == 1);
  temp = temp->next;
  assert(temp->order == 4);
  temp = temp->next;
  assert(temp->order == 2);
  temp = temp->next;
  assert(temp->order == 3);
  return 0;
}
