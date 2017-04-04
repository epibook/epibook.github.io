// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

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
using std::to_string;
using std::uniform_int_distribution;
using std::vector;

unsigned diff(unsigned a, unsigned b);

// @include
unsigned find_closest_palindrome(unsigned x) {
  string str(to_string(x));
  // Make str a palindrome by mirroring the left half to the right half.
  copy(str.cbegin(), str.cbegin() + (str.size() / 2), str.rbegin());

  unsigned mirror_left = stoul(str);
  int idx = (str.size() - 1) / 2;
  if (mirror_left >= x) {
    // Subtract one from the left half.
    while (idx >= 0) {
      if (str[idx] == '0') {
        str[idx--] = '9';
      } else {
        --str[idx];
        break;
      }
    }
    if (str[0] == '0') {  // special case, make the whole string as "99...9".
      str = to_string(stoul(str));  // removes the leading 0.
      fill(str.begin(), str.end(), '9');
    }
  } else {  // mirror_left < x.
    // Add one to the left half.
    while (idx >= 0) {
      if (str[idx] == '9') {
        str[idx--] = '0';
      } else {
        ++str[idx];
        break;
      }
    }
  }

  // Make str a palindrome again by mirroring the left half to the right half.
  copy(str.cbegin(), str.cbegin() + (str.size() / 2), str.rbegin());
  return diff(x, mirror_left) < diff(x, stoul(str)) ? mirror_left
                                                    : stoul(str);
}

unsigned diff(unsigned a, unsigned b) { return a > b ? a - b : b - a; }
// @exclude

bool is_palindrome(unsigned x) {
  string str(to_string(x));
  for (int i = 0, j = str.size() - 1; i < j; ++i, --j) {
    if (str[i] != str[j]) {
      return false;
    }
  }
  return true;
}

void check_answer(unsigned x, unsigned ans) {
  if (is_palindrome(x)) {
    assert(ans == x);
    return;
  }
  unsigned small = x - 1;
  while (is_palindrome(small) == false) {
    --small;
  }
  unsigned big = x + 1;
  while (is_palindrome(big) == false) {
    ++big;
  }
  if (x - small > big - x) {
    assert(big - x == ans > x ? ans - x : x - ans);
  } else {
    assert(x - small == ans > x ? ans - x : x - ans);
  }
}

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  for (int times = 0; times < 100000; ++times) {
    unsigned x;
    if (argc == 2) {
      x = atol(argv[1]);
    } else {
      uniform_int_distribution<int> dis(1, 10000000);
      x = dis(gen);
    }
    unsigned ret = find_closest_palindrome(x);
    cout << x << ' ' << ret << endl;
    assert(is_palindrome(ret));
    check_answer(x, ret);
  }
  return 0;
}
