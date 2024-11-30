def check(diffs, times, limit, level):
    addTime = times[0]

    for i in range(1,len(diffs)):
        cur_diff=diffs[i]
        cur_time=times[i]
        pre_time=times[i-1]

        if cur_diff<=level:
            addTime+=times[i]
        else:
            addTime+=(cur_diff-level)*(cur_time+pre_time)+cur_time

    return addTime<=limit



def solution(diffs, times, limit):
    answer = 0

    left = 1
    right = max(diffs)

    while left <= right:
        mid = (left+right)//2

        if check(diffs, times, limit, mid):
            answer = mid
            right = mid-1
        else: left = mid+1


    return answer