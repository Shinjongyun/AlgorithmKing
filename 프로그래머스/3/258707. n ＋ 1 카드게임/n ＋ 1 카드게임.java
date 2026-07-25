import java.util.*;

class Solution {
    
    static Set<Integer> hand = new HashSet<>();
    static Set<Integer> trash = new HashSet<>();
    static int n;
    static int target;
    
    public int solution(int coin, int[] cards) {
        
        n = cards.length;
        target = n + 1;
        for(int i=0; i<n/3; i++){
            hand.add(cards[i]);
        }
        
        int count = 1;
        for(int i=n/3; i<n; i+=2){
            
            int first = cards[i];
            int second = cards[i+1];
            
            // best 그냥 손에서 해결
            if(handChecking()){
                trash.add(first);
                trash.add(second);
                count++;
                continue; 
            }
            
            // 차선 : 동전 하나쓰고 해결
            if(coin - 1 >= 0){
                
                if(isValid(first)){
                    trash.add(second);
                    coin--;
                    count++;
                    continue;
                }
                
                if(isValid(second)){
                    trash.add(first);
                    coin--;
                    count++;
                    continue;
                }
                
                if(handTrashChecking()){
                    trash.add(first);
                    trash.add(second);
                    coin--;
                    count++;
                    continue;
                }
            }
            
            // 동전 2개쓰기, 쓰레기 더미 + 뽑기 or 뽑기s or 쓰레기 더미s
            if(coin - 2 >= 0){
                
                if(first + second == target){
                    coin -= 2;
                    count++;
                    continue;
                }
                
                if(isValidT(first)){
                    trash.add(second);
                    coin -= 2;
                    count++; 
                    continue;
                }
                
                if(isValidT(second)){
                    trash.add(first);
                    coin -= 2;
                    count++; 
                    continue;
                }
                
                if(trashChecking()){
                    trash.add(first);
                    trash.add(second);
                    coin -= 2;
                    count++; 
                    continue;
                }
            }
            
            // 라운드 패배
            break; 
        }
        return count;
    }
    
    public static boolean handTrashChecking(){
        
        int mem = 0;
        int mem1 = 0;
        boolean found = false;
        
        for(int i : hand){
            for(int j : trash){
                if(i + j == target){
                    mem = i;
                    mem1 = j; 
                    found = true;
                    break;
                }
            }
            if(found) break;
        }
        
        if(found){
            hand.remove(mem);
            trash.remove(mem1);
            return true;
        }
        return false; 
        
    }
    
    public static boolean isValidT(int num){
        if(trash.contains(target - num)){
            trash.remove(target - num); 
            return true;
        }
        return false;
    }
    
    public static boolean trashChecking(){
        
        int mem = 0;
        int mem1 = 0;
        boolean found = false;
        
        for(int i : trash){
            
            if(trash.contains(target - i)){
                
                mem = i;
                mem1 = target - i;
                found = true;
                break;
                
            } 
        }
        
        if(found){
            trash.remove(mem);
            trash.remove(mem1);
            return true;
        }
        return false; 
    }
    
    public static boolean handChecking(){
        
        int mem =0 ;
        int mem1 = 0;
        boolean found = false;
        
        for(int i : hand){
            
            if(hand.contains(target - i)){
                
                mem = i;
                mem1 = target - i;
                found = true;
                break;
                
            } 
        }
        
        if(found){
            hand.remove(mem);
            hand.remove(mem1);
            return true;
        }
        return false; 
    }
    
    public static boolean isValid(int num){
        
        if(hand.contains(target - num)){
            hand.remove(target - num); 
            return true;
        }
        return false;
    }
}