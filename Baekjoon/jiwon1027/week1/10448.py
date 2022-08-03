def fun(x):

    for one in Tn:
        for two in Tn:
            for three in Tn:
                if one + two + three == x:
                    return 1
    return 0

Tn = []
for i in range(1,45):
    Tn.append(i*(i+1)//2)

for _ in range(int(input())):
    n = int(input())
    print(fun(n))