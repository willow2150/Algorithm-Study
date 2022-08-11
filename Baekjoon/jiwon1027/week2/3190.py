from collections import deque
def rotate(c):
    global dx,dy
    if c == 'L': #왼쪽 회전
        if dx == 0 and dy == 1:
            dx = -1
            dy = 0
        elif dx == -1 and dy == 0:
            dx = 0
            dy = -1
        elif dx == 0 and dy == -1:
            dx = 1
            dy = 0
        elif dx == 1 and dy == 0:
            dx = 0
            dy = 1

    else: #오른쪽 회전
        if dx == 0 and dy == 1:
            dx = 1
            dy = 0
        elif dx == -1 and dy == 0:
            dx = 0
            dy = 1
        elif dx == 0 and dy == -1:
            dx = -1
            dy = 0
        elif dx == 1 and dy == 0:
            dx = 0
            dy = -1

def bfs():
    t,c = data.popleft()
    res = 0
    head_x,head_y = 0,0 #탐색하는 현재 위치

    board[0][0] = 2  # 2 : 뱀의 몸
    snake = deque()
    snake.append((0,0))

    while True:
        res += 1
        nx = head_x + dx
        ny = head_y + dy

        if res == int(t):
            rotate(c)
            try:
                t, c = data.popleft()
            except:
                pass

        if not (0 <= nx < n) or not (0 <= ny < n) or board[nx][ny] == 2:  #범위 바깥, 몸 만났을 때
            print(res)
            break

        elif board[nx][ny] == 1: #사과 만났을 때
            board[nx][ny] = 2
            snake.append((nx,ny))

        else:
            board[nx][ny] = 2
            snake.append((nx,ny))
            temp_x, temp_y = snake.popleft()
            board[temp_x][temp_y] = 0

        head_x = nx
        head_y = ny



n = int(input())
k = int(input())
board = [[0] * n for _ in range(n)]

for _ in range(k):
    x,y = list(map(int,input().split()))
    board[x-1][y-1] = 1 #1 : 사과

l = int(input())
data = deque()
for _ in range(l):
    data.append(input().split())
dx = 0
dy = 1

bfs()







