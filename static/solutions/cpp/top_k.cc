// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <queue>
#include <random>
#include <sstream>
#include <string>
#include <vector>

using std::default_random_engine;
using std::equal;
using std::function;
using std::greater;
using std::istringstream;
using std::priority_queue;
using std::random_device;
using std::stoi;
using std::string;
using std::to_string;
using std::uniform_int_distribution;
using std::vector;
using std::cout;
using std::endl;

// @include
vector<string> TopK(int k, istringstream* stream) {
  priority_queue<string, vector<string>, function<bool(string, string)>>
  min_heap(
      [](const string& a, const string& b) { return a.size() >= b.size(); });
  string next_string;
  while (*stream >> next_string) {
    min_heap.emplace(next_string);
    if (min_heap.size() > k) {
      // Remove the shortest string. Note that the comparison function above
      // will order the strings by length.
      min_heap.pop();
    }
  }
  vector<string> result;
  while (!min_heap.empty()) {
    result.emplace_back(min_heap.top());
    min_heap.pop();
  }
  return result;
}
// @exclude

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  for (int times = 0; times < 1000; ++times) {
    int num;
    if (argc == 2) {
      num = stoi(argv[1]);
    } else {
      uniform_int_distribution<int> num_dis(1, 10000);
      num = num_dis(gen);
    }
    vector<string> A;
    uniform_int_distribution<int> dis(1, 10000000);
    for (int i = 0; i < num; ++i) {
      A.emplace_back(to_string(dis(gen)));
    }
    uniform_int_distribution<int> kDis(1, num);
    int k = kDis(gen);
    string input_stream;
    for (const auto& s : A) {
      input_stream += s + ' ';
    }
    cout << "k = " << k << endl;
    istringstream sin(input_stream);
    vector<string> result = TopK(k, &sin);
    sort(A.begin(), A.end(), [](const string& a, const string& b) {
      return a.size() < b.size();
    });
    assert(equal(A.end() - k, A.end(), result.begin(), result.end(),
                 [](const string& a, const string& b) {
                   return a.size() == b.size();
                 }));
  }
  return 0;
}
