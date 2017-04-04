// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#ifndef SOLUTIONS_GCD2_H_
#define SOLUTIONS_GCD2_H_

namespace GCD2 {

// @include
long long GCD(long long x, long long y) { return y == 0 ? x : GCD(y, x % y); }
// @exclude

}  // namespace GCD2

#endif  // SOLUTIONS_GCD2_H_
