// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <cctype>
#include <iostream>
#include <limits>
#include <random>
#include <stdexcept>
#include <string>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::exception;
using std::invalid_argument;
using std::max;
using std::numeric_limits;
using std::random_device;
using std::string;
using std::uniform_int_distribution;

string RandIntString(int len) {
  default_random_engine gen((random_device())());
  string ret;
  if (len == 0) {
    return {"0"};
  }
  uniform_int_distribution<int> pos_or_neg(0, 1);
  if (pos_or_neg(gen)) {
    ret.push_back('-');
  }
  uniform_int_distribution<int> num_dis('1', '9');
  ret.push_back(num_dis(gen));
  while (--len) {
    uniform_int_distribution<int> dis('0', '9');
    ret.push_back(dis(gen));
  }
  return ret;
}

// @include
string IntToString(int x) {
  bool is_negative = false;
  if (x < 0) {
    is_negative = true;
  }

  string s;
  do {
    s += '0' + abs(x % 10);
    x /= 10;
  } while (x);

  if (is_negative) {
    s += '-';  // Adds the negative sign back.
  }
  return {s.rbegin(), s.rend()};
}

int StringToInt(const string& s) {
  int result = 0;
  for (int i = s[0] == '-' ? 1 : 0; i < s.size(); ++i) {
    const int digit = s[i] - '0';
    result = result * 10 + digit;
  }
  return s[0] == '-' ? -result : result;
}
// @exclude

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  if (argc == 2) {
    cout << StringToInt(argv[1]) << endl;
    cout << IntToString(StringToInt(argv[1])) << endl;
  } else {
    for (int times = 0; times < 10000; ++times) {
      uniform_int_distribution<int> dis(numeric_limits<int>::min(),
                                        numeric_limits<int>::max());
      int x = dis(gen);
      string str = IntToString(x);
      cout << x << " " << str << endl;
      assert(x == stoi(str));
      uniform_int_distribution<int> len_dis(0, 9);
      str = RandIntString(len_dis(gen));
      x = StringToInt(str);
      cout << str << " " << x << endl;
      assert(x == stoi(str));
    }
  }
  return 0;
}
