from collections import Counter

def row_sort():
    temp = []
    max_len = 0
    for row in data:
        row = Counter(row)
        del row[0]

        tmp = []
        for v in sorted(row.items(),key=lambda x:(x[1],x[0])):
            tmp.extend(v)
            if len(tmp)>=100:
                break
        max_len = max(max_len,len(tmp))
        temp.append(tmp)

    for row in temp:
        row += [0] * (max_len - len(row))
    return temp


r,c,k = list(map(int,input().split()))
data = [list(map(int,input().split())) for _ in range(3)]

row_cnt = len(data)
col_cnt = 0

for res in range(101):


    if r <= len(data) and c <= len(data[0]) and data[r-1][c-1] == k:
        print(res)
        break

    if len(data) >= len(data[0]):
        data = row_sort()
    else:
        data = list(map(list,zip(*data)))
        data = row_sort()
        data = list(map(list,zip(*data)))
else:
    print(-1)
