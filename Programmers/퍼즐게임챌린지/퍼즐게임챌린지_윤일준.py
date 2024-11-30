def solution(diffs, times, limit):
    left = 1
    right = max(diffs)

    while left < right:
        mid = (left + right) // 2
        if cal_time(diffs, times, mid) <= limit:
            right = mid
        else:
            left = mid + 1

    return left


def cal_time(diffs, times, level):
    time = 0
    for idx in range(len(diffs)):
        if diffs[idx] <= level:
            time += times[idx]
        else:
            time += (times[idx] + times[idx - 1]) * (diffs[idx] - level) + times[idx]

    return time
