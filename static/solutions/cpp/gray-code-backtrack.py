# from http://www.timswast.com/blog/2013/12/29/finding-gray-codes-with-backtracking-in-python/

def consecutives(aList):
    result = []
    for i in range(len(aList)-2):
        result.append((aList[i], aList[i+1])) 
    return result

def makeroot(bits):
    return [tuple([False for i in range(bits)])]

def hammingdistance(a, b):
    distance = 0
    for i, j in zip(a, b):
        if i != j:
            distance += 1
    return distance

def reject(code):
    # No repeat codes
    if len(set(code)) != len(code):
        return True
    # Each consecutive code point must be one
    # bit Hamming distance apart
    for c1, c2 in consecutives(code):
        if hammingdistance(c1, c2) != 1:
            return True
    return False

def accept(codelen, code):
    # Are we finished?
    if len(code) != codelen:
        return False
    return hammingdistance(code[0], code[-1]) == 1

def extensions(code):
    for i in range(len(code[-1])):
        nextcodepoint = list(code[-1])
        nextcodepoint[i] = not nextcodepoint[i]
        yield code + [tuple(nextcodepoint)]

def output_print(code):
    print "code:"
    for codepoint in code:
        for codeelement in codepoint:
            codechar = "1" if codeelement else "0"
            print codechar,
        print

def backtrack(codelen, code):
    if reject(code):
        return
    if accept(codelen, code):
        output(code)
        import sys
        sys.exit(0)
        return
    for extension in extensions(code):
        backtrack(codelen, extension)

number_of_gray_codes = 0
def output(code):
    global number_of_gray_codes
    output_print(code)
    number_of_gray_codes += 1

def resetoutput():
    global number_of_gray_codes
    print number_of_gray_codes
    number_of_gray_codes = 0

N = 10
backtrack(2**N, makeroot(N))
resetoutput()
