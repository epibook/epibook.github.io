---
layout: post
title:  Debugging and Testing
description: Debugging and Testing
permalink: Debugging-and-Testing

css:
    - /lib/lightbox/css/lightbox.css
js:
    - //ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js
    - /lib/lightbox/js/lightbox.js
---

{{ page.title }}
================

Debugging and testing are topics which are not usually the focus of university teaching.
We highly recommend The Practice of Programming by Kernighan and
Pike, which teaches much more than just writing code. Specifically, it covers testing,
debugging, portability, performance, and design alternatives.

<ol>
<li> What was your last bug? What was your hardest bug?
<li> How would you debug a distributed program?
<li> A program works sometimes and fails other times - why?
<li> A program works sometimes and fails other times on the exact same input - why?
<li> How would you determine where a program spends most of its time?
<li> How does JUnit make the process of testing easier?
<li> List five ways in which C code can be non-portable. What can you do to make the code portable?
<li> Write tests for implementation of an isupper() function.
<li> Should you test private methods? Should you test one line methods?
<li> If you find and fix an error by adding debug code, should you remove the debug code afterwards? Should you leave them in with a conditional compilation flag or with a run time flag?
<li> What is a buffer overflow and how can hackers exploit it?
<li> How can you use Valgrind to solve segfault problems?
<li> How does Valgrind catch access uninitialized memory?
</ol>
