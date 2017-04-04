#include <cassert>
#include <cstring>
#include <iostream>

using namespace std;

// @include
bool is_match(const string &s, const string &m) {
  if (m.empty()) {
    return s.empty();
  }

  if (m.size() == 1) {
    return s.size() == 1 && (s.front() == m.front() || m.front() == '.');
  }

  if (m[1] == '*') {
    for (int i = 0; i < s.size() && (s[i] == m.front() || m.front() == '.');
         ++i) {
      if (is_match(s.substr(i + 1), m.substr(2))) {
        return true;
      }
    }
    return is_match(s, m.substr(2));
  }

  return !s.empty() && (s.front() == m.front() || m.front() == '.') &&
         is_match(s.substr(1), m.substr(1));
}
// @exclude

int main(int argc, char *argv[]) {
  if (argc == 3) {
    // Remember to put '\' before any special metacharacter you input
    cout << boolalpha << is_match(argv[1], argv[2]) << endl;
  }
  assert(true == is_match("c", "."));
  assert(true == is_match("ac", ".c"));
  assert(true == is_match("c", "c"));
  assert(false == is_match("cc", "c"));
  assert(true == is_match("c", "c*"));
  assert(true == is_match("ac", ".*c"));
  assert(true == is_match("acac", ".*a.*"));
  assert(true == is_match("ac", ".*a.*c"));
  assert(true == is_match("acac", ".*a.*c"));
  assert(true == is_match("", ".*"));
  assert(true == is_match("a", ".*"));
  assert(true == is_match("cagt", ".*c.*a.*g.*t"));
  assert(true == is_match("cagt", "c*ca*ag*gt*"));
  assert(true == is_match("cagtt", "c*ca*ag*gt*tt"));
  return 0;
}
