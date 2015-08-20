// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <limits>
#include <memory>
#include <random>
#include <string>

#include "./Linked_list_prototype.h"
#include "./Merge_sorted_lists.h"

using std::cout;
using std::default_random_engine;
using std::endl;
using std::make_shared;
using std::numeric_limits;
using std::random_device;
using std::stoi;
using std::uniform_int_distribution;

void SimpleTest() {
  shared_ptr<ListNode<int>> L1 = nullptr;
  shared_ptr<ListNode<int>> L2 = nullptr;
  auto result = MergeTwoSortedLists(L1, L2);
  assert(result == nullptr);

  L1 = make_shared<ListNode<int>>(ListNode<int>{123, nullptr});
  result = MergeTwoSortedLists(L1, L2);
  assert(result->data == 123);

  L2 = L1;
  L1 = nullptr;
  result = MergeTwoSortedLists(L1, L2);
  assert(result->data == 123);

  L1 = make_shared<ListNode<int>>(ListNode<int>{-123, nullptr});
  L2 = make_shared<ListNode<int>>(ListNode<int>{123, nullptr});
  result = MergeTwoSortedLists(L1, L2);
  assert(result->data == -123 && result->next->data == 123 &&
         result->next->next == nullptr);
}

int main(int argc, char* argv[]) {
  SimpleTest();
  default_random_engine gen((random_device())());
  for (int times = 0; times < 10000; ++times) {
    shared_ptr<ListNode<int>> F = nullptr, L = nullptr;
    int n, m;
    if (argc == 3) {
      n = stoi(argv[1]), m = stoi(argv[2]);
    } else if (argc == 2) {
      n = stoi(argv[1]), m = stoi(argv[1]);
    } else {
      uniform_int_distribution<int> dis(0, 99);
      n = dis(gen), m = dis(gen);
    }
    for (int i = n; i > 0; --i) {
      shared_ptr<ListNode<int>> temp =
          make_shared<ListNode<int>>(ListNode<int>{i, nullptr});
      temp->next = F;
      F = temp;
    }
    for (int j = m; j > 0; --j) {
      shared_ptr<ListNode<int>> temp =
          make_shared<ListNode<int>>(ListNode<int>{j, nullptr});
      temp->next = L;
      L = temp;
    }

    shared_ptr<ListNode<int>> sorted_head = MergeTwoSortedLists(F, L);
    int count = 0;
    int pre = numeric_limits<int>::min();
    while (sorted_head) {
      assert(pre <= sorted_head->data);
      pre = sorted_head->data;
      sorted_head = sorted_head->next;
      ++count;
    }
    // Make sure the merged list have the same number of nodes as F and L.
    assert(count == n + m);
  }
  return 0;
}
