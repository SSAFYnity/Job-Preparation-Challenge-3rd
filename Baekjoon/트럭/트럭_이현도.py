n, w, L = map(int,input().split())
trucks = list(map(int,input().split()))
time = 0

from collections import deque

bridge = deque([0]*w)

whole_weight = 0
truck_index = 0
while truck_index < n-1 or bridge:
    time += 1
    if bridge:
        whole_weight -= bridge.popleft()
    if truck_index == n:
        continue
    if len(bridge) < w and whole_weight + trucks[truck_index] <= L:
        bridge.append(trucks[truck_index])
        whole_weight += trucks[truck_index]
        truck_index += 1
    else:
        bridge.append(0)
print(time)
