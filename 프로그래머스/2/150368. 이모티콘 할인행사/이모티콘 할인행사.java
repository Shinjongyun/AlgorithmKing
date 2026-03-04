import java.util.*;

class Solution {
    
    static int[][] users;
    static int[] emoticons;
    static int[] sales = {40, 30, 20, 10};
    static int[] answer = new int [2];
    static int n;
    static int m;
    static int[] rates;
    
    public int[] solution(int[][] users, int[] emoticons) {
    
        this.users = users;
        this.emoticons = emoticons;
        n = users.length;
        m = emoticons.length;
        rates = new int [m];
        
        dfs(0);
        return answer;
    }
    
    public static void dfs (int depth){
        if(depth == m){
            evaluate();
            return;
        }
        
        for (int sale : sales) {
            rates[depth] = sale;
            dfs(depth + 1);
        }
    }
    
    public static void evaluate(){
        int total = 0; // 최종 계산의 액수
        int count = 0; // 최종 계산의 가입자 수 
        
        // 사용자 별로 계산 
        for(int i=0; i<n; i++){
            int userTotal = 0;
            
            for(int j=0; j<m; j++){
                
                // 할인율이 최소 한정보다 작을 때, 볼것도 없음
                if(rates[j]<users[i][0]){
                    continue;
                }
                else {
                    userTotal += emoticons[j] - emoticons[j] * rates[j] /100;
                }
            }
            
            // 이제 가입자 수 계산 
            if (userTotal>=users[i][1]){
                count++;    // 가입해라
            }
            else{
                total += userTotal;
            }
        }
        
        // 최종 가입자 수 비교
        if(count > answer[0]){
            answer[0] = count;
            answer[1] = total;
        }
        
        // 최종 금액 비교
        if(count == answer[0] && total > answer[1]){
            answer[0] = count;
            answer[1] = total;
        }
    }
}