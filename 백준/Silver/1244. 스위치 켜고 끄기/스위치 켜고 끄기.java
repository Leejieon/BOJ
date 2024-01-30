import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static int N, M;
    static boolean[] switches;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 스위치의 개수
        N = Integer.parseInt(br.readLine());
        switches = new boolean[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            switches[i] = (Integer.parseInt(st.nextToken()) == 1);
        }

        // 학생 수
        M = Integer.parseInt(br.readLine());
        int gender, index;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            gender = Integer.parseInt(st.nextToken());
            index = Integer.parseInt(st.nextToken());

            if (gender == 1) {
                int count = index;
                while (count <= N) {
                    switches[count] = !switches[count];
                    count += index;
                }
            } else {
                int left = index - 1;
                int right = index + 1;
                switches[index] = !switches[index];
                while (true) {
                    // Base Case
                    if(left <= 0 || right > N) break;
                    if (switches[left] != switches[right]) break;

                    switches[left] = !switches[left];
                    switches[right] = !switches[right];
                    left--;
                    right++;
                }
            }
        }

        // 출력
        for (int i = 1; i <= N; i++) {
            if (i != 1 && i % 20 == 1) {
                sb.append("\n");
            }
            if (switches[i]) {
                sb.append(1).append(" ");
            }else{
                sb.append(0).append(" ");
            }
        }

        System.out.println(sb);
    }

}