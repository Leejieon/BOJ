#include <stdio.h>

int memo[100][100][100]={0};

int function(int a, int b, int c)
{
  if(a<=0||b<=0||c<=0)
    return 1; 

  if(memo[a][b][c]!=0)
    return memo[a][b][c];

  if(a>20||b>20||c>20)
    return function(20,20,20);

  if(a<b&&b<c)
    return memo[a][b][c] = function(a, b, c-1)+function(a, b-1, c-1)-function(a, b-1, c);

  else
    return memo[a][b][c] = function(a-1, b, c)+function(a-1, b-1, c)+function(a-1, b, c-1)-function(a-1, b-1, c-1);
}

int main(void) {

  int a,b,c;
  
  while(1)
  {
    scanf("%d %d %d", &a, &b, &c);
    if(a==-1&&b==-1&&c==-1)
      break;
    printf("w(%d, %d, %d) = %d\n",a,b,c,function(a,b,c));
  }
}