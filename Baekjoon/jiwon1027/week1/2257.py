data = input()

dic = {'H':1, 'C':12, 'O':16}
res = 0
stack = []
for part in data:

    if part == "(":
        stack.append(part)

    elif part == ")":
        res = 0
        while True:
            target = stack.pop()

            if target == "(":
                break

            res += target
        stack.append(res)

    elif part in dic:
        stack.append(dic[part])

    else:
        stack[-1] *= int(part)

print(sum(stack))
