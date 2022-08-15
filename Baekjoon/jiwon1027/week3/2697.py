for _ in range(int(input())):
    num = list(map(int,input()))

    idx = 0
    for i in range(len(num)-1,0,-1):
        if num[i-1] < num[i]:

            idx = i-1
            break

    front = num[:idx]
    back = num[idx:]

    if not len(front) or not len(back):
        print("BIGGEST")
    else:
        back.sort()
        for i in range(len(back)):
            if back[i] > num[idx]:
                front.append(back.pop(i))
                break

        for v in front:
            print(v,end='')
        for v in back:
            print(v,end='')
        print()
