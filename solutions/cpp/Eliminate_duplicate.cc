// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <random>
#include <string>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::random_device;
using std::string;
using std::to_string;
using std::uniform_int_distribution;
using std::vector;

// @include
struct Name {
  bool operator==(const Name& that) const {
    return first_name == that.first_name;
  }

  bool operator<(const Name& that) const {
    if (first_name != that.first_name) {
      return first_name < that.first_name;
    }
    return last_name < that.last_name;
  }

  string first_name, last_name;
};

void EliminateDuplicate(vector<Name>* A) {
  sort(A->begin(), A->end());  // Makes identical elements become neighbors.
  // unique() removes adjacent duplicates and returns an iterator to the
  // element the follows the last element not removed. The effect of erase()
  // is to restrict A to the distinct elements.
  A->erase(unique(A->begin(), A->end()), A->end());
}
// @exclude

void CheckAns(const vector<Name>& A) {
  for (size_t i = 1; i < A.size(); ++i) {
    assert(!(A[i] == A[i - 1]));
  }
}

void SmallTest() {
  vector<Name> A = {{"Foo", "Bar"}, {"ABC", "XYZ"}, {"Foo", "Widget"}};
  EliminateDuplicate(&A);
  assert(A.size() == 2);
}

int main(int argc, char* argv[]) {
  SmallTest();
  default_random_engine gen((random_device())());
  for (int times = 0; times < 1000; ++times) {
    int n;
    vector<Name> A;
    if (argc == 2) {
      n = atoi(argv[1]);
    } else {
      uniform_int_distribution<int> dis(0, 1000);
      n = dis(gen);
    }
    for (int i = 0; i < n; ++i) {
      uniform_int_distribution<int> dis(0, n - 1);
      A.emplace_back(Name{to_string(dis(gen)), to_string(dis(gen))});
    }
    EliminateDuplicate(&A);
    CheckAns(A);
  }
  return 0;
}
