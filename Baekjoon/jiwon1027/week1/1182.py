#조합은 2^n개, 2^20개니까 충분함!

n,s = list(map(int,input().split()))
data = list(map(int,input().split()))

def dfs(idx,sum):
    global res
    if idx>=n:
        return
    sum += data[idx]
    if sum == s:
        res += 1
    dfs(idx + 1, sum) #현재 원소 포함
    dfs(idx+1,sum-data[idx]) #현재 원소 미포함


res = 0

dfs(0,0)


# from itertools import combinations
#
# for i in range(1,n+1):
#     for temp in list(combinations(data,i)):
#         if sum(temp) == s:
#             res += 1
print(res)



