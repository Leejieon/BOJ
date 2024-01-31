#include <iostream>
#include <queue>

using namespace std;

int arr[1001][1001] = { 0, };
bool vi[1001] = { false, };
int N, M, V;

void init()
{
	for (int i = 0; i < 1001; i++)
		vi[i] = false;
}

void dfs(int s)
{
	int i = 0;

	cout << s << " ";
	vi[s] = true;

	for (i = 1; i < N + 1; i++)
		if (arr[s][i] && !vi[i])
			dfs(i);

	if (i == N)
		return;
}

void bfs(int s)
{
	queue<int> q;
	int i = 0;

	q.push(s);
	vi[s] = true;

	while (!q.empty())
	{
		int news = q.front();
		
		vi[news] = true;
		cout << news << " ";

		q.pop();

		for (i = 1; i < N + 1; i++)
		{
			if (arr[news][i] && !vi[i])
			{
				vi[i] = true;
				q.push(i);
			}
		}
	}
}

int main()
{
	int x, y;

	cin >> N >> M >> V;

	for (int i = 1; i < M + 1; i++)
	{
		cin >> x >> y;
		arr[x][y] = 1;
		arr[y][x] = 1;
	}

	dfs(V);
	cout << endl;

	init();
	bfs(V);
	cout << endl;


}