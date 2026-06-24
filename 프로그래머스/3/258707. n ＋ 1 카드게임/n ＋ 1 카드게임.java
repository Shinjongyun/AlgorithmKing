import java.util.*;

class Solution {
    
    static int coin;
    static int[] cards;
    static int n;
    static Set<Integer> hand = new HashSet<>(); 
    static Set<Integer> hubo = new HashSet<>(); 
    
    public int solution(int coin, int[] cards) {
        
        this.coin = coin;   
        this.cards = cards;
        n = cards.length;
        
        for(int i=0; i<n/3; i++){
            hand.add(cards[i]);    
        }
        
        int round = 1;
        for(int step = n/3; step+1<cards.length; step+=2){
            
            int first = cards[step];
            int second = cards[step+1];
            hubo.add(first);
            hubo.add(second);
            
            // 손안에서 해결이 가능한 경우 - 가장 좋은 경우 
            if(check()) {
                round++;
                continue;
            }
            
            boolean flag = false;
            
            // 한장만 되는 경우
            for(int cur : hubo){
                
                int target = n+1-cur;
                if(coin > 0 && hand.contains(target)){
                    coin--;
                    round++;
                    flag = true;
                    hubo.remove(cur);
                    hand.remove(target);
                    break;
                }
            }
            
             if (flag) continue;
            
            // 손에 잇는 놈끼리 되는 결루
            for(int cur:hubo){
                
                int target = n+1-cur;
                if(coin - 1 >0 && hubo.contains(target)){
                    coin-=2;
                    round++;
                    flag = true;
                    hubo.remove(cur);
                    hubo.remove(target);
                    break;
                }
            }
            
            // flag true면 다음 라운드
            if(flag) continue;
            
            break;
        }
        
        return round;
    }   
    
    public static boolean check(){
        for(int cur : hand){
            
            int target = n+1-cur;
            if(hand.contains(target)) {
                
                hand.remove(cur);
                hand.remove(target);
                
                return true;
            }
        }
        return false;
    }
}