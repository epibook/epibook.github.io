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
using std::stoul;
using std::string;
using std::uniform_int_distribution;
using std::vector;

bool is_prefix(const string& a, const string& b);

// @include
bool prefix_search(const vector<string>& A, const string& p) {
  auto it = lower_bound(A.cbegin(), A.cend(), p);
  if (it == A.cend()) {
    return false;
  }
  return is_prefix(p, *it);
}

bool is_prefix(const string& a, const string& b) {
  if (a.size() > b.size()) {
    return false;
  }
  return mismatch(a.cbegin(), a.cend(), b.cbegin()).first == a.cend();
}
// @exclude

void small_test() {
  const vector<string> A = {"aardvark", "ant", "antelope", "bat",
                            "cat",      "dog", "emu"};
  assert(prefix_search(A, "ante"));
  assert(prefix_search(A, "ant"));
  assert(!prefix_search(A, "anti"));
}

bool check_ans(const vector<string>& A, const string& target) {
  for (const string& s : A) {
    if (s.compare(target) > 0 && !is_prefix(target, s)) {
      return false;
    } else if (is_prefix(target, s)) {
      return true;
    }
  }
  return false;
}

string rand_string(int len) {
  default_random_engine gen((random_device())());
  uniform_int_distribution<char> dis('a', 'z');
  string ret;
  while (len--) {
    ret += dis(gen);
  }
  return ret;
}

int main(int argc, char** argv) {
  small_test();
  default_random_engine gen((random_device())());
  for (int times = 0; times < 1000; ++times) {
    size_t n;
    if (argc == 2) {
      n = stoul(argv[1]);
    } else {
      uniform_int_distribution<size_t> n_dis(0, 1000);
      n = n_dis(gen);
    }
    uniform_int_distribution<int> A_dis(1, 20);
    vector<string> A;
    generate_n(back_inserter(A), n, [&] { return rand_string(A_dis(gen)); });
    sort(A.begin(), A.end());
    /*
    for (const string& a : A) {
      cout << a << " ";
    }
    cout << endl;
    */
    string target = rand_string(A_dis(gen));
    // cout << "target = " << target << endl;
    auto result = prefix_search(A, target), ans = check_ans(A, target);
    // cout << std::boolalpha << result << " " << ans << endl;
    assert(result == ans);
  }
  return 0;
}
