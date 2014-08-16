// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <memory>
#include <stdexcept>
#include <string>

#include "./Linked_list_prototype.h"
#include "./Reverse_linked_list_iterative.h"

using std::cout;
using std::endl;
using std::exception;
using std::length_error;
using std::make_shared;
using std::shared_ptr;
using std::stoi;

// @include
shared_ptr<ListNode<int>> ReverseK(shared_ptr<ListNode<int>> L, int k) {
  auto dummy_head = make_shared<ListNode<int>>(ListNode<int>{0, L});
  shared_ptr<ListNode<int>> before_pre = dummy_head, pre = dummy_head->next,
                            before_post = dummy_head, post = dummy_head->next;
  while (pre) {
    int i = k;
    while (i) {
      before_post = post;
      post = post->next;
      --i;
      if (!post) {
        break;
      }
    }
    if (i) {
      return dummy_head->next;
    }

    before_post->next = nullptr;
    ReverseLinkedList(pre);
    before_pre->next = before_post;
    before_pre = pre, pre->next = post;
    pre = post, before_post = nullptr;
  }
  return dummy_head->next;
}
// @exclude

int main(int argc, char** argv) {
  shared_ptr<ListNode<int>> L;
  L = make_shared<ListNode<int>>(ListNode<int>{
      1, make_shared<ListNode<int>>(ListNode<int>{
             2, make_shared<ListNode<int>>(ListNode<int>{
                    3, make_shared<ListNode<int>>(ListNode<int>{
                           4, make_shared<ListNode<int>>(
                                  ListNode<int>{5, nullptr})})})})});
  int k;
  if (argc == 2) {
    k = stoi(argv[1]);
  } else {
    k = 2;
  }
  auto result = ReverseK(L, k);
  assert(result->data == 2 && result->next->data == 1 &&
         result->next->next->data == 4 &&
         result->next->next->next->data == 3 &&
         result->next->next->next->next->data == 5 &&
         result->next->next->next->next->next == nullptr);
  while (result) {
    cout << result->data << endl;
    result = result->next;
  }
  return 0;
}
