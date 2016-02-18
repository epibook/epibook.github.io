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

  // Finds the second half of L.
  auto slow = L, fast = L;
  while (fast && fast->next) {
    fast = fast->next->next, slow = slow->next;
  }

  auto first_half_head = L, second_half_head = slow->next;
  slow->next = nullptr;  // Splits the list into two lists.

  second_half_head = ReverseLinkedList(second_half_head);

  // Interleave the first half and the reversed of the second half.
  auto first_half_iter = first_half_head, second_half_iter = second_half_head;
  while (second_half_iter) {
    auto temp = second_half_iter->next;
    second_half_iter->next = first_half_iter->next;
    first_half_iter->next = second_half_iter;
    first_half_iter = first_half_iter->next->next;
    second_half_iter = temp;
  }
  return first_half_head;
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
      if (idx % 2) {
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
