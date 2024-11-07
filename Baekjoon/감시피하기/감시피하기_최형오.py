student = []
room = []
result = False

n = int(input())

for i in range(n):
    row = list(map(str, input().split()))
    room.append(row)
    for j in range(n):
        if row[j] == 'S':
            student.append([i, j])


def backtracking(cnt):
    global result

    if cnt == 3:
        if bfs():
            result = True
            return
    else:
        for x in range(n):
            for y in range(n):
                if room[x][y] == "X":
                    room[x][y] = "O"
                    backtracking(cnt+1)
                    room[x][y] = "X"


def bfs():
    dx = [1, -1, 0, 0]
    dy = [0, 0, 1, -1]

    for s in student:
        for k in range(4):
            nx, ny = s

            while 0<= nx < n and 0 <= ny < n:
                if room[nx][ny] == "O":
                    break

                if room[nx][ny] == "T":
                    return False
                
                nx += dx[k]
                ny += dy[k]

    return True

backtracking(0)

if result:
    print("YES")
else:
    print("NO")