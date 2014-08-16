// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <iostream>
#include <limits>
#include <random>
#include <string>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::min;
using std::numeric_limits;
using std::random_device;
using std::string;
using std::uniform_int_distribution;
using std::vector;

string RandString(int len) {
  default_random_engine gen((random_device())());
  string ret;
  while (len--) {
    uniform_int_distribution<int> dis('a', 'z');
    ret += dis(gen);
  }
  return ret;
}

// @include
int FindPrettyPrinting(const vector<string>& W, int L) {
  // Calculates M(i).
  vector<long> M(W.size(), numeric_limits<long>::max());
  for (int i = 0; i < W.size(); ++i) {
    int b_len = L - W[i].size();
    M[i] = min((i < 1 ? 0 : M[i - 1]) + (1 << b_len), M[i]);
    for (int j = i - 1; j >= 0; --j) {
      b_len -= (W[j].size() + 1);
      if (b_len < 0) {
        break;
      }
      M[i] = min((j - 1 < 0 ? 0 : M[j - 1]) + (1 << b_len), M[i]);
    }
  }

  // Finds the minimum cost without considering the last line.
  long min_mess = (W.size() >= 2 ? M[W.size() - 2] : 0);
  int b_len = L - W.back().size();
  for (int i = W.size() - 2; i >= 0; --i) {
    b_len -= (W[i].size() + 1);
    if (b_len < 0) {
      return min_mess;
    }
    min_mess = min(min_mess, (i < 1 ? 0 : M[i - 1]));
  }
  return min_mess;
}
// @exclude

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  int n, L;
  if (argc == 2) {
    n = atoi(argv[1]);
    uniform_int_distribution<int> dis(10, 19);
    L = dis(gen);
  } else if (argc == 3) {
    n = atoi(argv[1]);
    L = atoi(argv[2]);
  } else {
    uniform_int_distribution<int> n_dis(1, 30);
    n = n_dis(gen);
    uniform_int_distribution<int> L_dis(11, 20);
    L = L_dis(gen);
  }
  vector<string> W;
  for (int i = 0; i < n; ++i) {
    uniform_int_distribution<int> dis(1, 10);
    W.push_back(RandString(dis(gen)));
  }
  for (int i = 0; i < n; ++i) {
    cout << W[i] << ' ';
  }
  cout << L << endl;
  cout << FindPrettyPrinting(W, L) << endl;
  return 0;
}
