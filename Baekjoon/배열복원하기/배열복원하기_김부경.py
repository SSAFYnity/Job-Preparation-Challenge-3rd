import sys
input = sys.stdin.readline

h, w, x, y = map(int, input().split())
arrA = [[0]*w for _ in range(h)]
arrB = [list(map(int, input().split())) for _ in range(h+x)]

for i in range(h):
    for j in range(w):
        if i >= x and j >= y:
            arrA[i][j] = arrB[i][j] - arrA[i-x][j-y]
        else:
            arrA[i][j] = arrB[i][j]

for arr in arrA:
    print(" ".join(map(str, arr)))
