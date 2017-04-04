// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
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
int MinimumMessiness(const vector<string>& words, int line_length) {
  // minimum_messiness[i] is the minimum messiness when placing words[0 : i].
  vector<int> minimum_messiness(words.size(), numeric_limits<int>::max());
  int num_remaining_blanks = line_length - words[0].size();
  minimum_messiness[0] = num_remaining_blanks * num_remaining_blanks;
  for (int i = 1; i < words.size(); ++i) {
    num_remaining_blanks = line_length - words[i].size();
    minimum_messiness[i] = minimum_messiness[i - 1] +
                           num_remaining_blanks * num_remaining_blanks;
    // Try adding words[i - 1], words[i - 2], ...
    for (int j = i - 1; j >= 0; --j) {
      num_remaining_blanks -= (words[j].size() + 1);
      if (num_remaining_blanks < 0) {
        // Not enough space to add more words.
        break;
      }
      int first_j_messiness = j - 1 < 0 ? 0 : minimum_messiness[j - 1];
      int current_line_messiness =
          num_remaining_blanks * num_remaining_blanks;
      minimum_messiness[i] = min(minimum_messiness[i],
                                 first_j_messiness + current_line_messiness);
    }
  }
  return minimum_messiness.back();
}
// @exclude

void SmallTest() {
  assert(MinimumMessiness({"aaa", "bbb", "c", "d", "ee", "ff", "gggggg"},
                          11) == 45);
  assert(MinimumMessiness({"a", "b", "c", "d"}, 5) == 8);
}

int main(int argc, char* argv[]) {
  SmallTest();
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
  cout << MinimumMessiness(W, L) << endl;
  return 0;
}
