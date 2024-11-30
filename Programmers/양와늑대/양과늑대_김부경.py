def dfs(sheep, wolf, edges, info):
    global answer, visited
    
    if sheep <= wolf:
        return
    elif answer < sheep:
        answer = sheep

    for p, c in edges:
        if visited[p] and not visited[c]:
            visited[c] = 1
            if not info[c]:
                dfs(sheep+1, wolf, edges, info)
            else:
                dfs(sheep, wolf+1, edges, info)
            visited[c] = 0

            
def solution(info, edges):
    global answer, visited
    # info : 양 또는 늑대 정보 (0은 양, 1은 늑대) - 루트는 항상 0
    # edges : 트리의 각 노드 연결 관계
    visited = [0]*len(info)
    answer = 0
    
    visited[0] = 1
    dfs(1,0, edges, info)               
    return answer
