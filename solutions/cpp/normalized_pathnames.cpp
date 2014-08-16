// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <stdexcept>
#include <string>
#include <sstream>
#include <vector>

using std::cout;
using std::endl;
using std::exception;
using std::invalid_argument;
using std::stringstream;
using std::string;
using std::vector;

// @include
string NormalizedPathNames(const string& path) {
  vector<string> s;  // Uses vector as a stack.
  // Special case: starts with "/", which is an absolute path.
  if (path.front() == '/') {
    s.emplace_back("/");
  }

  stringstream ss(path);
  string token;
  while (getline(ss, token, '/')) {
    if (token == "..") {
      if (s.empty() || s.back() == "..") {
        s.emplace_back(token);
      } else {
        if (s.back() == "/") {
          throw invalid_argument("Path error");
        }
        s.pop_back();
      }
    } else if (token != "." && token != "") {  // Name.
      for (const char& c : token) {
        if (!isalnum(c)) {
          throw invalid_argument("Invalid directory name");
        }
      }
      s.emplace_back(token);
    }
  }

  string normalized_path("");
  if (!s.empty()) {
    auto it = s.cbegin();
    normalized_path += *it++;
    while (it != s.cend()) {
      if (*(it - 1) != "/") {  // Previous one is not an absolute path.
        normalized_path += "/";
      }
      normalized_path += *it++;
    }
  }
  return normalized_path;
}
// @exclude

int main(int argc, char* argv[]) {
  assert(NormalizedPathNames("123/456") == string("123/456"));
  assert(NormalizedPathNames("/123/456") == string("/123/456"));
  assert(NormalizedPathNames("usr/lib/../bin/gcc") == string("usr/bin/gcc"));
  assert(NormalizedPathNames("./../") == string(".."));
  assert(NormalizedPathNames("../../local") == string("../../local"));
  assert(NormalizedPathNames("./.././../local") == string("../../local"));
  try {
    NormalizedPathNames("/foo.txt");
  }
  catch (const exception& e) {
    cout << e.what() << endl;
  }
  try {
    NormalizedPathNames("/..");
  }
  catch (const exception& e) {
    cout << e.what() << endl;
  }
  try {
    NormalizedPathNames("/cpp_name/bin/");
  }
  catch (const exception& e) {
    cout << e.what() << endl;
  }
  assert(NormalizedPathNames("scripts//./../scripts/awkscripts/././") ==
         string("scripts/awkscripts"));
  if (argc == 2) {
    cout << NormalizedPathNames(argv[1]) << endl;
  }
  return 0;
}
