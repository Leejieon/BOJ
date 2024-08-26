import java.util.*;

class Solution {
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    PriorityQueue<Integer> rpq = new PriorityQueue<>((o1, o2) -> o2 - o1);

    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        StringTokenizer st;
        
        for(String operation : operations) {
            st = new StringTokenizer(operation);
            if(st.nextToken().equals("I")) {
                int number = Integer.parseInt(st.nextToken());
                pq.offer(number);
                rpq.offer(number);
            } else {
                if(pq.isEmpty() || rpq.isEmpty()) continue;
                
                int option = Integer.parseInt(st.nextToken());
                if(option == 1) {
                    pq.remove(rpq.poll());
                } else {
                    rpq.remove(pq.poll());
                }
            }
        }
        
        if(pq.isEmpty() || rpq.isEmpty()) {
            return answer;
        }
        
        answer[0] = rpq.peek();
        answer[1] = pq.peek();
        return answer;
    }
}