import java.util.*;

class Solution {
    
    static String answer;
    static StringBuilder sb = new StringBuilder();
    
    public String solution(int[] numbers, String hand) {
        
        int exRight = -1;
        int exLeft = -1;
        for(int i=0; i<numbers.length; i++){
            if(numbers[i] == 1 || numbers[i] == 4 || numbers[i] == 7){
                exLeft = numbers[i];
                sb.append("L");
            } 
            if(numbers[i] == 3 || numbers[i] == 6 || numbers[i] == 9){
                exRight = numbers[i];
                sb.append("R");
            } 
            if(numbers[i] == 2 || numbers[i] == 5 || numbers[i] == 8 || numbers[i] == 0){
                // 거리 기준
                int right = getRightDis(numbers[i], exRight);
                int left = getLeftDis(numbers[i], exLeft);
                
                if(left>right) {
                    sb.append("R");
                    exRight = numbers[i];
                } else if(left < right) {
                    sb.append("L");
                    exLeft = numbers[i];
                } 
                // 같은 경우
                else{
                    if(hand.equals("right")){
                        sb.append("R");
                        exRight = numbers[i];
                    } else{
                        sb.append("L");
                        exLeft = numbers[i];
                    }
                }
            }
        }
        
        answer = sb.toString();
        return answer;
    }
    
    public static int getRightDis(int num, int ex){
        
        if(ex == -1){
            if(num == 2) return 4;
            if(num == 5) return 3;
            if(num == 8) return 2;
            if(num == 0) return 1;
        }
        
        if(num == 2){
            if(ex == 3) return 1;
            if(ex == 6) return 2;
            if(ex == 9) return 3;
            if(ex == 2) return 0;
            if(ex == 5) return 1;
            if(ex == 8) return 2;
            if(ex == 0) return 3;
        }
        if(num == 5){
            if(ex == 3) return 2;
            if(ex == 6) return 1;
            if(ex == 9) return 2;
            if(ex == 2) return 1;
            if(ex == 5) return 0;
            if(ex == 8) return 1;
            if(ex == 0) return 2;
        }
        if(num == 8){
            if(ex == 3) return 3;
            if(ex == 6) return 2;
            if(ex == 9) return 1;
            if(ex == 2) return 2;
            if(ex == 5) return 1;
            if(ex == 8) return 0;
            if(ex == 0) return 1;
        }
        if(num == 0){
            if(ex == 3) return 4;
            if(ex == 6) return 3;
            if(ex == 9) return 2;
            if(ex == 2) return 3;
            if(ex == 5) return 2;
            if(ex == 8) return 1;
            if(ex == 0) return 0;
        } 
        return 0;
    }
    
    public static int getLeftDis(int num, int ex){
        
        if(ex == -1){
            if(num == 2) return 4;
            if(num == 5) return 3;
            if(num == 8) return 2;
            if(num == 0) return 1;
        }
        
        if(num == 2){
            if(ex == 1) return 1;
            if(ex == 4) return 2;
            if(ex == 7) return 3;
            if(ex == 2) return 0;
            if(ex == 5) return 1;
            if(ex == 8) return 2;
            if(ex == 0) return 3;
        }
        if(num == 5){
            if(ex == 1) return 2;
            if(ex == 4) return 1;
            if(ex == 7) return 2;
            if(ex == 2) return 1;
            if(ex == 5) return 0;
            if(ex == 8) return 1;
            if(ex == 0) return 2;
        }
        if(num == 8){
            if(ex == 1) return 3;
            if(ex == 4) return 2;
            if(ex == 7) return 1;
            if(ex == 2) return 2;
            if(ex == 5) return 1;
            if(ex == 8) return 0;
            if(ex == 0) return 1;
        }
        if(num == 0){
            if(ex == 1) return 4;
            if(ex == 4) return 3;
            if(ex == 7) return 2;
            if(ex == 2) return 3;
            if(ex == 5) return 2;
            if(ex == 8) return 1;
            if(ex == 0) return 0;
        } 
        return 0;
    }
}