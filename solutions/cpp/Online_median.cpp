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
void OnlineMedian(istringstream* sin) {
  // Min-heap stores the bigger part of the stream.
  priority_queue<int, vector<int>, greater<int>> H;
  // Max-heap stores the smaller part of the stream.
  priority_queue<int, vector<int>, less<int>> L;

  int x;
  while (*sin >> x) {
    if (!L.empty() && x > L.top()) {
      H.emplace(x);
    } else {
      L.emplace(x);
    }
    if (H.size() > L.size() + 1) {
      L.emplace(H.top());
      H.pop();
    } else if (L.size() > H.size() + 1) {
      H.emplace(L.top());
      L.pop();
    }

    if (H.size() == L.size()) {
      cout << 0.5 * (H.top() + L.top()) << endl;
    } else {
      cout << (H.size() > L.size() ? H.top() : L.top()) << endl;
    }
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
  istringstream sin(s);
  OnlineMedian(&sin);
  return 0;
}
