H, W, X, Y = map(int,input().split())
B = [list(map(int,input().split())) for _ in range(H+X)]
A = [[0]*W for _ in range(H)]
for x in range(H):
    for y in range(W):
        if X <= x and Y <= y:
            A[x][y] = B[x][y] - A[x-X][y-Y] 
        else:
            A[x][y] = B[x][y]
for _ in range(len(A)):
    print(*A[_])