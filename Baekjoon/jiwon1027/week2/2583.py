from collections import deque


def bfs(x,y):
    dx = [-1,1,0,0]
    dy = [0,0,1,-1]

    queue = deque()
    queue.append((x,y))
    visited[x][y] = 1

    temp = 1
    while queue:
        x,y = queue.popleft()
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            if 0<=nx<m and 0<=ny<n and not visited[nx][ny]:
                    queue.append((nx,ny))
                    visited[nx][ny] = 1
                    temp += 1
    return temp

m,n,k = list(map(int,input().split()))
visited = [[0] * n for _ in range(m)]

for _ in range(k):
    x1, y1, x2, y2 = list(map(int, input().split()))
    for i in range(m-y2,m-y1):
        for j in range(x1,x2):
            visited[i][j] = 1

res = []
for i in range(m):
    for j in range(n):
        if not visited[i][j]:
            res.append(bfs(i,j))

print(len(res))
print(*sorted(res))