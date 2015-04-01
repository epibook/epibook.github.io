---
layout: post
title:  Warming Up
description: Warming Up
permalink: Warming-Up

css:
    - /lib/lightbox/css/lightbox.css
js:
    - //ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js
    - /lib/lightbox/js/lightbox.js
---

{{ page.title }}
================

The programming exercises in EPI are representative of what you will be asked 
in an interview. We do not provide textbook-style review questions.

In this document, we present a small set of programming exercises suitable for
someone who needs to review basics. (You are unlikely to be 
asked these questions - their purpose is to bootstrap reading EPI.) 

When you write your solutions to these exercises, please code to the following constraints:
<ol>
<li>Do not use library calls.
<li>Do not try to design clever solutions. (This means it is fine to use brute-force approaches.)
<li>Assume valid inputs, and ignore internal errors like overflow, IO exceptions, etc.
<li>Write unit tests for your programs.
<li>Analyze your programs for time and space complexity. 
</ol>


<h3>Exercises, one for each EPI chapter</h3>
<ol>
<li> Primitive types: write a program that takes as input an integer, N, and prints
all the integers from 1 to N, replacing numbers divisible by 3 with "fizz", numbers divisible by
5 with "buzz", and numbers divisible by both with "fizz buzz".
<li> Arrays: Write a program that tests if a 2D square array is symmetric about the diagonal
from (0,0) to (n-1,n-1).
<li> Strings: Write a program to find the longest substring that consists of a single
character in an input string.
<li> Linked Lists: Implement a doubly linked list of integers class. Specifically, support create list, lookup key,
insert key, delete key, and delete node functions.
<li> Stacks and Queues: Write a programt to evaluate arithmetical expressions that use + and * applied
to nonnegative integer arguments. Expressions are in reverse-Polish notation, e.g., 3 4 + 5 *, 1 3 + 5 7 + *.
<li> Binary Trees: Write inorder, preorder and postorder traversal methods for a binary tree. (You will need
to implement a class suitable for representing binary trees, but do not need to implement
add, lookup, delete, etc. methods.)
<li> Heaps: Write a program that builds a max-heap from an integer array. (You will need to implement
a class suitable for representing heaps, but do not need to implement extract-max, insert key, etc.)
<li> Searching: Write a program to perform binary search over a sorted array.
<li> Hash tables: Write a program that finds the most common object in an array of objects. The 
objects consists of pairs of strings. Treat strings as being the same if they are equal when converted to lower case.
<li> BSTs: Write a program that searches a BST on integer keys for a given value. (You will need to
implement a class suitable for representing BSTs, but do not need to implement
add, lookup, delete, etc. methods.)
<li> Write a recursive program that takes as input positive integers x and N, and returns x to the power N. 
You should use O(log N) multiplications.
<li> Dynamic Programming: Write a program that takes a positive integer N, and returns the number
of binary strings of length N such that there are no consecutive 1s. For example, if N = 3, the result
is 5, corresponding to the strings 000, 001, 010, 100, 101.
<li> Greedy Algorithms and Invariants: Write a program that takes an input a positive integer
N, and returns the minimum number of coins in the US coinage system to create N cents. For example, if
N = 37, the answer is 4, corresponding to a quarter, a dime, and two pennies.
<li> Graphs: Implement Depth First Search and Breadth First Search. (You will need to implement
classes suitable to representing graphs.)
<li> Parallel Computing: Write a program which uses two threads to print the numbers from
1 to 100 in order. One thread can only print odd numbers, the other can only print even numbers.
</ol>

