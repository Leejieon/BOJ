#include <stdio.h>
#include <stdlib.h>
#define MAX_SIZE 1001
#define INF 99999999

typedef struct Node
{
    int index;
    struct Node *next;
} Node;

typedef struct
{
    Node *front;
    Node *rear;
    int count;
} Queue;

Node **arr;
int vertex, edge, visit[MAX_SIZE];
int result=0;

void addFront(Node *root, int index)
{
    Node *node = (Node *)malloc(sizeof(Node));
    node->index = index;
    node->next = root->next;
    root->next = node;
}

void queuePush(Queue *queue, int index)
{
    Node *node = (Node *)malloc(sizeof(Node));
    node->index = index;
    node->next = NULL;

    if (queue->count == 0)
        queue->front = node;
    else
        queue->rear->next = node;

    queue->rear = node;
    queue->count++;
}

int queuePop(Queue *queue)
{
    if (queue->count == 0)
    {
        printf("Queue underflow Error!\n");
        return -INF;
    }
    int index = queue->front->index;
    queue->front = queue->front->next;
    queue->count--;
    return index;
}

void bfs(int start)
{
    Queue q;
    q.front = q.rear = NULL;
    q.count = 0;
    queuePush(&q, start);
    visit[start] = 1;
    while (q.count != 0)
    {
        int x = queuePop(&q);
        result++;
        Node *cur = arr[x]->next;
        while (cur != NULL)
        {
            int next = cur->index;
            if (!visit[next])
            {
                queuePush(&q, next);
                visit[next] = 1;
            }
            cur = cur->next;
        }
    }
}

int main()
{
    scanf("%d %d", &vertex, &edge);
    arr = (Node **)malloc(sizeof(Node *) * MAX_SIZE);

    for (int i = 1; i <= vertex; i++)
    {
        arr[i] = (Node *)malloc(sizeof(Node));
        arr[i]->next = NULL;
    }

    for (int i = 0; i < edge; i++)
    {
        int x, y;
        scanf("%d %d", &x, &y);
        addFront(arr[x], y);
        addFront(arr[y], x);
    }

    bfs(1);
    printf("%d", result - 1);
}