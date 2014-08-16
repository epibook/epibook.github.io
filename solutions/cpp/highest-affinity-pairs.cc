// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <fstream>
#include <iostream>
#include <random>
#include <set>
#include <string>
#include <utility>
#include <unordered_map>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::ifstream;
using std::ofstream;
using std::pair;
using std::random_device;
using std::set;
using std::stoi;
using std::string;
using std::uniform_int_distribution;
using std::unordered_map;
using std::vector;

// @include
pair<string, string> HighestAffinityPair(ifstream* ifs) {
  // Creates a mapping from pages to distinct users.
  unordered_map<string, set<string>> page_users_map;
  string page, user;
  while (*ifs >> page >> user) {
    page_users_map[page].emplace(user);
  }

  pair<string, string> result;
  int max_count = 0;
  // Compares all pairs of pages to users maps.
  for (auto a = page_users_map.begin(); a != page_users_map.end(); ++a) {
    auto b = a;
    for (advance(b, 1); b != page_users_map.end(); ++b) {
      vector<string> intersect_users;
      set_intersection(a->second.begin(), a->second.end(), b->second.begin(),
                       b->second.end(), back_inserter(intersect_users));

      // Updates result if we find larger intersection.
      if (intersect_users.size() > max_count) {
        max_count = intersect_users.size();
        result = {a->first, b->first};
      }
    }
  }
  return result;
}
// @exclude

string RandString(int len) {
  default_random_engine gen((random_device())());
  uniform_int_distribution<int> dis('a', 'z');
  string ret;
  while (len--) {
    ret += dis(gen);
  }
  return ret;
}

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  int n;
  if (argc == 2) {
    n = stoi(argv[1]);
  } else {
    uniform_int_distribution<int> dis(1, 10000);
    n = dis(gen);
  }
  ofstream ofs("logs.txt");
  for (int i = 0; i < n; ++i) {
    string name = RandString(5);
    transform(name.begin(), name.end(), name.begin(), toupper);
    ofs << name << " " << RandString(5) << endl;
  }
  ofs.close();
  ifstream ifs("logs.txt");
  auto result = HighestAffinityPair(&ifs);
  cout << result.first << " " << result.second << endl;
  return 0;
}
