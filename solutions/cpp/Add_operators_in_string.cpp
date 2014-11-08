// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <numeric>
#include <random>
#include <string>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::random_device;
using std::string;
using std::uniform_int_distribution;
using std::vector;

bool ExpressionSynthesisHelper(const vector<int>& A, int k, int cur, int level,
                               vector<int>* operand_list,
                               vector<char>* operator_list);
int RemainingInt(const vector<int>& A, int idx);
int Evaluate(vector<int> operand_list, const vector<char>& operator_list);

vector<int> operand_result;
vector<char> operator_result;

// @include
void ExpressionSynthesis(const vector<int>& A, int k) {
  vector<char> operator_list;
  vector<int> operand_list;
  if (!ExpressionSynthesisHelper(A, k, 0, 0, &operand_list, &operator_list)) {
    cout << "no answer" << endl;
  }
}

bool ExpressionSynthesisHelper(const vector<int>& A, int k, int cur,
                               int level, vector<int>* operand_list,
                               vector<char>* operator_list) {
  cur = cur * 10 + A[level];
  if (level == A.size() - 1) {
    operand_list->emplace_back(cur);
    if (Evaluate(*operand_list, *operator_list) == k) {  // Found a match.
      // @exclude
      operand_result = *operand_list, operator_result = *operator_list;
      // @include
      auto operand_it = operand_list->cbegin();
      cout << *operand_it++;
      for (const char& oper : *operator_list) {
        cout << ' ' << oper << ' ' << *operand_it++;
      }
      cout << " = " << k << endl;
      return true;
    }
    operand_list->pop_back();
  } else {
    // No operator.
    if (ExpressionSynthesisHelper(A, k, cur, level + 1, operand_list,
                                  operator_list)) {
      return true;
    }

    // Adds operator '+'.
    operand_list->emplace_back(cur);
    if (k - Evaluate(*operand_list, *operator_list) <=
        RemainingInt(A, level + 1)) {  // Pruning.
      operator_list->emplace_back('+');
      if (ExpressionSynthesisHelper(A, k, 0, level + 1, operand_list,
                                    operator_list)) {
        return true;
      }
      operator_list->pop_back();
    }
    operand_list->pop_back();

    // Adds operator '*'.
    operand_list->emplace_back(cur), operator_list->emplace_back('*');
    if (ExpressionSynthesisHelper(A, k, 0, level + 1, operand_list,
                                  operator_list)) {
      return true;
    }
    operand_list->pop_back(), operator_list->pop_back();
  }
  return false;
}

// Calculates the int represented by A[idx:].
int RemainingInt(const vector<int>& A, int idx) {
  int val = 0;
  for (size_t i = idx; i < A.size(); ++i) {
    val = val * 10 + A[idx];
  }
  return val;
}

int Evaluate(vector<int> operand_list, const vector<char>& operator_list) {
  // Evaluates '*' first.
  auto operand_it = operand_list.begin();
  for (const char& oper : operator_list) {
    if (oper == '*') {
      int product = *operand_it;
      operand_it = operand_list.erase(operand_it);
      product *= *operand_it;
      *operand_it = product;
    } else {
      ++operand_it;
    }
  }

  // Evaluates '+' second.
  return accumulate(operand_list.cbegin(), operand_list.cend(), 0);
}
// @exclude

void SmallTest() {
  vector<int> A = {1, 2, 3, 2, 5, 3, 7, 8, 5, 9};
  int k = 995;
  ExpressionSynthesis(A, k);
  vector<int> golden_operand_result = {123, 2, 5, 3, 7, 85, 9};
  assert(golden_operand_result.size() == operand_result.size());
  assert(equal(operand_result.begin(), operand_result.end(),
               golden_operand_result.begin()));
  vector<char> golden_operator_result = {'+', '+', '*', '*', '+', '*'};
  assert(golden_operator_result.size() == operator_result.size());
  assert(equal(operator_result.begin(), operator_result.end(),
               golden_operator_result.begin()));
}

int main(int argc, char* argv[]) {
  SmallTest();
  default_random_engine gen((random_device())());
  vector<int> A;
  for (size_t i = 0; i < 10; ++i) {
    uniform_int_distribution<int> A_dis(0, 9);
    A.emplace_back(A_dis(gen));
  }
  int k;
  uniform_int_distribution<int> k_dis(0, 999);
  k = k_dis(gen);
  cout << "A = ";
  for (size_t i = 0; i < A.size(); ++i) {
    cout << A[i];
  }
  cout << ", k = " << k << endl;
  ExpressionSynthesis(A, k);
  return 0;
}
