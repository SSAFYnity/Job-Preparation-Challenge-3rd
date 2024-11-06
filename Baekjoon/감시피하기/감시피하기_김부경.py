from itertools import combinations
import sys
input = sys.stdin.readline

# 해당 장애물에서 선생님 피하기가 가능한 지 확인하는 함수
def avoid_teacher():
    # 학생이 선생님을 피하면 True, 만나면 False
    # 선생님들 탐색 (선생님의 수는 5이하이므로 학생을 탐색하는 것 보다 효율적이다.)
    for x, y in teachers:
        # 특정 선생님이 4방향에서 감시 (학생 만나는지 체크)
        for k in range(4):
            nx = x + di[k]
            ny = y + dj[k]
            # 이동 가능하면 계속 탐색 (범위 안에 있고, 장애물을 만나지 않으면)
            while 0 <= nx < n and 0 <= ny < n and (nx, ny) not in obstacle:
                # 선생님과 학생이 만나면 False
                if graph[nx][ny] == "S":
                    return False
                # k방향으로 한 칸 이동
                nx += di[k]
                ny += dj[k]
    # 학생이 선생님을 피하면 True
    return True


n = int(input())
graph = []
# 선생님 위치 저장
teachers = []
# 장애물 후보 위치 저장
candidates = []
for i in range(n):
    row = list(input().split())
    for j in range(n):
        if row[j] == 'X':
            candidates.append((i, j))
        elif row[j] == 'T':
            teachers.append((i, j))
    graph.append(row)

# 상하좌우
di = [1, -1, 0, 0]
dj = [0, 0, -1, 1]
# 장애물 후보 중 3개 선택한 경우
obstacles = combinations(candidates, 3)
# 모든 학생이 감시로부터 피할 수 있는 지 여부 저장
ans = "NO"
# 장애물 후보들 탐색
for obstacle in obstacles:
    if avoid_teacher():
        ans = "YES"
        break
print(ans)
