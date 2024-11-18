import math
n, m = map(int, input().split())

def isPrime(num):
    if num <2:
        return False
    else:
        for i in range(2, int(math.sqrt(num)+1)):
            if num % i == 0:
                return False
        return True

for i in range(n, m+1):
    if isPrime(i):
        print(i)
