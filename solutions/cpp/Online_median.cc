// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <iostream>
#include <functional>
#include <queue>
#include <random>
#include <sstream>
#include <string>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::greater;
using std::istringstream;
using std::less;
using std::priority_queue;
using std::random_device;
using std::string;
using std::stringstream;
using std::uniform_int_distribution;
using std::vector;

// @include
void OnlineMedian(istringstream* sequence) {
  // min_heap stores the larger half seen so far.
  priority_queue<int, vector<int>, greater<int>> min_heap;
  // max_heap stores the smaller half seen so far.
  priority_queue<int, vector<int>, less<int>> max_heap;

  int x;
  while (*sequence >> x) {
    min_heap.emplace(x);
    if (min_heap.size() > max_heap.size() + 1) {
      max_heap.emplace(min_heap.top());
      min_heap.pop();
    }

    cout << (min_heap.size() == max_heap.size() ?
        0.5 * (min_heap.top() + max_heap.top()) : min_heap.top()) << endl;
  }
}
// @exclude

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  int num;
  if (argc == 2) {
    num = atoi(argv[1]);
  } else {
    uniform_int_distribution<int> dis(1, 100000);
    num = dis(gen);
  }
  vector<int> stream;
  for (int i = 0; i < num; ++i) {
    uniform_int_distribution<int> dis(1, 10000);
    stream.emplace_back(dis(gen));
  }
  string s;
  for (int i = 0; i < stream.size(); ++i) {
    cout << stream[i] << endl;
    stringstream ss;
    ss << stream[i];
    s += ss.str();
    s += ' ';
  }
  cout << endl;
  istringstream sequence(s);
  OnlineMedian(&sequence);
  return 0;
}
