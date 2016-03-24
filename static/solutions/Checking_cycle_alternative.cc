// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <memory>

#include "./Linked_list_prototype.h"

using std::cout;
using std::endl;
using std::make_shared;

// @include
shared_ptr<ListNode<int>> HasCycle(const shared_ptr<ListNode<int>>& head) {
  shared_ptr<ListNode<int>> fast = head, slow = head;

  while (fast && fast->next && fast->next->next) {
    slow = slow->next, fast = fast->next->next;
    if (slow == fast) {  // There is a cycle.
      // Tries to find the start of the cycle.
      slow = head;
      // Both pointers advance at the same time.
      while (slow != fast) {
        slow = slow->next, fast = fast->next;
      }
      return slow;  // slow is the start of cycle.
    }
  }
  return nullptr;  // No cycle.
}
// @exclude

int main(int argc, char* argv[]) {
  shared_ptr<ListNode<int>> L3 =
      make_shared<ListNode<int>>(ListNode<int>{3, nullptr});
  shared_ptr<ListNode<int>> L2 =
      make_shared<ListNode<int>>(ListNode<int>{2, L3});
  shared_ptr<ListNode<int>> L1 =
      make_shared<ListNode<int>>(ListNode<int>{1, L2});

  // should output "L1 does not have cycle."
  assert(HasCycle(L1) == nullptr);
  cout << "L1 " << (HasCycle(L1) ? "has" : "does not have") << " cycle."
       << endl;

  // make it a cycle
  L3->next = L2;
  // should output "L1 has cycle, located at node has value 2"
  assert(HasCycle(L1) != nullptr);
  assert(HasCycle(L1)->data == 2);
  auto temp = HasCycle(L1);
  if (temp) {
    cout << "L1 has cycle, located at node has value " << temp->data << endl;
  } else {
    cout << "L1 does not have cycle" << endl;
  }
  return 0;
}
