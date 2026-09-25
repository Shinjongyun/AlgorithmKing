import java.util.*;

class Solution {
    
    List<Integer> list = new ArrayList<>();
    
    public int[] solution(int[] progresses, int[] speeds) {
        
        Stack<Integer> stack = new Stack<>(); 
        
        for(int i=0; i<progresses.length; i++){
            int dist = 100 - progresses[i]; 
            int d = dist % speeds[i];
            int d1 = dist / speeds[i];
            if(d != 0) d1++; 
            
            if(stack.isEmpty()){
                stack.push(d1);
                list.add(1); 
                continue; 
            }
            int until = stack.peek(); 
            if(until >= d1){
                list.set(list.size() - 1, list.get(list.size() - 1) + 1);
            }
            else{
                stack.push(d1); 
                list.add(1); 
            }
        }
        int idx = 0; 
        int[] answer = new int [list.size()];
        for(int a : list){
            answer[idx++] = a;
        }
        
        return answer;
    }
}