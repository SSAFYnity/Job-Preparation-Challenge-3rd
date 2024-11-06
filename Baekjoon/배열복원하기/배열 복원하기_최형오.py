h, w, x, y = map(int, input().split())

b_arr = []
for _ in range(h+x):
    b_arr.append(list(map(int, input().split())))

a_arr = [row[:w] for row in b_arr[:h]]

for i in range(h):
    for j in range(w):
        if i >= x and j >= y:
            a_arr[i][j] = b_arr[i][j] - a_arr[i - x][j - y]
        else:
            a_arr[i][j] = b_arr[i][j]

for result in a_arr[:h] :
    print(*result[:w])