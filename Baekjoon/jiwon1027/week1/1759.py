from itertools import combinations

l, c = map(int, input().split())
data = list(map(str, input().split()))
data.sort()
comb = combinations(data, l)
consonant = ['a', 'e', 'i', 'o', 'u']
res = []
for com in comb:
    conso_cnt = 0
    vowel_cnt = 0
    for i in range(l):
        if com[i] in consonant:
            conso_cnt += 1
        else:
            vowel_cnt += 1
    if conso_cnt>=1 and vowel_cnt>=2:
        print(''.join(com))
