---
layout: post
title:  EPI Java Project
description:
     EPI Java
css:
    - /lib/lightbox/css/lightbox.css
js:
    - //ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js
    - /lib/lightbox/js/lightbox.js
---

{{ page.title }}
================

<h2>EPI in Java</h2>

We have a draft version of EPI in Java ready!

EPI is a community book - it's quality as well as content
is a testament to the enthusiasm and involvement
of our readers.

In this spirit, we'd like to ask readers to help us by providing
feedback on 1-2 chapters.

By acting as a reviewer, you will get 
<ul>
<li>a chance to perfect your interviewing skills,
<li> an early look at the book, 
<li> our undying gratitude, and, 
<li> a free hardcopy of the book.
if you are one of the first 25 (TODO: number?) reviewers,  TODO: make it pop, bold/red
</ul>

If you are interested, please sign up here (TODO).
Each issue you identify should be reported through a Google form, which you
can view here (TODO).

We expect reviewers to spend one to two afternoons going through
the chapter, and identify an issue per 1-2 pages. The text is 98\% the same
as for the current version - the big difference is in the programs. 

Examples of good reports reported by readers:
<ul>
<li> Typo: 3rd paragraph, 3rd line: "<0, 0, 2, 2, 3, 3, 5, 5, 7>". According to the input array, it should be "<0, 0, 2, 2, 3, 3, 6, 6, 7>", because when we reach day 6, the max profit is 14 - 8 = 6, not 14 - 9 = 5.
This means on the last line of this paragrapgh, the last two elements in M should be 8 and 6 (instead of 7 and 5).
<li> Comment: Why in problem 6.19 and the variant, we need to assume that n = 2^k? This condition is not really used in current solutions (unless you meant to discuss an alternate solution that uses divide and conquer to rotate the matrix by quarters).
<li> Clarification: Solution 12.14: I feel the parameters in recursive function are confusing. Wouldn't it be better to use two integers to keep track of the number of left parens we need and the number of right parens we need? If the first one is larger than 0, then we can add either left or right paren; otherwise we just add right parens. When both are 0, we save the valid prefix.  
</ul>

Examples of bad reports:
<ul>
<li> Comment: Array search problem algorithm is suboptimum.
<ul>
<li> Reason: Lacks specificity (which array search problem?), why is it suboptimum?
</ul>
<li> Typo: Program for buy-low sell high problem 3.17 on page 123 is buggy
<ul>
<li> Reason: does not say why it's buggy, e.g., does it fail on empty input? negative inputs? can you reproduce?
</ul>
<li> Comment: add more figures
<ul>
<li> Reason: prioritize, which solutions need figures the most? 
</ul>
</ul>

TODO: US only?
