import sys
input = sys.stdin.readline

m,n = list(map(int,input().split()))
data = list(map(int,input().split()))

start = 0
end = max(data)

if sum(data) < m:
    print(0)
    exit()

res = 0
while (start <= end):
    temp = 0
    mid = (start + end) // 2

    for x in data:
        if x >= mid:
            temp += (x // mid)

    if temp >= m:
        start = mid + 1
        res = mid

    else:
        end = mid - 1

print(res)




