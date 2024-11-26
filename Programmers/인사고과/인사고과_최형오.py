def solution(scores):
    answer = 1

    target_a, target_b = scores[0]
    target_sum = target_a + target_b    

    scores.sort(key = lambda x: (-x[0], x[1]))
    max_b = 0

    for a, b in scores:
        if target_a < a and target_b < b:
            return -1

        if target_sum < a + b and max_b <= b:
            max_b = b
            answer += 1    
                
    return answer