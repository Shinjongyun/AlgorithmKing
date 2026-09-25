import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        
        Queue<int []> q = new LinkedList<>();  
        int idx = 0;
        
        for(int p : priorities){
            q.add(new int[] {idx++, p});
        }
        
        int[] arr = new int[priorities.length];
        int time = 1;
        while(!q.isEmpty()){
            Queue<int []> m = new LinkedList<>(q); 
            int[] cur = q.poll(); 
            
            boolean flag = false; 
            while(!m.isEmpty()){
                int[] d = m.poll(); 
                if(cur[1] < d[1]){
                    flag = true; 
                    break; 
                }
            }
            
            if(flag){
                q.add(cur); 
            }
            else{
                arr[cur[0]] = time; 
                time++;
            }
            
        }
        
        answer = arr[location];
        return answer;
    }
}