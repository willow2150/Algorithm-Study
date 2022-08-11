n = int(input())
m = int(input())
data = list(map(int,input().split()))

max_size = 0
for i in range(1,m):
    max_size = max(max_size, data[i] - data[i-1])

print(max((max_size+1)//2,data[0], n - data[-1]))