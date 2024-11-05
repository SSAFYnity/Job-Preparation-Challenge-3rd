H, W, X, Y = map(int, input().split())
arr = [list(map(int, input().split())) for _ in range(H + X)]

for i in range(X, H):
    for j in range(Y, W):
        arr[i][j] -= arr[i - X][j - Y]

for row in arr[:H]:
    print(*row[:W])
