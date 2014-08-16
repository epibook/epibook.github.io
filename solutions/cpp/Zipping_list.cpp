// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <memory>
#include <random>
#include <string>

#include "./Linked_list_prototype.h"
#include "./Reverse_linked_list_iterative.h"

using std::cout;
using std::default_random_engine;
using std::endl;
using std::make_shared;
using std::random_device;
using std::shared_ptr;
using std::stoi;
using std::uniform_int_distribution;

// @include
shared_ptr<ListNode<int>> ZippingLinkedList(
    const shared_ptr<ListNode<int>>& L) {
  if (!L || !L->next) {
    return L;
  }

  // Finds the middle point of L.
  auto slow = L, fast = L;
  while (fast && fast->next) {
    fast = fast->next->next, slow = slow->next;
  }

  auto reverse_head = slow->next;
  slow->next = nullptr;  // Splits the list into two lists.
  reverse_head = ReverseLinkedList(reverse_head);

  // Zips the list.
  auto curr = L;
  while (reverse_head) {
    auto temp = reverse_head->next;
    reverse_head->next = curr->next;
    curr->next = reverse_head;
    curr = curr->next->next;
    reverse_head = temp;
  }
  return L;
}
// @exclude

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  shared_ptr<ListNode<int>> head = nullptr;
  int n;
  if (argc > 2) {
    for (int i = 1; i < argc; ++i) {
      auto curr =
          make_shared<ListNode<int>>(ListNode<int>{stoi(argv[i]), nullptr});
      curr->next = head;
      head = curr;
    }
  } else {
    if (argc == 2) {
      n = stoi(argv[1]);
    } else {
      uniform_int_distribution<int> dis(1, 1000);
      n = dis(gen);
    }
    for (int i = n; i >= 0; --i) {
      auto curr = make_shared<ListNode<int>>(ListNode<int>{i, nullptr});
      curr->next = head;
      head = curr;
    }
  }
  shared_ptr<ListNode<int>> curr = ZippingLinkedList(head);
  int idx = 0, pre;
  while (curr) {
    if (argc <= 2) {
      if (idx & 1) {
        assert(pre + curr->data == n);
      }
    }
    ++idx;
    cout << curr->data << endl;
    pre = curr->data;
    curr = curr->next;
  }
  return 0;
}
