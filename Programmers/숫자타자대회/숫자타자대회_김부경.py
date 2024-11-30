import heapq

def solution(numbers):
        
    # 각 번호로 부터 최소 시간 저장
    def find_weights_dijkstra(idx):
        # 시작 위치
        x, y = board[idx]
        
        heap = []
        # 가중치, 현재 위치
        heapq.heappush(heap, (0, x, y))
        # 자기자신 누르면 가중치 1
        dist[idx][x][y] = 1

        while heap:
            currCost, x, y = heapq.heappop(heap)
            
            # 현재 비용이 저장된 비용보다 크면 탐색안함
            if currCost > dist[idx][x][y]:
                continue

            for k in range(8):
                nx = x + di[k]
                ny = y + dj[k]
                # 가중치
                if k < 4:
                    nc = currCost + 2
                else:
                    nc = currCost + 3
                if 0 <= nx < 4 and 0 <= ny < 3 and nc < dist[idx][nx][ny]:
                        dist[idx][nx][ny] = nc
                        heapq.heappush(heap, (nc, nx, ny))
    
    # 타이핑 최소 시간 찾기 
    def find_min_cost_dijkstra():
        heap = []
        # 비용, 왼손 번호, 오른손 번호, 다음 숫자 인덱스 
        heap.append((0, 4, 6, 0))
        # 각 경우에서 비용 저장
        visited = dict()
        # (왼손 번호, 오른손 번호, 다음 인덱스): 비용
        visited[(4, 6, 0)] = 0
            
        while heap:
            # 현재 비용, 왼손 위치, 오른손 위치, 다음에 누를 숫자 인덱스
            cost, leftHand, rightHand, numIdx = heapq.heappop(heap)
            # 같은 숫자 위에 두 엄지 손가락 불가
            if leftHand == rightHand:
                continue
            # 현재 비용이 저장된 비용보다 크면 탐색 안함
            if visited[(leftHand, rightHand, numIdx)] < cost:
                continue
            # 마지막 숫자 타이핑이 끝나면 최소 시간 반환
            if numIdx == n:
                return cost
        
            # 타이핑할 숫자
            number = int(numbers[numIdx])
            # 티이핑할 숫자 위치
            ex, ey = board[number]

            # 왼손 이동
            moveLeft = (number, rightHand, numIdx + 1)
            moveLeftCost = cost + dist[leftHand][ex][ey]
            if moveLeft not in visited or visited[moveLeft] > moveLeftCost:
                visited[moveLeft] = moveLeftCost
                heapq.heappush(heap, (moveLeftCost, number, rightHand, numIdx + 1))
                
            # 오른손 이동
            moveRight = (leftHand, number, numIdx + 1)
            moveRightCost = cost + dist[rightHand][ex][ey]
            if moveRight not in visited or visited[moveRight] > moveRightCost:
                visited[moveRight] = moveRightCost
                heapq.heappush(heap, (moveRightCost, leftHand, number, numIdx + 1))

    
    # 문자열 길이
    n = len(numbers)
    # 자판의 위치 저장
    board = {
        1: (0, 0), 2: (0, 1), 3: (0, 2),
        4: (1, 0), 5: (1, 1), 6: (1, 2),
        7: (2, 0), 8: (2, 1), 9: (2, 2),
        0: (3, 1)
    }
    # 가능한 최대 비용
    maxV = 300001
    # 각 위치에서 최소 시간 저장
    dist = [[[maxV] * 3 for _ in range(4)] for _ in range(10)]
    # 상하좌우(2), 대각선(3)
    di = [-1, 1, 0, 0, -1, 1, 1, -1]
    dj = [0, 0, -1, 1, 1, 1, -1, -1]
    
    # 각 위치에서 최소 시간 저장
    for i in range(10):
        find_weights_dijkstra(i)

    # 최소 가중치 저장
    answer = find_min_cost_dijkstra()

    return answer
