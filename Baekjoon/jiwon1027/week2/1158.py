3n, k = list(map(int,input().split()))

queue = list(range(1,n+1))
result = []
index = k-1

while len(queue) != 0:

    if index >= len(queue):
        index = int(index % len(queue))
    result.append(queue.pop(index))
    index += k -1

print("<", ', '.join(str(i) for i in result), ">", sep = '')

