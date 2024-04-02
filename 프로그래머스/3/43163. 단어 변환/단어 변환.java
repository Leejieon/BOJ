import java.util.*;

class Solution {
    
    ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    
    public int solution(String begin, String target, String[] words) {        
        // init graph
        for(int i = 0; i < words.length; i++) {
            graph.add(new ArrayList<>());
        }
        for(int i = 0; i < words.length - 1; i++) {
            String str1 = words[i];
            for(int j = i + 1; j < words.length; j++) {
                String str2 = words[j];
                if(checkStr(str1, str2)) {
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }
        
        int answer = Integer.MAX_VALUE;
        for(int i = 0; i < words.length; i++) {
            if(checkStr(begin, words[i])) {
                int result = bfs(i, words, target);
                if(result != -1) {
                    answer = Math.min(answer, result);
                }
            }
        }
        
        return answer == Integer.MAX_VALUE ? 0 : answer;
    }
    
    int bfs(int start, String[] words, String target) {
        // init
        Queue<Integer> queue = new ArrayDeque<>();
        int[] distance = new int[words.length];
        boolean[] visited = new boolean[words.length];
        visited[start] = true;
        distance[start] = 0;
        queue.offer(start);
        
        int index = 0;
        boolean isFound = false;
        while(!queue.isEmpty()) {
            int cur = queue.poll();
            
            if(words[cur].equals(target)) {
                isFound = true;
                index = cur;
                break;
            }
            
            for(int next : graph.get(cur)) {
                if(visited[next]) continue;
                visited[next] = true;
                queue.offer(next);
                distance[next] = distance[cur] + 1;
            }
        }
        
        return isFound ? distance[index] + 1 : -1;
    }
    
    boolean checkStr(String str1, String str2) {
        int count = 0;
        for(int i = 0; i < str1.length(); i++) {
            if(count >= 2) break;
            if(str1.charAt(i) != str2.charAt(i)) {
                count++;
            }
        }
        return count == 1 ? true : false;
    }
}