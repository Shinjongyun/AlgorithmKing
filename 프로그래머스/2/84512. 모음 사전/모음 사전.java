import java.util.*;

// 단어를 쌓고, 마지막 단어를 바꾸고, 줄이고 
class Solution {
    
    static Map<String, Integer> map = new HashMap<>();
    static char[] d = {'A', 'E', 'I', 'O', 'U'};
    static int idx = 1;
    
    public int solution(String word) {
        
        dfs(0, "");
        int answer = map.get(word);    
        
        return answer;
    }
    
    static void dfs(int depth, String num){
        
        if(depth == 5){
            // map.put(num, idx++);
            return;   
        }
        
        for(int i=0; i<5; i++){
            StringBuilder sb = new StringBuilder(num); 
            sb.append(d[i]);
            map.put(sb.toString(), idx++);
            dfs(depth+1, sb.toString());
            // System.out.print(sb.toString()+ " ");
        }
    }
}