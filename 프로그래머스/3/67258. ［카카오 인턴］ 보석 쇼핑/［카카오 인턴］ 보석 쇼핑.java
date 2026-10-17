import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        
        int[] answer = new int [2]; 
        
        Set<String> set = new HashSet<>(); 
        for(String g : gems){
            if(!set.contains(g)) set.add(g);
        }
        int target = set.size();
        
        Map<String, Integer> map = new HashMap<>();
        int left = 0; 
        int right = 1;

        int length = gems.length;
        answer[0] = 1;
        answer[1] = gems.length;
        
        map.put(gems[left], 1);
        while(right <= gems.length){
            int count = map.size();            
            
            if(count < target){
                if (right == gems.length) break;
                String next = gems[right];
                if(!map.containsKey(next)){
                    map.put(next, 1);
                }
                else{
                    map.put(next, map.get(next) + 1);
                }
                right++;
            }
            else{
                if(length > right - left){
                    answer[0] = left+1;
                    answer[1] = right;
                    length = right - left;
                }
                
                String past = gems[left];
                if(map.get(past) == 1){
                    map.remove(past);
                }
                else{
                    map.put(past, map.get(past) - 1);
                }
                left++;
            }
        }
        
        return answer;
    }
}