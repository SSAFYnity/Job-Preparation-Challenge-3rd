from itertools import combinations
from collections import Counter

def solution(orders, course):
    answer = []
    for c in course:
        candi = []
        for order in orders:
            for li in combinations(order, c):
                res = ''.join(sorted(li))
                candi.append(res)
        sorted_candi = Counter(candi).most_common()
        max = 0
        for oc in sorted_candi :
            if oc[1] >= max :
                max = oc[1]

        for oc in sorted_candi :
            if oc[1] >= max and oc[1] != 1 :
                answer.append(oc[0])
    return sorted(answer)
