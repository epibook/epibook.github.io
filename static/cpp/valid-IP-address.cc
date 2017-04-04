// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <random>
#include <string>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::random_device;
using std::stoi;
using std::string;
using std::uniform_int_distribution;
using std::vector;

bool IsValidPart(const string& s);

// @include
vector<string> GetValidIPAddress(const string& s) {
  vector<string> result;
  for (size_t i = 1; i < 4 && i < s.size(); ++i) {
    const string first = s.substr(0, i);
    if (IsValidPart(first)) {
      for (size_t j = 1; i + j < s.size() && j < 4; ++j) {
        const string second = s.substr(i, j);
        if (IsValidPart(second)) {
          for (size_t k = 1; i + j + k < s.size() && k < 4; ++k) {
            const string third = s.substr(i + j, k),
                         fourth = s.substr(i + j + k);
            if (IsValidPart(third) && IsValidPart(fourth)) {
              result.emplace_back(first + "." + second + "." + third + "." +
                                  fourth);
            }
          }
        }
      }
    }
  }
  return result;
}

bool IsValidPart(const string& s) {
  if (s.size() > 3) {
    return false;
  }
  // "00", "000", "01", etc. are not valid, but "0" is valid.
  if (s.front() == '0' && s.size() > 1) {
    return false;
  }
  int val = stoi(s);
  return val <= 255 && val >= 0;
}
// @exclude

int main(int argc, char** argv) {
  if (argc == 2) {
    auto result = GetValidIPAddress(argv[1]);
    for (const string& s : result) {
      cout << s << endl;
    }
  }
  auto res1 = GetValidIPAddress("255255255255");
  for (const string& s : res1) {
    cout << s << endl;
  }
  assert(res1.size() == 1);
  assert(res1.front() == "255.255.255.255");
  auto res2 = GetValidIPAddress("19216811");
  for (const string& s : res2) {
    cout << s << endl;
  }
  assert(res2.size() == 9);
  auto res3 = GetValidIPAddress("1111");
  for (const string& s : res3) {
    cout << s << endl;
  }
  assert(res3.size() == 1);
  assert(res3.front().compare("1.1.1.1") == 0);
  auto res4 = GetValidIPAddress("11000");
  for (const string& s : res4) {
    cout << s << endl;
  }
  assert(res4.size() == 2);
  sort(res4.begin(), res4.end());
  assert(res4[0].compare("1.10.0.0") == 0);
  assert(res4[1].compare("11.0.0.0") == 0);
  return 0;
}
