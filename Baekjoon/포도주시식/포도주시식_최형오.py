n = int(input())

maparr = [0 for _ in range(10005)]
result  = [0 for _ in range(10005)]
for i in range(n):
    maparr[i+1] = int(input())

result[1] = maparr[1]
result[2] = maparr[1] + maparr[2]

for i in range(3, n+1):
    result[i] = max((result[i-3] + maparr[i-1]) + maparr[i], result[i-2] + maparr[i], maparr[i]+ maparr[i-1] + result[i-4])

print(max(result[n-1], result[n]))

