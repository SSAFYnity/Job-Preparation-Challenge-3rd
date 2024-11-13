from collections import deque

def rotation():
    # 벨트가 로봇과 함께 회전한다.
    a.rotate(1)
    conveyorBelt.rotate(1)

def moveRobot():
    # 이동 전 내림
    conveyorBelt[n - 1] = False
    for i in range(n-1, 0, -1):
        if a[i] > 0 and conveyorBelt[i-1] and not conveyorBelt[i]:
            # 이동하려는 칸에 로봇이 없으며, 내구도가 1 이상 남아 있어야 한다.
            conveyorBelt[i] = True
            conveyorBelt[i - 1] = False
            # 로봇을 올리거나 이동하면 그 칸의 내구도 1 감소
            a[i] -= 1
    # 물건 올리기
    if a[0] > 0:
        conveyorBelt[0] = True
        a[0] -= 1
    # 이동 후 내림
    conveyorBelt[n - 1] = False
    return a.count(0)


n, k = map(int, input().split())
# i번 칸의 내구도는 Ai
a = deque(map(int, input().split()))
# 각 단계별 로봇 위치
conveyorBelt = deque([False]*(2 * n))
step = 0
while True:
    step += 1
    rotation()
    zeroCnt = moveRobot()
    if zeroCnt >= k:
        break
print(step)
