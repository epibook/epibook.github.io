// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <random>
#include <sstream>
#include <string>
#include <unordered_map>
#include <utility>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::ios;
using std::istringstream;
using std::random_device;
using std::string;
using std::uniform_int_distribution;
using std::unordered_map;
using std::vector;

string RandString(int len) {
  string ret;
  default_random_engine gen((random_device())());
  while (len--) {
    uniform_int_distribution<int> dis('a', 'z');
    ret += dis(gen);
  }
  return ret;
}

// @include
// Finds the candidates which may occur > n / k times.
vector<string> SearchFrequentItems(int k, istringstream* stream) {
  string buf;
  unordered_map<string, int> table;
  int n = 0;  // Count the number of strings.

  while (*stream >> buf) {
    ++table[buf], ++n;
    // Detecting k items in table, at least one of them must have exactly
    // one in it. We will discard those k items by one for each.
    if (table.size() == k) {
      auto it = table.begin();
      while (it != table.end()) {
        if (--(it->second) == 0) {
          table.erase(it++);
        } else {
          ++it;
        }
      }
    }
  }

  // Resets table for the following counting.
  for (auto& it : table) {
    it.second = 0;
  }

  // Resets the stream and read it again.
  stream->clear();
  stream->seekg(0, ios::beg);
  // Counts the occurrence of each candidate word.
  while (*stream >> buf) {
    auto it = table.find(buf);
    if (it != table.end()) {
      ++it->second;
    }
  }

  // Selects the word which occurs > n / k times.
  vector<string> ret;
  for (const auto& it : table) {
    if (it.second > static_cast<double>(n) / k) {
      ret.emplace_back(it.first);
    }
  }
  return ret;
}
// @exclude

void CheckAns(vector<string>* stream, int k, vector<string>* items) {
  sort(items->begin(), items->end());
  sort(stream->begin(), stream->end());
  int count = 1, idx = 0;
  for (int i = 1; i < stream->size(); ++i) {
    if ((*stream)[i].compare((*stream)[i - 1])) {
      if (count > static_cast<double>(stream->size()) / k) {
        assert(idx < items->size());
        assert((*stream)[i - 1].compare((*items)[idx++]) == 0);
      }
      count = 1;
    } else {
      ++count;
    }
  }
  if (count > static_cast<double>(stream->size()) / k) {
    assert((*stream).back().compare((*items)[idx++]) == 0);
  }
  assert(idx == items->size());
}

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  for (int times = 0; times < 1000; ++times) {
    cout << times << endl;
    vector<string> stream;
    int n, k;
    if (argc == 2) {
      n = atoi(argv[1]);
      uniform_int_distribution<int> k_dis(1, n);
      k = k_dis(gen);
    } else if (argc == 3) {
      n = atoi(argv[1]);
      k = atoi(argv[2]);
    } else {
      uniform_int_distribution<int> n_dis(0, 99999);
      n = n_dis(gen);
      uniform_int_distribution<int> k_dis(1, n);
      k = k_dis(gen);
    }
    for (int i = 0; i < n; ++i) {
      uniform_int_distribution<int> dis(1, 5);
      stream.emplace_back(RandString(dis(gen)));
    }
    string s;
    for (int i = 0; i < stream.size(); ++i) {
      s += stream[i];
      s += ' ';
    }
    istringstream stream(s);
    vector<string> items = SearchFrequentItems(k, &stream);
    CheckAns(&stream, k, &items);
  }
  return 0;
}
