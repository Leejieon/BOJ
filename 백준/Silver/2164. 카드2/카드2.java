import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Deque<Integer> cards = new ArrayDeque<>();

        int N = Integer.parseInt(br.readLine());
        for (int i = 1; i <= N; i++) {
            cards.offerLast(i);
        }

        while (true) {
            // Base Case
            if(cards.size() == 1) break;

            // 제일 위에 있는 카드 버리기
            cards.pollFirst();

            // 그 다음, 제일 위에 있는 카드를 제일 아래로 옮기기
            cards.offerLast(cards.pollFirst());
        }

        System.out.println(cards.poll());
    }
}