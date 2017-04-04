// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <memory>

#include "./Linked_list_prototype.h"

using std::cout;
using std::endl;
using std::make_shared;
using std::shared_ptr;

// @include
shared_ptr<ListNode<int>> ReverseLinkedList(
    const shared_ptr<ListNode<int>>& head) {
  if (!head || !head->next) {
    return head;
  }
  auto new_head = ReverseLinkedList(head->next);
  head->next->next = head;
  head->next = nullptr;
  return new_head;
}
// @exclude

void Print(shared_ptr<ListNode<int>> head) {
  if (head) {
    cout << "(" << head->data << ")" << endl;
    Print(head->next);
  }
}

int main(int argc, char* argv[]) {
  shared_ptr<ListNode<int>> L1 = make_shared<ListNode<int>>(ListNode<int>{
      1, make_shared<ListNode<int>>(ListNode<int>{
             2, make_shared<ListNode<int>>(ListNode<int>{3, nullptr})})});
  cout << "before reverse" << endl;
  Print(L1);
  shared_ptr<ListNode<int>> newhead = ReverseLinkedList(L1);
  assert(newhead->data == 3 && newhead->next->data == 2 &&
         newhead->next->next->data == 1);
  cout << endl << "after reverse" << endl;
  Print(newhead);
  newhead = ReverseLinkedList(newhead);
  assert(newhead->data == 1 && newhead->next->data == 2 &&
         newhead->next->next->data == 3);
  cout << endl << "after another reverse" << endl;
  Print(newhead);
  return 0;
}
