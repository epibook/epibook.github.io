// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <random>
#include <sstream>
#include <string>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::istringstream;
using std::random_device;
using std::string;
using std::stringstream;
using std::uniform_int_distribution;
using std::vector;

// @include
vector<int> ReservoirSampling(istringstream* sin, int k) {
  int x;
  vector<int> R;
  // Stores the first k elements.
  for (int i = 0; i < k && *sin >> x; ++i) {
    R.emplace_back(x);
  }

  // After the first k elements.
  int element_num = k;
  while (*sin >> x) {
    default_random_engine gen((random_device())());  // Random num generator.
    // Generate a random int in [0, element_num].
    uniform_int_distribution<int> dis(0, element_num++);
    int tar = dis(gen);
    if (tar < k) {
      R[tar] = x;
    }
  }
  return R;
}
// @exclude

int main(int argc, char* argv[]) {
  int n, k;
  default_random_engine gen((random_device())());
  if (argc == 2) {
    n = atoi(argv[1]);
    uniform_int_distribution<int> dis(1, n);
    k = dis(gen);
  } else if (argc == 3) {
    n = atoi(argv[1]);
    k = atoi(argv[2]);
  } else {
    uniform_int_distribution<int> n_dis(0, 99999);
    n = n_dis(gen);
    uniform_int_distribution<int> k_dis(1, n);
    k = k_dis(gen);
  }
  vector<int> A;
  for (int i = 0; i < n; ++i) {
    A.emplace_back(i);
  }
  string s;
  for (int i = 0; i < A.size(); ++i) {
    stringstream ss;
    ss << A[i];
    s += ss.str();
    s += ' ';
  }
  cout << n << ' ' << k << endl;
  istringstream sin(s);
  vector<int> ans = ReservoirSampling(&sin, k);
  assert(ans.size() == k);
  /*
  copy(ans.begin(), ans.end(), ostream_iterator<int>(cout, " "));
  cout << endl;
  */
  return 0;
}
