// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <sstream>
#include <stdexcept>
#include <string>
#include <vector>

using std::cout;
using std::endl;
using std::exception;
using std::invalid_argument;
using std::stringstream;
using std::string;
using std::vector;

// @include
string ShortestEquivalentPath(const string& path) {
  if (path.empty()) {
    throw invalid_argument("Empty string is not a valid path.");
  }

  vector<string> path_names;  // Uses vector as a stack.
  // Special case: starts with "/", which is an absolute path.
  if (path.front() == '/') {
    path_names.emplace_back("/");
  }

  stringstream ss(path);
  string token;
  while (getline(ss, token, '/')) {
    if (token == "..") {
      if (path_names.empty() || path_names.back() == "..") {
        path_names.emplace_back(token);
      } else {
        if (path_names.back() == "/") {
          throw invalid_argument("Path error");
        }
        path_names.pop_back();
      }
    } else if (token != "." && token != "") {  // Must be a name.
      path_names.emplace_back(token);
    }
  }

  string result;
  if (!path_names.empty()) {
    result = path_names.front();
    for (int i = 1; i < path_names.size(); ++i) {
      if (i == 1 && result == "/") {  // Avoid starting "//".
        result += path_names[i];
      } else {
        result += "/" + path_names[i];
      }
    }
  }
  return result;
}
// @exclude

int main(int argc, char* argv[]) {
  assert(ShortestEquivalentPath("123/456") == string("123/456"));
  assert(ShortestEquivalentPath("/123/456") == string("/123/456"));
  assert(ShortestEquivalentPath("usr/lib/../bin/gcc") ==
         string("usr/bin/gcc"));
  assert(ShortestEquivalentPath("./../") == string(".."));
  assert(ShortestEquivalentPath("../../local") == string("../../local"));
  assert(ShortestEquivalentPath("./.././../local") == string("../../local"));
  assert(ShortestEquivalentPath("/foo/../foo/./../") == string("/"));
  try {
    ShortestEquivalentPath("/foo.txt");
  } catch (const exception& e) {
    cout << e.what() << endl;
  }
  try {
    ShortestEquivalentPath("");
    assert(false);
  } catch (const exception& e) {
    cout << e.what() << endl;
  }
  try {
    ShortestEquivalentPath("/..");
  } catch (const exception& e) {
    cout << e.what() << endl;
  }
  try {
    ShortestEquivalentPath("/cpp_name/bin/");
  } catch (const exception& e) {
    cout << e.what() << endl;
  }
  assert(ShortestEquivalentPath("scripts//./../scripts/awkscripts/././") ==
         string("scripts/awkscripts"));
  if (argc == 2) {
    cout << ShortestEquivalentPath(argv[1]) << endl;
  }
  return 0;
}
