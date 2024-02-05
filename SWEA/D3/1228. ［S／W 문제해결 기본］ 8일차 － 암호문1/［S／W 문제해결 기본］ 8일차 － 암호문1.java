import java.io.*;
import java.util.StringTokenizer;

class Solution
{
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for(int test_case = 1; test_case <= 10; test_case++)
		{
			bw.write("#");
			bw.write(test_case + " ");

			LinkedList list = new LinkedList();
			int length = Integer.parseInt(br.readLine());
			int[] initArr = new int[length];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < length; i++) {
				initArr[i] = Integer.parseInt(st.nextToken());
			}
			list.insert(0, initArr);

			int M = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				char cmd = st.nextToken().charAt(0);
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());

				int[] temp = new int[y];
				for (int j = 0; j < y; j++) {
					temp[j] = Integer.parseInt(st.nextToken());
				}
				list.insert(x, temp);
			}
			list.print();
			bw.write("\n");
		}
		bw.flush();
		bw.close();
	}

	static class Node {
		int data;
		Node next;

		Node(int data) {
			this.data = data;
			this.next = null;
		}
	}

	static class LinkedList {
		Node head;
		Node tail;
		Node[] nodeArr;
		int nodeCnt;

		public LinkedList() {
			head = null;
			nodeArr = new Node[1_000_001];
			nodeCnt = 0;
		}

		Node getNewNode(int data) {
			nodeArr[nodeCnt] = new Node(data);
			return nodeArr[nodeCnt++];
		}

		void insert(int index, int[] numbers) {
			int start = 0;
			// 맨 앞에 붙이는 경우
			if (index == 0) {
				if (head != null) {
					Node newNode = getNewNode(numbers[0]);
					newNode.next = head;
					head = newNode;
				} else {
					head = getNewNode(numbers[0]);
				}

				index = 1;
				start = 1;
			}

			Node cur = head;
			// index 개 만큼 이동하기
			for (int i = 1; i < index; i++) {
				cur = cur.next;
			}

			// 이동한 뒤, 추가하기
			for (int i = start; i < numbers.length; i++) {
				Node newNode = getNewNode(numbers[i]);
				newNode.next = cur.next;
				cur.next = newNode;
				cur = newNode;
			}

			if (cur.next == null) {
				tail = cur;
			}
		}

		void print() throws IOException {
			int cnt = 10;
			Node cur = head;
			while (--cnt >= 0) {
				bw.write(cur.data + " ");
				cur = cur.next;
			}
		}
	}
}