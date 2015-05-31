// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <memory>
#include <stack>

using std::make_shared;
using std::shared_ptr;
using std::stack;

template <typename T>
struct ListNode {
  T order;
  shared_ptr<ListNode<T>> next, jump;
};

// @include
void SearchPostingsList(const shared_ptr<ListNode<int>>& L) {
  stack<shared_ptr<ListNode<int>>> s;
  int order = 0;
  s.emplace(L);
  while (!s.empty()) {
    auto curr = s.top();
    s.pop();
    if (curr && curr->order == -1) {
      curr->order = order++;
      s.emplace(curr->next);
      s.emplace(curr->jump);
    }
  }
}
// @exclude

int main(int argc, char* argv[]) {
  shared_ptr<ListNode<int>> L = nullptr, curr;
  curr = L;
  // Build a linked list L->1->2->3->4->5->nullptr.
  for (size_t i = 0; i < 5; ++i) {
    shared_ptr<ListNode<int>> temp =
        make_shared<ListNode<int>>(ListNode<int>{-1, nullptr, nullptr});
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
  shared_ptr<ListNode<int>> temp = L;
  SearchPostingsList(L);
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
