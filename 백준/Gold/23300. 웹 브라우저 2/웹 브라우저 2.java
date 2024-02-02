import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

class Main {
    static int N, Q;
    static Deque<Integer> back = new ArrayDeque<>();
    static Deque<Integer> front = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        int curPage = 0;
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            switch (st.nextToken()) {
                case "B":
                    curPage = goBack(curPage);
                    break;
                case "F":
                    curPage = goFront(curPage);
                    break;
                case "A":
                    int web = Integer.parseInt(st.nextToken());
                    goSite(curPage);
                    curPage = web;
                    break;
                case "C":
                    back = compress(back);
            }
        }

        // 결과 출력
        // 현재 접속 중인 페이지
        sb.append(curPage).append("\n");
        // 뒤로 가기 공간
        if (back.isEmpty()) {
            sb.append(-1);
        } else {
            while (!back.isEmpty()) {
                sb.append(back.pollLast()).append(" ");
            }
        }
        sb.append("\n");
        // 앞으로 가기 공간
        if (front.isEmpty()) {
            sb.append(-1);
        } else {
            while (!front.isEmpty()) {
                sb.append(front.pollFirst()).append(" ");
            }
        }

        System.out.println(sb);
    }

    static int goBack(int curPage) {
        // 뒤로가기 공간이 비어있는 경우
        if (back.isEmpty()) {
            return curPage;
        }
        front.offerFirst(curPage);
        return back.pollLast();
    }

    static int goFront(int curPage) {
        // 앞으로가기 공간이 비어있는 경우
        if (front.isEmpty()) {
            return curPage;
        }
        back.offerLast(curPage);
        return front.pollFirst();
    }

    static void goSite(int prevPage) {
        // 앞으로 가기 공간 모두 삭제
        front.clear();

        // 처음 접속이 아닌 경우에만 현재 페이지를 뒤로 가기 공간에 추가
        if (prevPage != 0) {
            back.offerLast(prevPage);
        }
    }

    static Deque<Integer> compress(Deque<Integer> curBack) {
        Deque<Integer> result = new ArrayDeque<>();

        if (curBack.isEmpty()) {
            return curBack;
        }
        
        int prevPage = curBack.pollFirst();
        result.offerLast(prevPage);
        while (!curBack.isEmpty()) {
            int curPage = curBack.pollFirst();
            if (curPage == prevPage) {
                continue;
            }

            result.offerLast(curPage);
            prevPage = curPage;
        }
        return result;
    }
}