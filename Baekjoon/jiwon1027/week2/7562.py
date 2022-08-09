from collections import deque
dx = [-1, 1, 2, 2, 1, -1, -2, -2]
dy = [2, 2, 1, -1, -2, -2, -1, 1]

def bfs(x,y):
    queue = deque()
    queue.append((x,y))
    visited[x][y] = 1

    while queue:
        x,y = queue.popleft()

        if x == end_x and y == end_y:
            return visited[end_x][end_y]-1

        for i in range(8):
            nx = x + dx[i]
            ny = y + dy[i]
            if 0<=nx<n and 0<=ny<n and not visited[nx][ny]:
                queue.append((nx,ny))
                visited[nx][ny] = visited[x][y] + 1


for _ in range(int(input())):
    n = int(input())
    start_x,start_y = list(map(int,input().split()))
    end_x, end_y = list(map(int,input().split()))
    visited = [[0] * n for _ in range(n)]
    print(bfs(start_x,start_y))