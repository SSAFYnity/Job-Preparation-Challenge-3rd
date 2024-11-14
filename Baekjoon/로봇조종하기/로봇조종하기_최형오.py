# import sys
# sys.setrecursionlimit(1000000)
# n, m = map(int, input().split())

# dx = [1, -1, 0]
# dy = [0, 0, 1]

# def detective(x, y, sum):
#     if cur_arr[y][x] > sum:
#         return
#     else:
#         cur_arr[y][x] = sum

#     if x == n-1 and y == m-1:
#         return
    
#     for i in range(3):
#         nx = x + dx[i]
#         ny = y + dy[i]

#         if 0<= nx < n and 0<= ny < m:
#             if not visited[ny][nx]:
#                 visited[ny][nx] = True
#                 detective(ny, nx, sum + map_arr[ny][nx])
#                 visited[ny][nx] = False

# visited = [[False for _ in range(n)] for _ in range(m)]
# cur_arr = [[0 for _ in range(n)] for _ in range(m)]

# visited[0][0] = True

# map_arr = []
# for _ in range(n):
#     map_arr.append(list(map(int, input().split())))

# detective(0, 0, map_arr[0][0])

# print(cur_arr[n-1][m-1])


import sys
input = sys.stdin.readline

N, M = map(int, input().strip().split())
board = [list(map(int, input().strip().split())) for i in range(N)]

for j in range(1, M) :
    board[0][j] += board[0][j-1]
        
for i in range(1, N) :   
    startLeft = [board[i][j]+board[i-1][j] for j in range(M)]
    startRight = [board[i][j]+board[i-1][j] for j in range(M)]
    
    for j in range(1, M) : 
        startLeft[j] = max(startLeft[j], startLeft[j-1]+board[i][j]) 
    
    for j in range(M-2, -1, -1) : 
        startRight[j] = max(startRight[j], startRight[j+1]+board[i][j])
    
    for j in range(M) : 
        board[i][j] = max(startLeft[j], startRight[j])

print(board[N][M])