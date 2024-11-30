def is_prime_number(k):
    # 1은 소수가 아님
    if k == 1:
        return False

    for i in range(2, int(k**(1/2))+1):
        if k % i == 0:
            return False
    return True


m, n = map(int, input().split())

for num in range(m, n+1):
    if is_prime_number(num):
        print(num)
