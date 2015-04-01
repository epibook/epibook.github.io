---
layout: post
title:  Programming Languages
description: General facts about C, C++, Java, Python
permalink: Programming-Languages
css:
    - /lib/lightbox/css/lightbox.css
js:
    - //ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js
    - /lib/lightbox/js/lightbox.js
---

{{ page.title }}
================

<h3>Core language</h3>

<ol>
<li> What are the types in C, Java, C++, and Perl?  How many bits does it take
to represent the basic types?
<li> What do floating point numbers look like in
memory? Specifically, how many bits are there in a double and
what sequence to the bits appear in?
<li> What is twos-complement notation?
<li> How would you implement a bit array class?
<li> Does the check x == x + 1 always return false for integer x?
<li> What does a C struct look like in memory?  What does a C++
object look like in memory? What does a Java object look like in
memory?
<li> What is the difference between an interpreted and a compiled language?
<li> What is garbage collection?
<li> How would you determine if call stack grows up or down relative
to memory addresses?
<li> Give an example of a memory leak in Java.
<li> What is tail recursion? How can it be removed automatically?
<li> Is the natural recursive program for computing $n!$ tail recursive?
<li> Your manager reads an online article that says it is
$10\times$ faster to code in Python than in C++. He wants you
to code exclusively in Python from now on. What would you say to him?
<li> What does an executable look like as a sequence of bytes?
</ol>

<h3>Libraries</h3>

A programmer who regularly implements complex algorithms such as KMP
string matching or the Dijkstra shortest path computation quickly
will not advance very far. Solutions to such problems are well-known and have
high quality, thoroughly tested, and debugged implementations, often
available as open source. Programmers should know and use these libraries.

<ol>
<li>Give an example of a function which is in the C standard library.
<li>Give an example of a commonly used function which is not in the C standard library.
<li>What library would you use to determine the current date in Java?
<li>What library would you use in Java if you had to implement a tinyurl service?
<li>How does STL implement sets?
<li>How does the library code compute trigonometric functions?
<li>The strtok(char *s1, char *s2) function in the C standard library successively
<li>returns occurrences of the characters in s2 in string s1; it returns null
<li>if there are no more occurrences.  What makes this a dangerous function to use in a multithreaded program?
</ol>

<h3>Programming language implementation</h3>

<ol>
<li> Give an example of a language which cannot be parsed by any computer.
<li> What problems does dynamic linkage solve? What problems does it introduce?
<li> What is a functional language?
<li> What is a virtual function?
<li> How is method dispatch implemented in Java?
<li> What is automatic garbage collection and how is it implemented?
<li> What is a type-safe language?
<li> What is the difference between a lexer and a parser?
<li> Give an example of a language which cannot be recognized by a lexer.
<li> Give an example of a language which cannot be parsed by a parser.
</ol>
