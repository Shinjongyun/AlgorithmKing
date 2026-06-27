import java.util.*;

class Solution {
    
    static int[] answer;
    static int n; 
    
    public int[] solution(long[] numbers) {
        
        n = numbers.length;
        
        answer = new int [n];
        
        for(int i=0; i<n; i++){
            
            if(cal(numbers[i])) answer[i] = 1;
            else answer[i] = 0;
            
        }
        
        return answer;
    }
    
    public static boolean cal(long number){
        
        String num = Long.toBinaryString(number);
        StringBuilder sb = new StringBuilder(num);
        
        int total = 1;
        while(total < sb.length()){
            total = total * 2 + 1;
        }
        
        StringBuilder real = new StringBuilder();
        int diff = total - sb.length(); 
        for(int i=0; i<diff; i++){
            real.append("0"); 
        }
        
        real.append(num);
        
        return binary(real.toString(), 0, real.length() -1 );
    }
     
    public static boolean binary(String num, int low, int high){
        
        if(low > high) return true;
        
        int mid = (low + high) / 2;
        
        if(num.charAt(mid) == '0' && check(num, low, high)) return false;
        
        return binary(num, low, mid - 1) && binary(num, mid + 1, high);
    }
               
    public static boolean check(String num, int low, int high){
        for(int i=low; i<=high; i++){
            if(num.charAt(i) == '1') return true;
        }
        return false;
    }
}