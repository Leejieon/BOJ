n = int(input())

# n개의 원판, a 시작, b 보조, c 목표
def hanoi(n,a,b,c): 
    if n==1:
        print(a,c)
    else:
        hanoi(n-1,a,c,b)
        print(a,c)
        hanoi(n-1,b,a,c)

sum = 1;

for i in range(n-1):
    sum = sum*2 + 1

print(sum)
hanoi(n,1,2,3)
