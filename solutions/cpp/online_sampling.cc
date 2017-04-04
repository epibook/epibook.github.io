// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <iostream>
#include <random>
#include <unordered_map>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::ostream_iterator;
using std::random_device;
using std::uniform_int_distribution;
using std::unordered_map;
using std::vector;

// @include
// Returns a random k-sized subset of {0, 1, ..., n - 1}.
vector<int> RandomSubset(int n, int k) {
  unordered_map<int, int> changed_elements;
  default_random_engine seed((random_device())());  // Random num generator.
  for (int i = 0; i < k; ++i) {
    // Generate a random index in [i, n - 1].
    int rand_idx = uniform_int_distribution<int>{i, n - 1}(seed);
    auto ptr1 = changed_elements.find(rand_idx),
         ptr2 = changed_elements.find(i);
    if (ptr1 == changed_elements.end() && ptr2 == changed_elements.end()) {
      changed_elements[rand_idx] = i;
      changed_elements[i] = rand_idx;
    } else if (ptr1 == changed_elements.end() &&
               ptr2 != changed_elements.end()) {
      changed_elements[rand_idx] = ptr2->second;
      ptr2->second = rand_idx;
    } else if (ptr1 != changed_elements.end() &&
               ptr2 == changed_elements.end()) {
      changed_elements[i] = ptr1->second;
      ptr1->second = i;
    } else {
      int temp = ptr2->second;
      changed_elements[i] = ptr1->second;
      changed_elements[rand_idx] = temp;
    }
  }

  vector<int> result;
  for (int i = 0; i < k; ++i) {
    result.emplace_back(changed_elements[i]);
  }
  return result;
}
// @exclude

int main(int argc, char *argv[]) {
  default_random_engine gen((random_device())());
  int n, k;
  if (argc == 2) {
    n = atoi(argv[1]);
    uniform_int_distribution<int> dis(1, n);
    k = dis(gen);
  } else if (argc == 3) {
    n = atoi(argv[1]);
    k = atoi(argv[2]);
  } else {
    uniform_int_distribution<int> n_dis(1, 10000);
    n = n_dis(gen);
    uniform_int_distribution<int> k_dis(1, n);
    k = k_dis(gen);
  }
  cout << "n = " << n << " k = " << k << endl;
  for (int i = 0; i < 100; ++i) {
    vector<int> res = RandomSubset(n, k);
    cout << "result = ";
    copy(res.begin(), res.end(), ostream_iterator<int>(cout, " "));
    cout << endl;
  }
  return 0;
}
