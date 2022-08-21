import java.util.*;

class Info{
    private String name1;
    private ArrayList<String> name2 = new ArrayList<>();
    private int count=0;
    
    Info(String n){
        this.name1 = n;
    }
    public void setName1(String name1){
        this.name1 = name1;
    }
    public void addName2(String name){ //신고한 사람 list
        for(int i=0;i<name2.size();i++)//중복 신고 처리
        {
            if(name.equals(name2.get(i)))
                return;
        }
        name2.add(name);
        addCount();
    }
    public void addCount(){
        this.count++;
    }
    
    public String getName1(){
        return this.name1;
    }
    public ArrayList<String> getName2(){
        return this.name2;
    }
    public int getCount(){
        return this.count;
    }
}
class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        String a,b;
        ArrayList<Info> map = new ArrayList<>();
        
        for(int i=0;i<id_list.length;i++)
        {   
            answer[i] = 0;
            Info info = new Info(id_list[i]);
            map.add(info);
        }
        
        StringTokenizer st;
        
        for(int i=0;i<report.length;i++){
            st = new StringTokenizer(report[i], " ");
            a = st.nextToken();
            b = st.nextToken();
            int end=0;

            for(int j=0;j<id_list.length;j++){
                if(b.equals(id_list[j]))
                    map.get(j).addName2(a);
            }
            
        }
        for(int i=0;i<id_list.length;i++){
            ArrayList<String> str = new ArrayList<>();
            if(map.get(i).getCount()>=k)
            {
                str = map.get(i).getName2();
                for(int j=0;j<id_list.length;j++)
                {
                    for(int l=0;l<str.size();l++)
                        if(str.get(l).equals(id_list[j]))
                            answer[j]++;
                }
                
            }
        }
        
        return answer;
    }
}