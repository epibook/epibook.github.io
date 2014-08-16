// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <memory>
#include <random>

#include "./Linked_list_prototype.h"

using std::cout;
using std::default_random_engine;
using std::endl;
using std::make_shared;
using std::random_device;
using std::shared_ptr;
using std::uniform_int_distribution;

// @include
shared_ptr<ListNode<int>> EvenOddMerge(const shared_ptr<ListNode<int>>& L) {
  if (!L) {
    return L;
  }

  auto odd_first = L->next, odd_curr = L->next;
  shared_ptr<ListNode<int>> pre_even_curr = nullptr, even_curr = L;

  while (even_curr && odd_curr) {
    even_curr->next = odd_curr->next;
    pre_even_curr = even_curr;
    even_curr = even_curr->next;
    if (even_curr) {
      odd_curr->next = even_curr->next;
      odd_curr = odd_curr->next;
    }
  }

  if (even_curr) {
    even_curr->next = odd_first;
  } else {
    pre_even_curr->next = odd_first;
  }
  return L;
}
// @exclude

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  shared_ptr<ListNode<int>> head = nullptr;
  int n;
  if (argc == 2) {
    n = atoi(argv[1]);
  } else {
    uniform_int_distribution<int> dis(1, 1000);
    n = dis(gen);
  }
  for (int i = n - 1; i >= 0; --i) {
    auto curr = make_shared<ListNode<int>>(ListNode<int>{i, nullptr});
    curr->next = head;
    head = curr;
  }
  shared_ptr<ListNode<int>> ans = EvenOddMerge(head);
  int x = 0, count = 0;
  while (ans) {
    ++count;
    assert(x == ans->data);
    x += 2;
    if (x >= n) {
      x = 1;
    }
    cout << ans->data << endl;
    ans = ans->next;
  }
  assert(count == n);
  return 0;
}
