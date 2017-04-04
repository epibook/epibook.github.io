# @include
def change_making(cents):
    COINS = [100, 50, 25, 10, 5, 1]
    num_coins = 0
    for coin in COINS:
        num_coins += cents / coin
        cents %= coin
    return num_coins
# @exclude


def main():
    change_making(100) == 1
    change_making(101) == 1
    change_making(68) == 6
    change_making(13142) == 136


if __name__ == '__main__':
    main()
