import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int L, C;
    static char[] chars, select;
    static boolean[] visited;
    static List<Character> vowels = Arrays.asList('a', 'e', 'i', 'o', 'u');

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        select = new char[L];
        visited = new boolean[C];
        chars = br.readLine().replace(" ","").toCharArray();
        Arrays.sort(chars);

        combination(0, 0, 0, 0);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static void combination(int depth, int prevIndex, int vCnt, int cCnt) {
        // Base Case
        if (depth == L) {
            if (vCnt >= 1 && cCnt >= 2) {
                for (char c : select) {
                    sb.append(c);
                }
                sb.append("\n");
            }
            return;
        }

        // Recursive Case
        for (int i = prevIndex; i < chars.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                select[depth] = chars[i];
                if (vowels.contains(chars[i])) {
                    combination(depth + 1, i, vCnt + 1, cCnt);
                } else {
                    combination(depth + 1, i, vCnt, cCnt + 1);
                }
                visited[i] = false;
            }
        }
    }
}