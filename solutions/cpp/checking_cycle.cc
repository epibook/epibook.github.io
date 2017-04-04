// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <memory>

#include "./Checking_cycle.h"
#include "./Linked_list_prototype.h"

using std::cout;
using std::endl;
using std::make_shared;

void SimpleTest() {
  shared_ptr<ListNode<int>> L0 =
      make_shared<ListNode<int>>(ListNode<int>{42, nullptr});
  L0->next = L0;
  assert(HasCycle(L0));

  shared_ptr<ListNode<int>> L1 =
      make_shared<ListNode<int>>(ListNode<int>{42, nullptr});
  shared_ptr<ListNode<int>> L2 =
      make_shared<ListNode<int>>(ListNode<int>{42, nullptr});
  L1->next = L2;
  L2->next = L1;
  assert(HasCycle(L1) == L1);
  assert(HasCycle(L2) == L2);

  L2->next = nullptr;
  assert(HasCycle(L1) == nullptr);
  assert(HasCycle(L2) == nullptr);
}

int main(int argc, char* argv[]) {
  SimpleTest();
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
