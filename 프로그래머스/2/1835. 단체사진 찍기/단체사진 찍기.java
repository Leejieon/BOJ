class Solution {
    final char[] FRIENDS = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    final int SIZE = 8;
    
    int temp = 0;
    int total = 0;
    char[] select = new char[SIZE];
    boolean[] visited = new boolean[SIZE];
    
    public int solution(int n, String[] data) {
        permutation(0, data);
        return total;
    }
    
    void permutation(int count, String[] data) {
        // Base Case
        if(count == SIZE) {
            if(proceed(data)) {
                total++;
            }
            return;
        }
        
        // Recursive Case
        for(int i = 0; i < SIZE; i++) {
            if(visited[i]) continue;
            
            visited[i] = true;
            select[count] = FRIENDS[i];
            permutation(count + 1, data);
            visited[i] = false;
        }
    }
    
    boolean proceed(String[] data) {
        String line = makeLine();
        boolean result = true;
        for(int i = 0; i < data.length; i++) {
            if(!result) {
                break;
            }
            
            int distance = Math.abs(
                line.indexOf(data[i].charAt(0)) - line.indexOf(data[i].charAt(2))) - 1;
            int len = data[i].charAt(4) - '0';
            
            switch(data[i].charAt(3)) {
                case '=':
                    if(distance != len) {
                        result = false;
                    }
                    break;
                case '<':
                    if(distance >= len) {
                        result = false;
                    }
                    break;
                case '>':
                    if(distance <= len) {
                        result = false;
                    }
                    break;
            }
        }
        
        return result;
    }
    
    String makeLine() {
        String result = "";
        for(int i = 0; i < SIZE; i++) {
            result += select[i];
        }
        return result;
    }
}