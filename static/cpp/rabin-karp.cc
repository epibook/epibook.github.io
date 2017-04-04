// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <cmath>
#include <iostream>
#include <random>
#include <string>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::random_device;
using std::string;
using std::uniform_int_distribution;
using std::vector;

// @include
// Returns the index of the first character of the substring if found, -1
// otherwise.
int RabinKarp(const string &t, const string &s) {
  if (s.size() > t.size()) {
    return -1;  // s is not a substring of t.
  }

  const int kBase = 26;
  int t_hash = 0, s_hash = 0;  // Hash codes for the substring of t and s.
  int power_s = 1;  // kBase^|s|.
  for (int i = 0; i < s.size(); ++i) {
    power_s = i ? power_s * kBase : 1;
    t_hash = t_hash * kBase + t[i];
    s_hash = s_hash * kBase + s[i];
  }

  for (int i = s.size(); i < t.size(); ++i) {
    // Checks the two substrings are actually equal or not, to protect
    // against hash collision.
    if (t_hash == s_hash && !t.compare(i - s.size(), s.size(), s)) {
      return i - s.size();  // Found a match.
    }

    // Uses rolling hash to compute the new hash code.
    t_hash -= t[i - s.size()] * power_s;
    t_hash = t_hash * kBase + t[i];
  }

  // Tries to match s and t[t.size() - s.size() : t.size() - 1].
  if (t_hash == s_hash && t.compare(t.size() - s.size(), s.size(), s) == 0) {
    return t.size() - s.size();
  }
  return -1;  // s is not a substring of t.
}
// @exclude

int CheckAnswer(const string &t, const string &s) {
  for (int i = 0; i + s.size() - 1 < t.size(); ++i) {
    bool find = true;
    for (int j = 0; j < s.size(); ++j) {
      if (t[i + j] != s[j]) {
        find = false;
        break;
      }
    }
    if (find == true) {
      return i;
    }
  }
  return -1;  // No matching.
}

string RandString(int len) {
  default_random_engine gen((random_device())());
  string ret;
  while (len--) {
    uniform_int_distribution<char> dis('a', 'z');
    ret += dis(gen);
  }
  return ret;
}

void SimpleTest() {
  assert(RabinKarp("GACGCCA", "CGC") == 2);
  assert(RabinKarp("GATACCCATCGAGTCGGATCGAGT", "GAG") == 10);
  assert(RabinKarp("FOOBARWIDGET", "WIDGETS") == -1);
  assert(RabinKarp("A", "A") == 0);
  assert(RabinKarp("A", "B") == -1);
  assert(RabinKarp("A", "") == 0);
  assert(RabinKarp("ADSADA", "") == 0);
  assert(RabinKarp("", "A") == -1);
  assert(RabinKarp("", "AAA") == -1);
  assert(RabinKarp("A", "AAA") == -1);
  assert(RabinKarp("AA", "AAA") == -1);
  assert(RabinKarp("AAA", "AAA") == 0);
  assert(RabinKarp("BAAAA", "AAA") == 1);
  assert(RabinKarp("BAAABAAAA", "AAA") == 1);
  assert(RabinKarp("BAABBAABAAABS", "AAA") == 8);
  assert(RabinKarp("BAABBAABAAABS", "AAAA") == -1);
  assert(RabinKarp("FOOBAR", "BAR") > 0);
}

int main(int argc, char *argv[]) {
  SimpleTest();
  if (argc == 3) {
    string t = argv[1];
    string s = argv[2];
    cout << "t = " << t << endl;
    cout << "s = " << s << endl;
    assert(RabinKarp(t, s) == CheckAnswer(t, s));
  } else {
    default_random_engine gen((random_device())());
    for (int times = 0; times < 10000; ++times) {
      uniform_int_distribution<int> t_dis(1, 1000);
      uniform_int_distribution<int> s_dis(1, 20);
      string t = RandString(t_dis(gen));
      string s = RandString(s_dis(gen));
      cout << "t = " << t << endl;
      cout << "s = " << s << endl;
      assert(RabinKarp(t, s) == CheckAnswer(t, s));
    }
  }
  return 0;
}
