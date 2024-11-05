from collections import deque

n, w, l = map(int, input().split())

trucks = deque(map(int, input().split()))

bridge = deque([0 for _ in range(w)])

count = 0

while 1:
    count += 1

    bridge.popleft()

    if sum(trucks) == 0 and sum(bridge) == 0:
        break

    sum_bri = sum(bridge)

    if trucks:
        if sum_bri + trucks[0] <= l:
            truck = trucks.popleft()
            bridge.append(truck)
        else:
            bridge.append(0)

print(count)