from collections import deque

n, k = map(int, input().split())
rail = deque(map(int, input().split()))
robot = deque([0] * n)
result = 0

while True:
    result += 1

    rail.rotate(1)
    robot[-1] = 0
    robot.rotate(1)
    robot[-1] = 0

    for i in range(n -2, -1, -1):
        if rail[i+1] >= 1 and robot[i+1] == 0  and robot[i] == 1:
            robot[i+1] = 1
            robot[i] = 0
            rail[i+1] -= 1
    robot[-1] = 0

    if rail[0] != 0 and robot[0] != 1:
        robot[0] = 1
        rail[0] -= 1

    if rail.count(0) >= k:
        break

print(result)


