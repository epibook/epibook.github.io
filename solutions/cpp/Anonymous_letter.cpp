// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <random>
#include <string>
#include <unordered_map>

using std::boolalpha;
using std::cout;
using std::default_random_engine;
using std::endl;
using std::random_device;
using std::string;
using std::uniform_int_distribution;
using std::unordered_map;
using std::vector;

string RandString(int len) {
  string ret;
  default_random_engine gen((random_device())());
  while (len--) {
    uniform_int_distribution<int> dis(0, 26);
    int x = dis(gen);
    ret += (x < 26) ? 'a' + x : ' ';
  }
  return ret;
}

// @include
bool AnonymousLetter(const string& L, const string& M) {
  unordered_map<char, int> hash;
  // Inserts all chars in L into a hash table.
  for_each(L.begin(), L.end(), [&hash](const char & c) { ++hash[c]; });

  // Checks characters in M that could cover characters in a hash table.
  for (const char& c : M) {
    auto it = hash.find(c);
    if (it != hash.cend()) {
      if (--it->second == 0) {
        hash.erase(it);
        if (hash.empty()) {
          return true;
        }
      }
    }
  }
  // No entry in hash means L can be covered by M.
  return hash.empty();
}
// @exclude

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  string L, M;
  if (argc == 3) {
    L = argv[1], M = argv[2];
  } else {
    uniform_int_distribution<int> L_dis(1, 1000);
    uniform_int_distribution<int> M_dis(1, 100000);
    L = RandString(L_dis(gen)), M = RandString(M_dis(gen));
  }
  cout << L << endl;
  cout << M << endl;
  assert(!AnonymousLetter("123", "456"));
  assert(!AnonymousLetter("123", "12222222"));
  assert(AnonymousLetter("123", "1123"));
  assert(AnonymousLetter("123", "123"));
  cout << boolalpha << AnonymousLetter(L, M) << endl;
  return 0;
}
