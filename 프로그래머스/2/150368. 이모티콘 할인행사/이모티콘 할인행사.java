import java.util.*;

class Solution {
    
    static int[][] users;
    static int[] emoticons;
    static int[] sale = {10, 20, 30, 40};
    static int[] answer = new int[2];
    
    public int[] solution(int[][] users, int[] emoticons) {
        this.users = users;
        this.emoticons = emoticons;
        int[] rates = new int[emoticons.length];
        
        back(rates, 0);
        
        return answer;
    }
    
    public static void back(int[] rates, int depth){
        if(depth == rates.length){
            checkOptimal(rates);
            return;
        }
        
        for(int i=0; i<sale.length; i++){
            rates[depth] = sale[i];
            back(rates, depth+1);
        }
    }
    
    
    public static void checkOptimal(int[] rates){
        // 해당 할인율 조합에 대해서 총 계산을 한다.
        int total = 0;
        int count = 0;
        for(int i=0; i<users.length; i++){
            int userTotal = 0;
            for(int j=0; j<emoticons.length; j++){
                if(users[i][0]<=rates[j]){
                    userTotal += emoticons[j] - emoticons[j] * rates[j] / 100;
                    total += emoticons[j] - emoticons[j] * rates[j] / 100;
                }
            }
            if(userTotal>=users[i][1]){
                count++;
                total -= userTotal;
            }
        }
        if(count>answer[0]){
            answer[0] = count;
            answer[1] = total;
            return;
        }
        if(count == answer[0] && total > answer[1]){
            answer[0] = count;
            answer[1] = total;
            return;
        }
    }
}