// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <memory>

#include "./Checking_cycle.h"
#include "./Linked_list_prototype.h"

using std::cout;
using std::endl;
using std::make_shared;

int main(int argc, char* argv[]) {
  shared_ptr<ListNode<int>> L3 =
      make_shared<ListNode<int>>(ListNode<int>{3, nullptr});
  shared_ptr<ListNode<int>> L2 =
      make_shared<ListNode<int>>(ListNode<int>{2, L3});
  shared_ptr<ListNode<int>> L1 =
      make_shared<ListNode<int>>(ListNode<int>{1, L2});

  // Should output "L1 does not have cycle."
  assert(HasCycle(L1) == nullptr);
  cout << "L1 " << (HasCycle(L1) ? "has" : "does not have") << " cycle."
       << endl;

  // Make it a cycle
  L3->next = L2;
  // Should output "L1 has cycle, located at node has value 2"
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
