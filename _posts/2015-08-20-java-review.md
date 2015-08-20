---
layout: post
title:  EPI Java 
description:
     EPI 2.0
css:
    - /lib/lightbox/css/lightbox.css
js:
    - //ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js
    - /lib/lightbox/js/lightbox.js
---

{{ page.title }}
================

<h3>The draft version of EPI in Java is ready!</h3>

EPI is a community book - its content, quality, and very existence,
are a testament to the engagement and enthusiasm of its readers.

In this spirit, we are asking readers to help us by providing
feedback on 1-2 chapters on the Java draft.

By acting as a reviewer, you will get 
<ol>
<li>A chance to perfect your interviewing skills,
<li> an early look at the book, 
<li> our undying gratitude, and, 
<li> a <font color="red"><b>free hardcopy</b></font> of the book, <em>if</em> you are one of the first 25 reviewers or make great contributions to this review.
</ol>

If you are interested, please sign up via this <a href="http://goo.gl/forms/a9CsMJFXbn">Google form</a>.

We expect reviewers to spend one to two afternoons going through
the chapter, and identify an issue every 1 to 2 pages. The text is very similar to that of
the current version - the big difference is in the programs which are now in Java. 

The perfect is the enemy of the good - please send us your inputs as soon as you can. (We are hoping
to have a substantial amount of feedback by the end of August.)

Issues can be typos, language that is misleading, suboptimum solutions, bad programming practices - in short
anything that can improve the quality of the book.
Every individual issue you identify should be reported through a Google form, which you
can view <a href="http://goo.gl/forms/x0DJqUcP6e">here</a>.

Here are some examples of issues reported by readers.
Note how specific these suggestions are - they have details on where the issue was, what the problem was, what the right wording should be, etc.
<ol>
<li> Typo: 3rd paragraph, 3rd line: "<0, 0, 2, 2, 3, 3, 5, 5, 7>". According to the input array, it should be "<0, 0, 2, 2, 3, 3, 6, 6, 7>", because when we reach day 6, the max profit is 14 - 8 = 6, not 14 - 9 = 5.
This means on the last line of this paragraph, the last two elements in M should be 8 and 6 (instead of 7 and 5).
<li> Comment: Why in problem 6.19 and the variant, we need to assume that n = 2^k? This condition is not really used in current solutions (unless you meant to discuss an alternate solution that uses divide and conquer to rotate the matrix by quarters).
<li> Clarification: Solution 12.14: I feel the parameters in recursive function are confusing. Would it not be better to use two integers to keep track of the number of left parens we need and the number of right parens we need? If the first one is larger than 0, then we can add either left or right paren; otherwise we just add right parens. When both are 0, we save the valid prefix.  
<li> Error: Program in Solution 8.4 goes into an infinite loop
if there are duplicates entries in the input list. The problem statement should either explicitly
rule that out, or you should use a container which supports duplicate entries.
<li> Suggestion: Drop Variant 10.16.1, as it is effectively the same as Problem 10.18.
</ol>
