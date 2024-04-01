import java.util.*;

class Solution {
    ArrayList<Integer> select = new ArrayList<>();    
    ArrayList<ArrayList<Integer>> key = new ArrayList<>();
    Set<String> set = new HashSet<>();
    int col, row, prev, answer;
    boolean isFound;

    public int solution(String[][] relation) {
        answer = 0;
        row = relation.length;
        col = relation[0].length;
        
        for(int i = 0; i < col; i++) {
            isFound = false;
            prev = 0;
            combination(relation, 0, 0, i + 1);
        }
        
        return answer;
    }
    
    void combination(String[][] relation, int next, int count, int N) {
        // Base Case
        if(count == N) {
            if(proceed(relation)) {
                answer++;
            }
            return;
        }
        
        // Recursive Case
        for(int i = next; i < col; i++) {
            select.add(i);
            combination(relation, i + 1, count + 1, N);
            select.remove(select.size() - 1);
        }
    }
    
    boolean proceed(String[][] relation) {
        set.clear();
        for(int r = 0; r < row; r++) {
            String str = "";
            for(int i = 0; i < select.size(); i++) {
                str += relation[r][select.get(i)];
            }
            set.add(str);
        }
        
        if(set.size() == row) {
            for(int i = 0; i < key.size(); i++) {
                if(select.containsAll(key.get(i))) {
                    return false;
                }
            }
            key.add((ArrayList<Integer>)select.clone());
            return true;
        }
        
        return false;
    }
    
    
}