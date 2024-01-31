import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Member> pq = new PriorityQueue<>(((o1, o2) -> {
            if (o1.age == o2.age)
                return o1.order - o2.order;
            return o1.age - o2.age;
        }));
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            pq.offer(new Member(Integer.parseInt(st.nextToken()), st.nextToken(), i));
        }

        while (!pq.isEmpty()) {
            Member member = pq.poll();
            sb.append(member.age)
                    .append(" ")
                    .append(member.name)
                    .append("\n");
        }

        System.out.print(sb);
    }

    static class Member {
        int age;
        String name;
        int order;

        Member(int age, String name, int order) {
            this.age = age;
            this.name = name;
            this.order = order;
        }
    }
}
