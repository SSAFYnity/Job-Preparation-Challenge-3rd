n, w, l = map(int, input().split())
trucks = list(map(int, input().split()))
# 시간 저장
time = 0
# 다리 각 위치에 해당하는 트럭 저장
bridge = [0]*w
# 다리 위의 전체 트럭 무게
bridgeW = 0

while trucks or bridgeW:
    # 맨 앞 트럭 내보냄
    bridgeW -= bridge.pop(0)
    # 새로운 트럭이 있고 진입 했을 때 최대 하중 이하면
    if trucks and bridgeW + trucks[0] <= l:
        # 진입 트럭
        truck = trucks.pop(0)
        # 다리에 진입
        bridge.append(truck)
        # 다리에 트럭 무게 추가
        bridgeW += truck
    else:
        # 최대 하중을 초과하면 다리에 진입 못함
        bridge.append(0)
    # 시간 증가
    time += 1

print(time)
