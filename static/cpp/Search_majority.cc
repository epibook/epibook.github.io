// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
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
using std::uniform_int_distribution;
using std::vector;

// @include
string MajoritySearch(istringstream* input_stream) {
  string candidate, iter;
  int candidate_count = 0;
  while (*input_stream >> iter) {
    if (candidate_count == 0) {
      candidate = iter;
      candidate_count = 1;
    } else if (candidate == iter) {
      ++candidate_count;
    } else {
      --candidate_count;
    }
  }
  return candidate;
}
// @exclude

string RandString(int len) {
  default_random_engine gen((random_device())());
  string ret;
  while (len--) {
    uniform_int_distribution<int> dis('a', 'z');
    ret += dis(gen);
  }
  return ret;
}

void CheckAns(vector<string>& stream, const string& ans) {
  sort(stream.begin(), stream.end());
  int candidate_count = 1;
  bool find = false;
  for (int i = 1; i < stream.size(); ++i) {
    if (stream[i].compare(stream[i - 1])) {
      if (candidate_count * 2 >= stream.size()) {
        assert(ans.compare(stream[i - 1]) == 0);
        find = true;
      }
      candidate_count = 1;
    } else {
      ++candidate_count;
    }
  }
  if (candidate_count * 2 >= stream.size()) {
    assert(ans.compare(stream.back()) == 0);
    find = true;
  }
  assert(find == true);
}

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  for (int times = 0; times < 10000; ++times) {
    int n;
    vector<string> stream;
    if (argc == 2) {
      n = atoi(argv[1]);
    } else {
      uniform_int_distribution<int> n_dis(1, 1000);
      n = n_dis(gen);
    }
    for (int i = 0; i < n; ++i) {
      uniform_int_distribution<int> dis(1, 5);
      stream.emplace_back(RandString(dis(gen)));
    }
    // generate the majority
    for (int i = 0; i < n; ++i) {
      stream.emplace_back(stream.back());
    }
    string s;
    for (int i = 0; i < stream.size(); ++i) {
      s += stream[i];
      s += ' ';
    }
    istringstream input_stream(s);
    string ret(MajoritySearch(&input_stream));
    cout << ret << endl;
    CheckAns(stream, ret);
  }
  return 0;
}
