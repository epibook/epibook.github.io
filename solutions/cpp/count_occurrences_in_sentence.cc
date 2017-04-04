// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <random>
#include <string>
#include <unordered_map>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::random_device;
using std::string;
using std::uniform_int_distribution;
using std::unordered_map;

static unordered_map<char, int> char_to_count;

// @include
void CountOccurrences(string S) {
  sort(S.begin(), S.end());

  int current_character_count = 1;
  for (int i = 1; i < S.size(); ++i) {
    if (S[i] == S[i - 1]) {
      ++current_character_count;
    } else {
      cout << '(' << S[i - 1] << ',' << current_character_count << "),";
      // @exclude
      char_to_count[S[i - 1]] = current_character_count;
      // @include
      current_character_count = 1;
    }
  }
  cout << '(' << S.back() << ',' << current_character_count << ')' << endl;
}
// @exclude

string RandString(int len) {
  default_random_engine gen((random_device())());
  string ret;
  while (len--) {
    uniform_int_distribution<int> dis('a', 'z');
    ret += dis(gen);
  }
  return ret;
}

static void SimpleTest() {
  CountOccurrences("foo bar! ABA A");
  assert(char_to_count['f'] == 1);
  assert(char_to_count.count('F') == 0);
  assert(char_to_count['o'] == 2);
  assert(char_to_count.count('x') == 0);
  assert(char_to_count[' '] == 3);
  assert(char_to_count['!'] == 1);
  assert(char_to_count['A'] == 3);
  assert(char_to_count['B'] == 1);
}

int main(int argc, char* argv[]) {
  SimpleTest();
  default_random_engine gen((random_device())());
  string S;
  if (argc == 2) {
    S = argv[1];
  } else {
    uniform_int_distribution<int> dis(1, 1000);
    S = RandString(dis(gen));
  }
  cout << S << endl;
  CountOccurrences(S);
  return 0;
}
