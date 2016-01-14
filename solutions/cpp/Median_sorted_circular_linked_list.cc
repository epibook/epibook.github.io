// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <memory>
#include <random>
#include <stdexcept>

#include "./Linked_list_prototype.h"

using std::cout;
using std::default_random_engine;
using std::endl;
using std::exception;
using std::length_error;
using std::make_shared;
using std::random_device;
using std::uniform_int_distribution;

// @include
double FindMedianSortedCircularLinkedList(
    const shared_ptr<ListNode<int>>& arbitrary_node) {
  // Checks if all nodes are identical and identifies the first smallest node.
  auto iter = arbitrary_node, first_smallest_node = arbitrary_node;
  int n = 0;
  do {
    if (iter->data > iter->next->data) {
      first_smallest_node = iter->next;
    }
    ++n, iter = iter->next;
  } while (iter != arbitrary_node);

  // Advances to the middle of the list.
  for (int i = 0; i < ((n - 1) / 2); ++i) {
    first_smallest_node = first_smallest_node->next;
  }
  return n % 2 ? first_smallest_node->data
               : 0.5 * (first_smallest_node->data +
                        first_smallest_node->next->data);
}
// @exclude

void SmallTest() {
  shared_ptr<ListNode<int>> L = make_shared<ListNode<int>>(
      ListNode<int>{0, make_shared<ListNode<int>>(ListNode<int>{
                           2, make_shared<ListNode<int>>(ListNode<int>{
                                  2, make_shared<ListNode<int>>(
                                         ListNode<int>{2, nullptr})})})});
  L->next->next->next->next = L;
  cout << FindMedianSortedCircularLinkedList(L->next->next) << endl;
  assert(2 == FindMedianSortedCircularLinkedList(L->next->next));
}

int main(int argc, char* argv[]) {
  SmallTest();
  default_random_engine gen((random_device())());
  for (int times = 0; times < 1000; ++times) {
    int n;
    if (argc == 2) {
      n = atoi(argv[1]);
    } else {
      uniform_int_distribution<int> dis(1, 1000);
      n = dis(gen);
    }
    shared_ptr<ListNode<int>> head;
    for (int i = n; i >= 0; --i) {
      auto curr = make_shared<ListNode<int>>(ListNode<int>{i, nullptr});
      curr->next = head;
      head = curr;
    }
    shared_ptr<ListNode<int>> curr = head;
    if (curr != shared_ptr<ListNode<int>>(nullptr)) {
      while (curr->next != shared_ptr<ListNode<int>>(nullptr)) {
        curr = curr->next;
      }
      curr->next = head;  // make the list as a circular list.
    }
    double res = FindMedianSortedCircularLinkedList(head->next);
    cout << res << endl;
    assert(res == 0.5 * n);
  }

  // Test identical list.
  shared_ptr<ListNode<int>> head;
  for (int i = 0; i < 10; ++i) {
    auto curr = make_shared<ListNode<int>>(ListNode<int>{5, nullptr});
    curr->next = head;
    head = curr;
  }
  shared_ptr<ListNode<int>> curr = head;
  if (curr != shared_ptr<ListNode<int>>(nullptr)) {
    while (curr->next != nullptr) {
      curr = curr->next;
    }
    curr->next = head;  // make the list as a circular list.
  }
  assert(5 == FindMedianSortedCircularLinkedList(head));
  return 0;
}
