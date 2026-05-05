import java.util.*;
import java.io.*;

class Solution {
    
    static int[] answer;
    static List<Integer> list = new ArrayList<>();
    static int now;
    static Map<String, Integer> map = new HashMap<>();
    
    public int[] solution(String today, String[] terms, String[] privacies) {
        
        now = toVal(today);
        
        for(String t : terms){
            
            String[] term = t.split(" ");
            
            map.put(term[0], Integer.parseInt(term[1]));
        }
        
        int idx = 1;
        for(String p : privacies){
            
            String[] pri = p.split(" ");
            
            int past = toVal(pri[0]);
            String type = pri[1];
            
            int dur = map.get(type) * 28 - 1;
            
            if(past + dur < now){
                list.add(idx);
            }
            idx++;
        }
        
        answer = new int [list.size()];
        idx = 0;
        for(int cur : list){
            answer[idx] = cur;
            idx++;
        }
        
        return answer;
    }
        
    public static int toVal(String t){
        String[] time = t.split("\\.");
        
        int count = 0;
        
        int y = (Integer.parseInt(time[0]) - 2000) * 12 * 28; 
        int h = (Integer.parseInt(time[1] )- 1)  * 28;
        int d = Integer.parseInt(time[2]);
        count = y + h + d;
        return count;
    }
}