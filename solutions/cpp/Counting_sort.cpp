// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <functional>
#include <iostream>
#include <random>
#include <string>
#include <unordered_map>
#include <unordered_set>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::hash;
using std::max;
using std::random_device;
using std::swap;
using std::string;
using std::uniform_int_distribution;
using std::unordered_map;
using std::unordered_set;
using std::vector;

// @include
struct Person {
  int key;
  string name;
};

void CountingSort(vector<Person>* people) {
  unordered_map<int, int> key_to_count;
  for (const Person& p : *people) {
    ++key_to_count[p.key];
  }
  unordered_map<int, int> key_to_offset;
  int offset = 0;
  for (const auto& p : key_to_count) {
    key_to_offset[p.first] = offset;
    offset += p.second;
  }

  while (key_to_offset.size()) {
    auto from = key_to_offset.begin();
    auto to = key_to_offset.find((*people)[from->second].key);
    swap((*people)[from->second], (*people)[to->second]);
    // Use key_to_count to see when we are finished with a particular key.
    if (--key_to_count[to->first]) {
      ++to->second;
    } else {
      key_to_offset.erase(to);
    }
  }
}
// @exclude

string RandString(int len) {
  string ret;
  default_random_engine gen((random_device())());
  uniform_int_distribution<int> char_dis(0, 25);
  while (len--) {
    ret += 'a' + char_dis(gen);
  }
  return ret;
}

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  for (int times = 0; times < 1000; ++times) {
    int size;
    if (argc == 2 || argc == 3) {
      size = atoi(argv[1]);
    } else {
      uniform_int_distribution<int> dis(1, 10000);
      size = dis(gen);
    }
    int k;
    if (argc == 3) {
      k = atoi(argv[2]);
    } else {
      uniform_int_distribution<int> dis(1, size);
      k = dis(gen);
    }
    vector<Person> people;
    uniform_int_distribution<int> k_dis(0, k - 1);
    uniform_int_distribution<int> len_dis(1, 10);
    for (int i = 0; i < size; ++i) {
      people.emplace_back(Person{k_dis(gen), RandString(len_dis(gen))});
    }
    unordered_set<int> key_set;
    for (const Person& p : people) {
      key_set.emplace(p.key);
    }

    CountingSort(&people);

    // Check the correctness of sorting.
    int diff_count = 1;
    for (int i = 1; i < people.size(); ++i) {
      if (people[i].key != people[i - 1].key) {
        ++diff_count;
      }
    }
    assert(diff_count == key_set.size());
  }
  return 0;
}
