import java.io.*;
import java.util.*;

class Main {
    static int originalNum, targetNum;
    static char[] types = {'D', 'S', 'L', 'R'};
    static boolean[] visited;
    static Queue<Node> queue;
    static ArrayList<Character> result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int TC = Integer.parseInt(br.readLine());
        for (int t = 0; t < TC; t++) {
            st = new StringTokenizer(br.readLine());
            originalNum = Integer.parseInt(st.nextToken());
            targetNum = Integer.parseInt(st.nextToken());

            visited = new boolean[10_000];

            result = new ArrayList<>(); // 결과를 담을 스택
            queue = new ArrayDeque<>();
            queue.offer(new Node(originalNum, new ArrayList<>()));
            visited[originalNum] = true;
            while (!queue.isEmpty()) {
                Node cur = queue.poll();
                int number = cur.number;
                ArrayList<Character> list = cur.types;

                // Base Case
                if (number == targetNum) {
                    result = list;
                    break;
                }

                // Recursive Case
                for (int cand = 0; cand < types.length; cand++) {
                    int newNumber = proceedDSLR(number, types[cand]);
                    if(visited[newNumber]) continue;

                    ArrayList<Character> temp = (ArrayList<Character>) list.clone();
                    temp.add(types[cand]);
                    visited[newNumber] = true;
                    queue.offer(new Node(newNumber, temp));
                }
            }

            for (Character c : result) {
                sb.append(c);
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.close();
    }

    static int proceedDSLR(int number, char type) {
        int result = 0;
        String numberStr;

        switch (type) {
            case 'D':
                result = (number * 2) % 10_000;
                break;
            case 'S':
                if (number == 0) {
                    result = 9999;
                } else {
                    result = number - 1;
                }
                break;
            case 'L':
                numberStr = convertNumToStr(number);
                numberStr = numberStr.substring(1,4) + numberStr.charAt(0);
                result = Integer.parseInt(numberStr);
                break;
            case 'R':
                numberStr = convertNumToStr(number);
                numberStr = numberStr.charAt(numberStr.length() - 1) + numberStr.substring(0, 3);
                result = Integer.parseInt(numberStr);
                break;
        }
        return result;
    }

    static String convertNumToStr(int number) {
        String result = "";
        if (number >= 1_000) {
            result += number;
        } else if(number >= 100) {
            result = "0" + number;
        } else if (number >= 10) {
            result = "00" + number;
        } else {
            result = "000" + number;
        }
        return result;
    }

    static class Node{
        int number;
        ArrayList<Character> types;

        public Node(int number, ArrayList<Character> types) {
            this.number = number;
            this.types = types;
        }
    }
}