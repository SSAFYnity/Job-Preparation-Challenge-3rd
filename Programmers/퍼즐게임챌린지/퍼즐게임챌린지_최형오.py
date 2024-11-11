def levellow(level, diff, time_cur, time_prev):
    if level >= diff:
        return time_cur
    else:
        return (diff - level) * (time_cur + time_prev) + time_cur
    return

def solution(diffs, times, limit):
    max_level, min_level = max(diffs), 1

    while min_level < max_level:
        mid_level = (min_level + max_level) // 2
        total = 0

        for i in range(len(diffs)):
            if i == 0:
                time_prev = 0
            else:
                time_prev = times[i-1]
            
            time_cur = times[i]
            total += levellow(mid_level, diffs[i], time_cur, time_prev)
        
        if total <= limit:
            max_level = mid_level
        else:
            min_level = mid_level+1

    return min_level

print(solution([1, 5, 3], [2, 4, 7], 30))