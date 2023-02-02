import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        PriorityQueue<Integer> MinHeap = new PriorityQueue<>();
        PriorityQueue<Integer> MaxHeap = new PriorityQueue<>(Collections.reverseOrder());

        int N = Integer.parseInt(br.readLine());
        MaxHeap.offer(Integer.parseInt(br.readLine())); // 첫 번째 숫자는 기준이 되어야 하므로 바로 MaxHeap에 넣어준다.
        sb.append(MaxHeap.peek()+"\n");
        for (int i = 1; i < N; i++) {
            int number = Integer.parseInt(br.readLine());
            if(number < MaxHeap.peek())
                MaxHeap.offer(number);
            else
                MinHeap.offer(number);

            if (MaxHeap.size() < MinHeap.size()) {
                MaxHeap.offer(MinHeap.poll());
            } else if (MaxHeap.size() > MinHeap.size() + 1) {
                MinHeap.offer(MaxHeap.poll());
            }

            // 짝수 개 일 경우
            if (MaxHeap.size() == MinHeap.size()) {
                sb.append(Math.min(MaxHeap.peek(), MinHeap.peek())+"\n");
            } else {
                sb.append(MaxHeap.peek()+"\n");
            }

        }

        System.out.print(sb);
    }
}
