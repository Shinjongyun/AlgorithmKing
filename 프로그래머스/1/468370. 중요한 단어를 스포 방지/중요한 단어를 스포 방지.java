import java.util.*;

class Solution {
    
    static int answer;
    static Set<String> set = new HashSet<>();
    static boolean[] isSpoiler;
    
    public int solution(String message, int[][] spoiler_ranges) {
        
        StringBuilder sb = new StringBuilder(message);
        isSpoiler = new boolean[sb.length()];
        
        for(int[] s: spoiler_ranges){
            int x = s[0];
            int y = s[1]; 
            
            for(int i=x; i<=y; i++){
                isSpoiler[i] = true;
            }
        }
        
        // 한글자씩 돌아가기
        for(int i=0; i<sb.length(); i++){
            
            // 빈칸이 아니고, 스포도 아닌 경우
            if(sb.charAt(i) != ' '){
                
                boolean noSpo = true;
                StringBuilder word = new StringBuilder();
                while(i < sb.length() && sb.charAt(i) != ' '){
                    
                    if(isSpoiler[i]) noSpo = false;
                    word.append(sb.charAt(i));
                    i++;
                }
                
                if(noSpo && !set.contains(word.toString())) set.add(word.toString());
            }
        }
        
        // 이제 스포 단어 찾으면서 세기
        int count = 0;
        for(int i=0; i<sb.length(); i++){
            
            if(isSpoiler[i] && sb.charAt(i) != ' '){
                int sI = i;
                int eI = i;
                for(int j=i; j>=0; j--){
                    if(sb.charAt(j) == ' ') {
                        sI = ++j;
                        break; 
                    }
                    if(j==0) sI=j;
                }
                
                for(int j=i; j<sb.length(); j++){
                    if(sb.charAt(j) == ' '){ 
                        eI = --j;
                        break; 
                    }
                    if(j==sb.length()-1) eI = j;
                }
                
                StringBuilder spoilerWord = new StringBuilder();
                for(int j=sI; j<=eI; j++){
                    
                    spoilerWord.append(sb.charAt(j));
                    
                }
                
                String[] spoilers = spoilerWord.toString().split(" ");
                
                for(int l=0; l<spoilers.length; l++){
                    if(!set.contains(spoilers[l])){
                        count++;
                        set.add(spoilers[l]);
                    } 
                }
                i = eI+1;
            }
        }
        return count;
    }
}