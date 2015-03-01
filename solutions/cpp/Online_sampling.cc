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
vector<int> OnlineSampling(int n, int k) {
  unordered_map<int, int> H;
  default_random_engine gen((random_device())());  // Random num generator.
  for (int i = 0; i < k; ++i) {
    // Generate a random int in [0, n - 1 - i].
    uniform_int_distribution<int> dis(0, n - 1 - i);
    int r = dis(gen);
    auto ptr1 = H.find(r), ptr2 = H.find(n - 1 - i);
    if (ptr1 == H.end() && ptr2 == H.end()) {
      H[r] = n - 1 - i;
      H[n - 1 - i] = r;
    } else if (ptr1 == H.end() && ptr2 != H.end()) {
      H[r] = ptr2->second;
      ptr2->second = r;
    } else if (ptr1 != H.end() && ptr2 == H.end()) {
      H[n - 1 - i] = ptr1->second;
      ptr1->second = n - 1 - i;
    } else {
      int temp = ptr2->second;
      H[n - 1 - i] = ptr1->second;
      H[r] = temp;
    }
  }

  vector<int> res;
  for (int i = 0; i < k; ++i) {
    res.emplace_back(H[n - 1 - i]);
  }
  return res;
}
// @exclude

int main(int argc, char* argv[]) {
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
  for (int i = 0; i < 6; ++i) {
    vector<int> res = OnlineSampling(n, k);
    cout << "result = ";
    copy(res.begin(), res.end(), ostream_iterator<int>(cout, " "));
    cout << endl;
  }
  return 0;
}
