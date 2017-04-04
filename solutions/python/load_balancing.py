import sys
import random


# @include
def decide_load_balancing(user_file_size, server_num):
    def greedy_assignment(limit, assign_res):
        server_idx = 0
        for file in user_file_size:
            while server_idx < server_num and file + \
                    assign_res[server_idx] > limit:
                server_idx += 1

            if server_idx >= server_num:
                return False
            else:
                assign_res[server_idx] += file
        return True
    # Uses binary search to find the assignment with minimized maximum load.
    l = 0
    r = sum(user_file_size)
    feasible_assignment = []
    while l <= r:
        m = l + ((r - l) // 2)
        assign_res = [0] * server_num
        is_feasible = greedy_assignment(m, assign_res)
        if is_feasible:
            feasible_assignment = assign_res
            r = m - 1
        else:
            l = m + 1
    return feasible_assignment
# @exclude


def main():
    if len(sys.argv) == 3:
        n = int(sys.argv[1])
        m = int(sys.argv[2])
    else:
        n = random.randint(1, 50000)
        m = random.randint(1, n)
    print(n, m)
    # stores user i's data size.
    users = [random.randint(1, 1000) for i in range(n)]
    print(*users)
    res = decide_load_balancing(users, m)
    print(*res)


if __name__ == '__main__':
    main()
