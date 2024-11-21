import heapq

def dijkstra(start):
    distance = [INF] * (n+1)
    distance[start] = 0 

    d = []

    heapq.heappush(d, (0, start))

    while d:
       dist, now = heapq.heappop(d)
       if distance[now] >= dist:
          for v, val in graph[now]:
            if dist + val < distance[v]:
               distance[v] = dist + val
               heapq.heappush(d, (dist + val, v))
    return distance
n, m, x = map(int, input().split())

INF = 1e8

graph = [[] for _ in range(n+1)]

for _ in range(m):
  u, v, w = map(int, input().split()) 
  graph[u].append((v, w))             

answer = dijkstra(x)
answer[0] = 0

for i in range(1, n+1):
   if i != x:
      res = dijkstra(i)
      answer[i] += res[x]

print(max(answer))



