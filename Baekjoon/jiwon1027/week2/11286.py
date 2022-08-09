import sys
import heapq

input = sys.stdin.readline

n = int(input())
h = []
for _ in range(n):
    temp = int(input())
    if temp != 0:
        heapq.heappush(h, (abs(temp),temp))
    else:
        try:
            print(heapq.heappop(h)[1])
        except:
            print(0)