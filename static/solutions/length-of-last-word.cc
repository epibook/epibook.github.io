// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <random>
#include <string>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::random_device;
using std::string;
using std::uniform_int_distribution;

// @include
size_t length_of_last_word(const string& s) {
  auto last_space = s.rbegin();
  bool seen_non_space = false;
  for (auto it = s.rbegin(); it != s.rend(); ++it) {
    if (*it == ' ') {
      if (seen_non_space) {
        return distance(*last_space == ' ' ? last_space + 1 : last_space, it);
      }
      last_space = it;
    } else {
      seen_non_space = true;
    }
  }
  return seen_non_space
             ? distance(*last_space == ' ' ? last_space + 1 : last_space,
                        s.rend())
             : 0;
}
// @exclude

int main(int argc, char** argv) {
  string s = "Hello World";
  assert(5 == length_of_last_word(s));
  s = "a ";
  assert(1 == length_of_last_word(s));
  s = " ";
  assert(0 == length_of_last_word(s));
  s = "a";
  assert(1 == length_of_last_word(s));
  s = " a ";
  assert(1 == length_of_last_word(s));
  s = " a,b,.c d ";
  assert(1 == length_of_last_word(s));
  s = " a,bbbbbbb,.c d ";
  assert(1 == length_of_last_word(s));
  return 0;
}
