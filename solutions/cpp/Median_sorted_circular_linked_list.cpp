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
    const shared_ptr<ListNode<int>>& random_pointer) {
  // Checks all nodes are identical or not and identify the start of list.
  auto curr = random_pointer, start = random_pointer;
  int count = 0;
  do {
    ++count, curr = curr->next;
    // start will point to the largest element in the list.
    if (start->data <= curr->data) {
      start = curr;
    }
  } while (curr != random_pointer);
  // start's next is the begin of the list.
  start = start->next;

  // Traverses to the middle of the list and returns the median.
  for (int i = 0; i < ((count - 1) / 2); ++i) {
    start = start->next;
  }
  return count & 1 ? start->data : 0.5 * (start->data + start->next->data);
}
// @exclude

int main(int argc, char* argv[]) {
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
