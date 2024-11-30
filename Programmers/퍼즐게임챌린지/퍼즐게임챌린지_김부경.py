def solution(diffs, times, limit):
    # 퍼즐 게임 소요시간 구하기
    def puzzle_game(level):
        # 현재 숙련도에서 소요 시간
        total_time = 0
        # 퍼즐 게임 시작
        for i in range(n):
            # 시간 계산
            if diffs[i] <= level:
                total_time += times[i]
            else:
                # 틀린 횟수
                cnt = diffs[i] - level
                total_time += (times[i] + times[i-1]) * cnt + times[i]

        return total_time

    # 숙련도 이분 탐색
    def binary_search(start, end, target):
        
        while start <= end:
            mid = (start + end) // 2
            # 게임 시간 계산
            game_time = puzzle_game(mid) 
            # 제한 시간과 게임 시간 비교
            if game_time <= target:
                end = mid - 1
            else:
                start = mid + 1
        # 숙련도 최솟값 반환
        return start
    
    # 퍼즐 개수
    n = len(diffs)
    # 숙련도 시작값
    start_level = 1
    # 숙련도 최댓값
    end_level = 100000
    # 숙련도 최솟값 구하기
    return binary_search(start_level, end_level, limit)
