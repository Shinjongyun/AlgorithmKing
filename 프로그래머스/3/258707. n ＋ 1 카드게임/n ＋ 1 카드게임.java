import java.util.*;

class Solution {
    
    static int answer = 0;
    static int n;
    static Set<Integer> hand = new HashSet<>();
    static Set<Integer> can = new HashSet<>();
    static int left;
    
    public int solution(int coin, int[] cards) {
        
        n = cards.length;
        left = coin;
        
        // 손에 넣고
        for(int i= 0; i<n/3; i++){
            hand.add(cards[i]);
        }
        
        // 게임 시작
        int round = 1;
        for(int i = n/3; i<cards.length; i+=2){
            
            int one = cards[i];
            int two = cards[i+1];
            
            can.add(one);
            can.add(two);
            
            // 코인 0개
            if(removePair(hand, hand)){
                round++;
            }
            // 코인 1개
            else if (coin >= 1 && removePair(hand, can)){
                round++;
                coin--;
            }
            // 코인 2개
            else if (coin >=2 && removePair(can, can)){
                round++;
                coin = coin - 2;
            }
            
            // 코인도 안돼, 페어도 없어
            else {
                break;
            }
        }
        answer = round;
        return answer;
    }
    
    public static boolean removePair(Set<Integer> a, Set<Integer> b){
        for(int n1 : a){
            
            int target = n + 1 - n1;
            
            if(b.contains(target)){
                a.remove(n1);
                b.remove(target);
                
                return true;
            }
        }
        return false;
    }
}