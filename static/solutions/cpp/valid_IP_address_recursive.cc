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

void GetOneValidIPAddress(const string&, int, vector<string>*,
                          vector<string>*);
string FormIPAddress(const vector<string>&);
bool IsValidPart(const string&);

// @include
vector<string> GetValidIPAddress(const string& s) {
  vector<string> partial_IP_address;
  vector<string> result;
  GetOneValidIPAddress(s, 4, &partial_IP_address, &result);
  return result;
}

void GetOneValidIPAddress(const string& s, int remaining_parts,
                          vector<string>* partial_IP_address,
                          vector<string>* result) {
  if (s.empty() && remaining_parts == 0) {
    result->emplace_back(FormIPAddress(*partial_IP_address));
    return;
  }

  for (int i = 1; i < 4 && i <= s.size(); ++i) {
    const string part = s.substr(0, i);
    if (IsValidPart(part)) {
      partial_IP_address->emplace_back(part);
      GetOneValidIPAddress(s.substr(i), remaining_parts - 1,
                           partial_IP_address, result);
      partial_IP_address->pop_back();
    }
  }
}

string FormIPAddress(const vector<string>& partial_IP_address) {
  string address = partial_IP_address.front();
  for (int i = 1; i < partial_IP_address.size(); ++i) {
    address += '.' + partial_IP_address[i];
  }
  return address;
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
