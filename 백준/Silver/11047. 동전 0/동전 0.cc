#include <iostream>

using namespace std;

int coin_count(int value, int* coin,int n)
{
  int total_coin = 0;

  for(int i=0;i<n;i++)
  {
    if(value==0)
      break;
    int coin_num = value/coin[i];
    total_coin += coin_num;
    value -= coin_num * coin[i];
  }

  return total_coin;
}

int main()
{
  int n,k;
  cin>>n>>k;

  int* coin = new int[n];

  for(int i=n-1;i>=0;i--)
    cin>>coin[i];
  
  int result = coin_count(k,coin,n);

  cout<<result<<endl;
}