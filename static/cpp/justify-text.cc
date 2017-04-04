// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <cmath>
#include <iostream>
#include <random>
#include <string>
#include <utility>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::move;
using std::random_device;
using std::string;
using std::uniform_int_distribution;
using std::vector;

string JoinALineWithSpace(const vector<string>&, size_t, size_t, size_t);

// @include
vector<string> JustifyText(const vector<string>& words, size_t L) {
  size_t curr_line_start = 0, num_words_curr_line = 0, curr_line_length = 0;
  vector<string> result;
  for (size_t i = 0; i < words.size(); ++i) {
    // curr_line_start is the first word in the current line, and i is used to
    // identify the last word.
    ++num_words_curr_line;
    size_t lookahead_line_length =
        curr_line_length + words[i].size() + (num_words_curr_line - 1);
    if (lookahead_line_length == L) {
      result.emplace_back(
          JoinALineWithSpace(words, curr_line_start, i, i - curr_line_start));
      curr_line_start = i + 1, num_words_curr_line = 0, curr_line_length = 0;
    } else if (lookahead_line_length > L) {
      result.emplace_back(JoinALineWithSpace(words, curr_line_start, i - 1,
                                             L - curr_line_length));
      curr_line_start = i, num_words_curr_line = 1,
      curr_line_length = words[i].size();
    } else {  // lookahead_line_length < L.
      curr_line_length += words[i].size();
    }
  }

  // Handles the last line. Last line is to be left-aligned.
  if (num_words_curr_line > 0) {
    string line = JoinALineWithSpace(words, curr_line_start, words.size() - 1,
                                     num_words_curr_line - 1);
    line.append(L - curr_line_length - (num_words_curr_line - 1), ' ');
    result.emplace_back(line);
  }
  return result;
}

// Joins strings in words[start : end] with num_spaces spaces spread evenly.
string JoinALineWithSpace(const vector<string>& words, size_t start,
                          size_t end, size_t num_spaces) {
  size_t num_words_curr_line = end - start + 1;
  string line;
  for (size_t i = start; i < end; ++i) {
    line += words[i];
    --num_words_curr_line;
    size_t num_curr_space =
        ceil(static_cast<double>(num_spaces) / num_words_curr_line);
    line.append(num_curr_space, ' ');
    num_spaces -= num_curr_space;
  }
  line += words[end];
  line.append(num_spaces, ' ');
  return line;
}
// @exclude

void TestCase(const vector<string>& words, size_t L,
              const vector<string>& golden) {
  auto result = JustifyText(words, L);
  for (const string& s : result) {
    cout << "\"" << s << "\"" << endl;
  }
  cout << endl;
  assert(equal(result.begin(), result.end(), golden.begin(), golden.end()));
}

int main(int argc, char** argv) {
  vector<string> words = {"Text", "justification", "is", "trickier", "than",
                          "it",   "seems!"};
  vector<string> golden = {"Text          ", "justification ",
                           "is    trickier", "than it seems!"};
  size_t L = 14;
  cout << "L = " << L << endl;
  TestCase(words, L, golden);
  words = {"Listen", "to", "many,", "speak", "to", "a", "few."};
  golden = {"Listen", "to    ", "many, ", "speak ", "to   a", "few.  "};
  L = 6;
  cout << "L = " << L << endl;
  TestCase(words, L, golden);
  words = {"The",  "quick", "brown", "fox",  "jumped",
           "over", "the",   "lazy",  "dogs."};
  golden = {"The   quick", "brown   fox", "jumped over", "the    lazy",
            "dogs.      "};
  L = 11;
  cout << "L = " << L << endl;
  TestCase(words, L, golden);
  golden = {"The  quick brown", "fox  jumped over", "the lazy dogs.  "};
  L = 16;
  cout << "L = " << L << endl;
  TestCase(words, L, golden);
  golden = {"The  quick  brown", "fox  jumped  over", "the lazy dogs.   "};
  L = 17;
  cout << "L = " << L << endl;
  TestCase(words, L, golden);
  words = {"Hello", "World"};
  golden = {"Hello World   "};
  L = 14;
  TestCase(words, L, golden);
  return 0;
}
