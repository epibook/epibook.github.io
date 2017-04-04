# Tail.cpp 848813e190b1b85a8e75107fe8513c3be38ad1a9
import sys
import os


# @include
def tail(file_name, N):
    with open(file_name, 'rb') as file_ptr:
        # Note: you can seek from the file's end only in binary mode.
        file_size = file_ptr.seek(0, os.SEEK_END)
        newline_count = 0
        last_n_lines = []
        # Reads file in reverse looking for '\n'.
        for i in range(file_size):
            file_ptr.seek(-1 - i, os.SEEK_END)
            c = file_ptr.read(1)
            if c == b'\n':
                newline_count += 1
                if newline_count > N:
                    break
            last_n_lines.append(c)

    return b''.join(reversed(last_n_lines))


# @exclude


def small_test():
    with open('test_Tail.txt', 'wb') as ofs:
        ofs.write(
            b"A free soft copy sample of Elements of Programming Interviews can be found at bit.ly/epi-sample\n\n"
            b"The core of Elements of Programming Interviews (EPI) is a collection of 300 problems with detailed solutions, including over 150 figures and 250 tested programs. The problems are representative of the questions asked at interviews at the most exciting companies. They are well-motivated, thought-provoking and fun to solve!\n\n"
            b"EPI includes a summary of the nontechnical aspects of interviewing, including common mistakes, strategies for a great interview, the interviewer's perspective, negotiating the best offer, and much more.\n\n"
            b"Since different candidates have different time constraints, EPI includes a study guide with several study scenarios, ranging from weekend Hackathon to semester long preparation with a recommended a subset of problems for each scenario.\n\n")
    res = tail('test_Tail.txt', 3)
    assert res == b'\nSince different candidates have different time constraints, EPI includes a study guide with several study scenarios, ranging from weekend Hackathon to semester long preparation with a recommended a subset of problems for each scenario.\n\n'
    os.remove('test_Tail.txt')


def main():
    small_test()
    print('Usage: file name and tail count')
    if len(sys.argv) == 2:
        file_name = sys.argv[1]
        tail_count = 10
    elif len(sys.argv) == 3:
        file_name = sys.argv[1]
        tail_count = int(sys.argv[2])
    else:
        sys.exit(1)

    output = tail(file_name, tail_count)
    print(output)
    command = 'tail -n %d %s' % (tail_count, file_name)
    print('command =', command)
    os.system(command)


if __name__ == '__main__':
    main()
