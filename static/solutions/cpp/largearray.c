// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <stdio.h>
#include <time.h>
#include <assert.h>
#include <stdlib.h>

// @include
#define N 3000000000
char A[N];
// @exclude

int comp(const void* a, const void* b) {
  if (*(char*)a == *(char*)b)
    return 0;
  else if (*(char*)a < *(char*)b)
    return -1;
  else
    return 1;
}

int comp_unsigned_int(const void* a, const void* b) {
  if (*(unsigned int*)a == *(unsigned int*)b) {
    return 0;
  } else if (*(unsigned int*)a < *(unsigned int*)b) {
    return -1;
  } else {
    return 1;
  }
}

void init(char A[]) {
  srand(time(NULL));
  int i;
  unsigned int P[127];
  for (i = 0; i < 127; ++i) {
    P[i] = rand() % N;
  }
  qsort(P, 127, sizeof(unsigned int), comp_unsigned_int);
  unsigned int pre = 0;
  char val;
  for (val = 0; val < 127; ++val) {
    while (pre < P[val]) {
      A[pre++] = val;
    }
  }
  while (pre < N) {
    A[pre++] = val;
  }
  // qsort(A, N, sizeof(char), comp);  // too slow to sort
}

int BinarySearch(const char key, const char* A, int L, int U) {
  while (L <= U) {
    int M;
    // Change the following logic to M = (L + U) / 2 will fail this program.
    if ((L > 0 && U > 0 || L < 0 && U < 0)) {
      M = L + (U - L) / 2;
    } else {
      M = (L + U) / 2;
    }
    if (A[M] < key) {
      L = M + 1;
    } else if (A[M] > key) {
      U = M - 1;
    } else {
      return M;
    }
  }
  return -1;
}

int main(int argc, char* argv[]) {
  char key = 123;
  init(A);
  // @include
char* B = (A + 1500000000);
int L = -1499000000;
int U = 1499000000;
// On a 32-bit machine (U - L) = -1296967296 because the actual value,
// 2998000000 is larger than 2^31 - 1. Consequently, the bsearch function
// called below sets m to -2147483648 instead of 0, which leads to an
// out-of-bounds access, since the most negative index that can be applied
// to B is -1500000000.
int result = BinarySearch(key, B, L, U);
  // @exclude
  if (result != -1) {
    printf("B[%d] = %d\n", result, B[result]);
    assert(B[result] == key);
  }
  return 0;
}
