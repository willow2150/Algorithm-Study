import sys
input = sys.stdin.readline

for _ in range(int(input())):
    data = []
    for _ in range(int(input())):
        data.append(list(map(int,input().split())))
    data.sort(key = lambda x:x[0])
    y_min = data[0][1]
    res = 1

    for x,y in data:
        if y < y_min:
            res+=1
            y_min = min(y_min,y)
            # print(x,y)
    print(res)
