arr = []
teachers = []
N = int(input())
for idx in range(N):
    ipt = list(input().split())
    arr.append(ipt)
    for jdx, elm in enumerate(ipt):
        if elm == "T":
            teachers.append((idx,jdx))

from itertools import combinations

delta = [(0,1),(1,0),(-1,0),(0,-1)]

for blocks in combinations([(x,y) for x in range(N) for y in range(N) if arr[x][y]=="X"],3):

    for bx, by in blocks:
        arr[bx][by] = "O"
    
    flag = False
    for tx,ty in teachers:
        for dx, dy in delta:
            nx, ny = tx, ty
            while 0 <= nx < N and 0 <= ny < N:
                if arr[nx][ny] == "O":
                    break
                if arr[nx][ny] == "S":
                    flag = True
                    break            
                nx += dx
                ny += dy
            if flag:
                break
        if flag:
            break
    for bx, by in blocks:
        arr[bx][by] = "X"

    if not flag:
        print("YES")
        exit()
print("NO")


