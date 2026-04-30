import java.util.*;

class Solution {
    
    static int[] answer;
    
    public int[] solution(long[] numbers) {
        
        answer = new int [numbers.length];
        
        for(int i=0; i<numbers.length; i++){
            boolean flag = false;
            if(binary(numbers[i])) flag = true;
            if(flag) answer[i] = 1;
            else answer[i] = 0;
        }
        
        return answer;
    }
    
    public static boolean binary(long num){
        
        String m = Long.toBinaryString(num);
        StringBuilder sb = new StringBuilder();
        
        int size = 0;
        while(size < m.length()){
            size = size * 2 + 1;
        }  // size가 15
        
        while(size != m.length()){
            sb.append("0");
            size--;
        }
        sb.append(m);
        
        return binary(sb, 0, sb.length() - 1);
    }
    
    public static boolean binary(StringBuilder num, int low, int high){
        
        if(low >= high){
            return true;
        }
        
        int mid = (low + high) / 2;
        
        if(num.charAt(mid) == '0'){
            if(!cal(low, mid - 1, num)) return false;
            if(!cal(mid + 1, high, num)) return false;
        }
        
        return binary(num, mid + 1, high) && binary(num, low, mid - 1);
    }
    
    public static boolean cal(int low, int high, StringBuilder num){
        
        for(int i=low; i<=high; i++){
            if(num.charAt(i) == '1') return false;
        }
        return true;
    }
}