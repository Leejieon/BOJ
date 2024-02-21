import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int N;
    static int[] numbers;
    static int[] select;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        numbers = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        select = new int[N];
        int curIndex = 0;

        // 처음 초기값 넣기
        select[0] = numbers[0];

        for (int i = 1; i < N; i++) {
            int curNum = numbers[i];
            // 현재 결과 리스트의 마지막 인덱스보다 큰 경우
            if (select[curIndex] < curNum) {
                select[++curIndex] = curNum;
                continue;
            }
            // 아닌 경우, 이분 탐색을 통해 들어갈 수 있는 위치 찾기
            int findIndex = binarySearch(curNum, 0, curIndex);
            select[findIndex] = curNum;
        }

        int size = 0;
        for (int i = 0; i < N; i++) {
            if (select[i] == 0)
                break;
            size++;
        }
        System.out.println(size);
    }

    // 현재 찾는 수가 들어갈 수 있는 위치 찾기
    static int binarySearch(int target, int low, int high) {
        while (low < high) {
            int mid = (low + high) / 2;

            if (target > select[mid]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return high;
    }
}