// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

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
  int age;
  string name;
};

void GroupByAge(vector<Person>* people) {
  unordered_map<int, int> age_to_count;
  for (const Person& p : *people) {
    ++age_to_count[p.age];
  }
  unordered_map<int, int> age_to_offset;
  int offset = 0;
  for (const auto& p : age_to_count) {
    age_to_offset[p.first] = offset;
    offset += p.second;
  }

  while (!age_to_offset.empty()) {
    auto from = age_to_offset.begin();
    auto to = age_to_offset.find((*people)[from->second].age);
    swap((*people)[from->second], (*people)[to->second]);
    // Use age_to_count to see when we are finished with a particular age.
    --age_to_count[to->first];
    if (age_to_count[to->first] > 0) {
      ++to->second;
    } else {
      age_to_offset.erase(to);
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

static void SimpleTest() {
  vector<Person> people = {Person({20, "foo"}), Person({10, "bar"}),
                           Person({20, "widget"}), Person({20, "something"})};

  GroupByAge(&people);
  if (people[0].age == 10) {
    assert(people[1].age == 20 && people[2].age == 20 && people[3].age == 20);
  } else {
    assert(people[1].age == 20 && people[2].age == 20 && people[3].age == 10);
  }
}

int main(int argc, char* argv[]) {
  SimpleTest();
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
    unordered_set<int> age_set;
    for (const Person& p : people) {
      age_set.emplace(p.age);
    }

    GroupByAge(&people);

    // Check the correctness of sorting.
    int diff_count = 1;
    for (int i = 1; i < people.size(); ++i) {
      if (people[i].age != people[i - 1].age) {
        ++diff_count;
      }
    }
    assert(diff_count == age_set.size());
  }
  return 0;
}
