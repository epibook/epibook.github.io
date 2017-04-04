// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <array>
#include <cassert>
#include <iostream>
#include <memory>
#include <random>

#include "./Linked_list_prototype.h"

using std::array;
using std::cout;
using std::default_random_engine;
using std::endl;
using std::make_shared;
using std::random_device;
using std::shared_ptr;
using std::uniform_int_distribution;

shared_ptr<ListNode<int>> EvenOddMergeOriginal(
    const shared_ptr<ListNode<int>>& L) {
  if (!L) {
    return L;
  }

  auto even_list_head = L, even_list_iter = even_list_head;
  shared_ptr<ListNode<int>> predecessor_even_list_iter = nullptr;
  auto odd_list_head = L->next, odd_list_iter = odd_list_head;

  while (even_list_iter && odd_list_iter) {
    even_list_iter->next = odd_list_iter->next;
    predecessor_even_list_iter = even_list_iter;
    even_list_iter = even_list_iter->next;
    if (even_list_iter) {
      odd_list_iter->next = even_list_iter->next;
      odd_list_iter = odd_list_iter->next;
    }
  }

  // Appends odd list to the tail of even list.
  if (even_list_iter) {
    even_list_iter->next = odd_list_head;
  } else {
    predecessor_even_list_iter->next = odd_list_head;
  }
  return even_list_head;
}

// @include
shared_ptr<ListNode<int>> EvenOddMerge(const shared_ptr<ListNode<int>>& L) {
  if (L == nullptr) {
    return L;
  }

  auto even_dummy_head =
           make_shared<ListNode<int>>(ListNode<int>{0, nullptr}),
       odd_dummy_head = make_shared<ListNode<int>>(ListNode<int>{0, nullptr});
  array<shared_ptr<ListNode<int>>, 2> tails = {even_dummy_head,
                                               odd_dummy_head};
  int turn = 0;
  for (auto iter = L; iter; iter = iter->next) {
    tails[turn]->next = iter;
    tails[turn] = tails[turn]->next;
    turn ^= 1;  // Alternate between even and odd.
  }
  tails[1]->next = nullptr;
  tails[0]->next = odd_dummy_head->next;
  return even_dummy_head->next;
}
// @exclude

void SimpleTest() {
  auto L = make_shared<ListNode<int>>(ListNode<int>{0, nullptr});
  auto result = EvenOddMerge(L);
  assert(result->data == 0);
  assert(result->next == nullptr);

  L->next = make_shared<ListNode<int>>(ListNode<int>{1, nullptr});
  result = EvenOddMerge(L);
  assert(result->data == 0);
  assert(result->next->data == 1);
  assert(result->next->next == nullptr);

  L->next->next = make_shared<ListNode<int>>(ListNode<int>{2, nullptr});
  result = EvenOddMerge(L);
  assert(result->data == 0);
  assert(result->next->data == 2);
  assert(result->next->next->data == 1);
  assert(result->next->next->next == nullptr);
}

shared_ptr<ListNode<int>> CreateList(int n) {
  shared_ptr<ListNode<int>> head = nullptr;
  for (int i = n - 1; i >= 0; --i) {
    auto curr = make_shared<ListNode<int>>(ListNode<int>{i, nullptr});
    curr->next = head;
    head = curr;
  }
  return head;
}

void CheckAnswer(const shared_ptr<ListNode<int>>& L, int n) {
  int x = 0, count = 0;
  auto iter = L;
  while (iter) {
    ++count;
    assert(x == iter->data);
    x += 2;
    if (x >= n) {
      x = 1;
    }
    cout << iter->data << endl;
    iter = iter->next;
  }
  assert(count == n);
}

int main(int argc, char* argv[]) {
  SimpleTest();
  default_random_engine gen((random_device())());
  int n;
  if (argc == 2) {
    n = atoi(argv[1]);
  } else {
    uniform_int_distribution<int> dis(1, 1000);
    n = dis(gen);
  }
  shared_ptr<ListNode<int>> head = CreateList(n);
  CheckAnswer(EvenOddMerge(head), n);
  shared_ptr<ListNode<int>> another_head = CreateList(n);
  CheckAnswer(EvenOddMergeOriginal(another_head), n);
  return 0;
}
