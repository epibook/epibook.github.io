// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <random>
#include <stdexcept>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::length_error;
using std::random_device;
using std::swap;
using std::uniform_int_distribution;
using std::vector;

/*
void CannotModify(const vector<int>& A) {
  A[0] = 1;
}
*/

// Not recommended.
void Modify(vector<int>& A) {
  A[0] = 5;
}

// Recommended.
void AnotherModify(vector<int>* A) {
  auto& B = *A;
  B[0] = 4;
}

// Copy.
void Copy(vector<int> A) {
  A[0] = 10;
}

void TwoLevelModify(const vector<vector<int>>& B) {
  auto& C = B;
}

int main(void) {
  vector<int> A = {1, 3, 5, 7, 10};
  //CannotModify(A);
  Modify(A);
  cout << "A[0] = " << A[0] << endl;
  AnotherModify(&A);
  cout << "A[0] = " << A[0] << endl;
  Copy(A);
  cout << "after copy A[0] = " << A[0] << endl;
  cout << "A[0] = " << A.at(0) << endl;
  cout << "A[10] = " << A[10] << endl;
  //cout << "A[10] = " << A.at(10) << endl;

  vector<vector<int>> B = {{2, 3}, {4, 5}};
  cout << "B[0][0] = " << B[0][0] << endl;
  TwoLevelModify(B);
  cout << "B[0][0] = " << B[0][0] << endl;
  return 0;
}
