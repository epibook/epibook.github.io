// Copyright (c) 2014 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <string>

using std::cout;
using std::endl;
using std::string;

bool is_integer(const string& s, int begin, int end, bool allow_sign,
                bool allow_no_digit);
string trim_space(const string& s);

// @include
bool is_valid_number(const string& s) {
  string trim_s = trim_space(s);
  // cout << "trim_s = " << trim_s << endl;
  int e_idx = -1, dot_idx = -1;
  // Checks no illegal chars in s.
  for (size_t i = 0; i < trim_s.size(); ++i) {
    if (trim_s[i] == ' ') {
      return false;
    }
    if (trim_s[i] == 'e' || trim_s[i] == 'E') {
      if (e_idx != -1) {
        return false;
      }
      e_idx = i;
    } else if (trim_s[i] == '.') {
      if (dot_idx != -1) {
        return false;
      }
      dot_idx = i;
    } else if (!(isdigit(trim_s[i]) || trim_s[i] == '+' ||
                 trim_s[i] == '-')) {
      return false;
    }
  }

  // cout << "going to test " << e_idx << " " << dot_idx << endl;
  // '.' must appear before e.
  if (dot_idx != -1 && e_idx != -1 && dot_idx > e_idx) {
    return false;
  }
  bool result = true;
  if (dot_idx != -1) {
    // cout << "1 " << std::boolalpha << result << endl;
    if (e_idx != -1) {
      result &= ((is_integer(trim_s, 0, dot_idx, true, false) &&
                  is_integer(trim_s, dot_idx + 1, e_idx, false, true)) ||
                 (is_integer(trim_s, 0, dot_idx, true, true) &&
                  is_integer(trim_s, dot_idx + 1, e_idx, false, false))) &&
                is_integer(trim_s, e_idx + 1, trim_s.size(), true, false);
      // cout << "2 " << std::boolalpha << result << endl;
    } else {
      result &=
          (is_integer(trim_s, 0, dot_idx, true, true) &&
           is_integer(trim_s, dot_idx + 1, trim_s.size(), false, false)) ||
          (is_integer(trim_s, 0, dot_idx, true, false) &&
           is_integer(trim_s, dot_idx + 1, trim_s.size(), false, true));
      // cout << "3 " << std::boolalpha << result << endl;
    }
  } else {
    if (e_idx != -1) {
      result &= is_integer(trim_s, 0, e_idx, true, false) &&
                is_integer(trim_s, e_idx + 1, trim_s.size(), true, false);
      // cout << "4 " << std::boolalpha << result << endl;
    } else {
      result &= is_integer(trim_s, 0, trim_s.size(), true, false);
      // cout << "5 " << std::boolalpha << result << endl;
    }
  }
  return result;
}

bool is_integer(const string& s, int begin, int end, bool allow_sign,
                bool allow_no_digit) {
  if (begin < end) {
    if (!allow_sign && (s[begin] == '+' || s[begin] == '-')) {
      return false;
    }
    if (allow_sign) {
      if (s[begin] == '+' || s[begin] == '-') {
        ++begin;
      } else if (!isdigit(s[begin])) {
        return false;
      }
    }
  }

  for (size_t i = begin; i < end; ++i) {
    if (!isdigit(s[i])) {
      return false;
    }
  }
  return end > begin || allow_no_digit;
}

string trim_space(const string& s) {
  const auto space_begin = s.find_first_not_of(' ');
  if (space_begin == string::npos) {
    return "";
  }

  const auto space_end = s.find_last_not_of(' ');
  const auto range = space_end - space_begin + 1;
  return s.substr(space_begin, range);
}
// @exclude

void small_test() {
  assert(is_valid_number("0"));
  assert(is_valid_number(" 0.1"));
  assert(!is_valid_number("abc"));
  assert(!is_valid_number("1 a"));
  assert(is_valid_number("2e10"));
  assert(!is_valid_number("e"));
  assert(is_valid_number(".1"));
  assert(!is_valid_number("."));
  assert(!is_valid_number(".e1"));
  assert(!is_valid_number("-."));
}

int main(int argc, char* argv[]) {
  small_test();
  if (argc == 2) {
    cout << argv[1] << "is" << (is_valid_number(argv[1]) ? " " : " not")
         << "a valid number." << endl;
  }
  return 0;
}
