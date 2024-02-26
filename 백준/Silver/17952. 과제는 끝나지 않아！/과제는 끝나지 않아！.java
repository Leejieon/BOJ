import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static ArrayList<Job> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 입력값 넣기
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            if (Integer.parseInt(st.nextToken()) == 1) {
                int score = Integer.parseInt(st.nextToken());
                int time = Integer.parseInt(st.nextToken());
                list.add(new Job(score, time));
            } else {
                list.add(new Job(0, 0));
            }
        }

        int prevNumber = 0;
        int totalScore = 0;
        for(int i = 0; i < N; i++)  {
            Job curJob = list.get(i);

            // 새로운 업무가 추가되는 경우
            if(curJob.time != 0) {
                curJob.time--;

                // 새로운 업무를 마치는 경우
                if(curJob.time == 0) {
                    totalScore += curJob.score;
                    // 바로 이전에 해결 못한 업무 가져오기
                    prevNumber = findPrevJob(i);
                    continue;
                }
                // 새로운 업무를 마치지 못했으면, 현재 업무를 계속 수행
                prevNumber = i;
            }
            // 새로운  업무가 없는 경우
            else {
                // 이전 업무 이어서 수행
                Job prevJob = list.get(prevNumber);
                prevJob.time--;

                // 이전 업무가 끝나게 되는 경우
                if(prevJob.time == 0) {
                    totalScore += prevJob.score;
                    // 그것보다 이전 업무 찾기
                    prevNumber = findPrevJob(i);
                }
            }
        }

        System.out.println(totalScore);
    }

    // 가장 이전의 업무 찾기
    static int findPrevJob(int cur) {
        for(int i = cur; i >= 0; i--) {
            if(list.get(i).time > 0) {
                return i;
            }
        }
        // 모든 일을 끝마친 경우
        return 0;
    }

    static class Job {
        int score;
        int time;

        Job(int score, int time) {
            this.score = score;
            this.time = time;
        }

    }

}
