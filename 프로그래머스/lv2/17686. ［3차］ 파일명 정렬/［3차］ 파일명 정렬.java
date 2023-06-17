import java.util.*;

class Solution {
    public ArrayList<String> solution(String[] files) {
        ArrayList<String> answer = new ArrayList<>();
        ArrayList<File> result = new ArrayList<>();
        
        for(int i=0;i<files.length;i++){
            int head_index = getHeadIndex(files[i]);
            int number_index = getNumberIndex(files[i], getHeadIndex(files[i]));
            
            String head = files[i].substring(0, head_index);
            String number = files[i].substring(head_index, number_index);  
            String tail = files[i].substring(number_index, files[i].length());
            
            result.add(new File(head, number, tail));
        }
            
        Collections.sort(result, new Comparator<File>() {
            @Override
            public int compare(File f1, File f2) {
                if(f1.head.compareToIgnoreCase(f2.head) < 0)
                    return -1;
                else if(f1.head.compareToIgnoreCase(f2.head) == 0)
                    return Integer.compare(f1.number, f2.number);
                else
                    return 1;
            }
        });
        
        for(File f : result){
            answer.add(f.head + f.number_str + f.tail);
        }
        
        return answer;
    }
    
    public int getHeadIndex(String str){
        for(int i = 0;i<str.length();i++){
            if(str.charAt(i) - '0' >= 0 && str.charAt(i) - '0' <= 9){
                return i;
            }
        }
        return str.length();
    }
    
    public int getNumberIndex(String str, int start_index){
        for(int i = start_index;i<str.length();i++){
            if(str.charAt(i) - '0' < 0 || str.charAt(i) - '9' > 9)
                return i;
        }
        return str.length();
    }
    
    
    class File {
        String head;
        String number_str;
        int number;
        String tail;
        
        File(String head, String number, String tail){
            this.head = head;
            this.number_str = number;
            this.number = Integer.parseInt(number);
            this.tail = tail;
        }
    }
}