import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static final int TESTCASE = 10;
    static final int MAX_NODE = 5000;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int t = 1; t <= TESTCASE; t++) {
            LinkedList list = new LinkedList();

            sb = new StringBuilder();
            sb.append("#").append(t).append(" ");
            
            int N = Integer.parseInt(br.readLine());
            int[] numbers = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                numbers[i] = Integer.parseInt(st.nextToken());
            }

            list.insert(0, numbers);

            int M = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                char cmd = st.nextToken().charAt(0);
                int x, y;
                switch (cmd) {
                    case 'I' :
                        x = Integer.parseInt(st.nextToken());
                        y = Integer.parseInt(st.nextToken());
                        int[] temp = new int[y];
                        for (int j = 0; j < y; j++) {
                            temp[j] = Integer.parseInt(st.nextToken());
                        }
                        list.insert(x, temp);
                        break;
                    case 'D' :
                        x = Integer.parseInt(st.nextToken());
                        y = Integer.parseInt(st.nextToken());
                        list.delete(x, y);
                        break;
                    case 'A' :
                        y = Integer.parseInt(st.nextToken());
                        for (int j = 0; j < y; j++) {
                            list.append(Integer.parseInt(st.nextToken()));
                        }
                        break;
                }
            }

            list.print();
            System.out.println(sb.toString());
        }
    }

    static class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
            this.next = null;
        }
    }

    static class LinkedList {
        Node head;
        Node tail;
        Node[] nodeArr;
        int nodeCnt;

        LinkedList() {
            head = null;
            nodeArr = new Node[MAX_NODE];
            nodeCnt = 0;
        }

        // 만들어진 노드를 return 해야, 연결을 진행할 수 있다.
        Node getNewNode(int value) {
            nodeArr[nodeCnt] = new Node(value);
            return nodeArr[nodeCnt++];
        }

        void insert(int index, int[] nums) {
            int start = 0;
            // 맨 앞에 삽입되는 경우 -> head 가 변경되어야 하는 경우
            if (index == 0) {
                // 현재 head 가 존재하는 경우
                if (head != null) {
                    Node newNode = getNewNode(nums[0]);
                    newNode.next = head;
                    head = newNode;
                } else {
                    head = getNewNode(nums[0]);
                }

                index = 1; // 인덱스 설정
                start = 1; // nums 의 시작 위치 재설정
            }

            Node cur = head;
            // index 개 만큼 이동하기
            for (int i = 1; i < index; i++) {
                cur = cur.next;
            }

            // cur 에 nums 추가하기
            for (int i = start; i < nums.length; i++) {
                Node newNode = getNewNode(nums[i]);
                newNode.next = cur.next;
                cur.next = newNode;
                cur = newNode;
            }

            // cur 가 마지막 node 일 경우, tail 로 선언
            if (cur.next == null) {
                tail = cur;
            }
        }

        void delete(int index, int count) {
            Node cur = head;
            // 맨 앞부터 삭제되는 경우
            if (index == 0) {
                // count 개 만큼 이동하고 head 랑 연결하기
                for (int i = 0; i < count; i++) {
                    cur = cur.next;
                }

                head = cur;
                return;
            }

            // index 개 만큼 이동하기
            for (int i = 1; i < index; i++) {
                cur = cur.next;
            }

            Node save = cur; // 삭제되기 직전 위치 기억하기
            // index 위치부터 count 개 만큼 이동하기
            for (int i = 0; i < count; i++) {
                cur = cur.next;
            }
            save.next = cur.next;

            // save 가 마지막 node 일 경우, tail 로 선언
            if (save.next == null) {
                tail = save;
            }
        }

        // 제일 마지막에 추가하기
        void append(int value) {
            Node cur = tail;
            Node newNode = new Node(value);
            cur.next = newNode;
            tail = newNode;
        }

        void print() {
            int count = 10;
            Node cur = head;
            while (--count >= 0) {
                sb.append(cur.value).append(" ");
                cur = cur.next;
            }
        }
    }
}
