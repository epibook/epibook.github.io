// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <memory>

#include "./Linked_list_prototype.h"
#include "./Reverse_linked_list_iterative.h"

using std::cout;
using std::endl;
using std::make_shared;
using std::shared_ptr;

// @include
bool IsLinkedListAPalindrome(shared_ptr<ListNode<int>> L) {
  if (L == nullptr) {
    return true;
  }

  // Finds the second half of L.
  shared_ptr<ListNode<int>> slow = L, fast = L;
  while (fast && fast->next) {
    fast = fast->next->next, slow = slow->next;
  }

  // Compares the first half and the reversed second half lists.
  auto first_half_iter = L, second_half_iter = ReverseLinkedList(slow->next);
  while (second_half_iter && first_half_iter) {
    if (second_half_iter->data != first_half_iter->data) {
      return false;
    }
    second_half_iter = second_half_iter->next;
    first_half_iter = first_half_iter->next;
  }
  return true;
}
// @exclude

template <typename T>
void PrintList(shared_ptr<ListNode<T>> L) {
  while (L) {
    cout << L->data << ' ';
    L = L->next;
  }
  cout << endl;
}

int main(int argc, char* argv[]) {
  shared_ptr<ListNode<int>> head = nullptr;
  if (argc > 2) {
    // Input the node's value in reverse order.
    for (int i = 1; i < argc; ++i) {
      shared_ptr<ListNode<int>> curr =
          make_shared<ListNode<int>>(ListNode<int>{atoi(argv[i]), head});
      head = curr;
    }
    cout << ((IsLinkedListAPalindrome(head)) ? "Yes" : "No") << endl;
  } else {
    // A link list is a palindrome.
    for (int i = 6; i >= 1; --i) {
      shared_ptr<ListNode<int>> curr =
          make_shared<ListNode<int>>(ListNode<int>{1, head});
      head = curr;
    }
    assert(IsLinkedListAPalindrome(head) == true);
    // Still a palindrome linked list.
    head = shared_ptr<ListNode<int>>(nullptr);
    for (int i = 5; i >= 1; --i) {
      shared_ptr<ListNode<int>> curr =
          make_shared<ListNode<int>>(ListNode<int>{1, head});
      head = curr;
    }
    head->next->next->data = 3;
    assert(IsLinkedListAPalindrome(head) == true);
    // Not a palindrome linked list.
    head = nullptr;
    for (int i = 5; i >= 1; --i) {
      shared_ptr<ListNode<int>> curr =
          make_shared<ListNode<int>>(ListNode<int>{i, head});
      head = curr;
    }
    assert(IsLinkedListAPalindrome(head) == false);
    assert(IsLinkedListAPalindrome(nullptr) == true);
  }
  return 0;
}
