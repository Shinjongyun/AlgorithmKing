import java.util.*;

class Solution {
    
    static int[] answer;
    
    public int[] solution(long[] numbers) {
        
        answer = new int [numbers.length];
        
        for(int i=0; i<numbers.length; i++){
            boolean flag = false;
            
            if(cal(numbers[i])) flag = true;
            
            if(flag) answer[i] = 1;
        }
        
        return answer;
    }
    
    public static boolean cal(long number){
        
        String str = Long.toBinaryString(number);
        int strSize = str.length();
        StringBuilder sb = new StringBuilder();
        
        int size = 0;
        while(size < strSize){
            size = size * 2 + 1;
        }
        
        while(size != strSize){
            sb.append("0");
            size--;
        }
        sb.append(str);
        
        int low = 0;
        int high = sb.length() - 1;
        return bin(sb, low, high);
    }
    
    public static boolean bin(StringBuilder sb, int low, int high){

        if(low>high){
            return true;
        }
        
        int mid = (low + high) / 2 ;
        
        if(sb.charAt(mid) == '0'){
            if(lookUp(low, mid -1, sb)) return false;
            if(lookUp(mid+1, high, sb)) return false;
        }
        
        return bin(sb, low, mid - 1) && bin(sb, mid + 1, high);
    }
    
    public static boolean lookUp(int start, int end, StringBuilder sb){
        for(int i=start; i<=end; i++){
            if(sb.charAt(i) == '1') return true;
        }
        return false;
    }
}