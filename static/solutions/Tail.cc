// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <fstream>
#include <iostream>
#include <string>

using std::cout;
using std::endl;
using std::fstream;
using std::ios;
using std::ofstream;
using std::string;
using std::to_string;

// @include
string tail(const string& file_name, int N) {
  fstream file_ptr(file_name.c_str());

  file_ptr.seekg(0, ios::end);
  int file_size = file_ptr.tellg(), newline_count = 0;
  string last_N_lines;
  // Reads file in reverse looking for '\n'.
  for (int i = 0; i < file_size; ++i) {
    file_ptr.seekg(-1 - i, ios::end);
    char c;
    file_ptr.get(c);
    if (c == '\n') {
      ++newline_count;
      if (newline_count >= N) {
        break;
      }
    }
    last_N_lines.push_back(c);
  }

  reverse(last_N_lines.begin(), last_N_lines.end());
  return last_N_lines;
}
// @exclude

void SmallTest() {
  ofstream ofs("test_Tail.txt");
  string L1 = "The first line";
  string L2 = "The second line";
  string L3 = "The third line";
  string L4 = "The fourth line";
  ofs << L1 << endl;
  ofs << L2 << endl;
  ofs << L3 << endl;
  ofs << L4;
  ofs.close();
  auto res = tail("test_Tail.txt", 1);
  assert(!res.compare(L4));
  res = tail("test_Tail.txt", 2);
  assert(!res.compare(L3 + "\n" + L4));
  res = tail("test_Tail.txt", 3);
  assert(!res.compare(L2 + "\n" + L3 + "\n" + L4));
  res = tail("test_Tail.txt", 4);
  assert(!res.compare(L1 + "\n" + L2 + "\n" + L3 + "\n" + L4));
  remove("test_Tail.txt");
}

int main(int argc, char* argv[]) {
  SmallTest();
  cout << "Usage: file name and tail count" << endl;
  int tail_count = 10;
  string file_name;
  if (argc == 2) {
    file_name = argv[1];
  } else if (argc == 3) {
    file_name = argv[1];
    tail_count = atoi(argv[2]);
  } else {
    exit(1);
  }
  string output = tail(file_name, tail_count);
  cout << output;
  string command = "tail -n " + to_string(tail_count) + " " + file_name;
  cout << "command = " << command << endl;
  system(command.c_str());
  return 0;
}
