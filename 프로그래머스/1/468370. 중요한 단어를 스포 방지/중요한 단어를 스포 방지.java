import java.util.*;

class Solution {
    
    static int answer = 0;
    static Set<String> noSpo =  new HashSet<>();
    static String[] spoilers;
    static StringBuilder sb;
    static StringBuilder spoSb;
    static boolean[] isSpo;
    
    public int solution(String message, int[][] spoiler_ranges) {
        
        sb = new StringBuilder(message);
        spoSb = new StringBuilder();
        isSpo = new boolean[message.length()];
        
        // 스포 구간 배치
        for(int[] cur : spoiler_ranges){
            int start = cur[0];
            int end = cur[1];
            for(int i = start; i<=end; i++){
                isSpo[i]  =true;
            }
        }
        
        // 스포 아닌 단어들 set에 넣기
        for(int i=0; i<message.length();){
            int end=i;
            while(true){
                if(end == message.length() -1) break;
                if(sb.charAt(end) == ' '){
                    end--;
                    break;
                }
                end++;
            }
            
            // 먼저 스포가 포함되어 있는 지 체크
            boolean flag = false;
            for(int k = i; k<= end; k++){
                if(isSpo[k]) flag = true;
            }
            if(flag) {
                i = end + 2; 
                continue;
            }
            
            // 스포가 포함되어 있지 않다면 단어 추출
            StringBuilder word = new StringBuilder();
            for(int k =i; k<=end; k++){
                word.append(sb.charAt(k));
            }
            
            String w = word.toString();
            if(!noSpo.contains(w)) noSpo.add(w);
            i = end + 2;
        }
        
        // 스포일러 단어들 추출
        for(int[] cur : spoiler_ranges){
            int start = cur[0];
            int end = cur[1];
            
            int sIdx = start;
            while(true){
                if(start == 0){
                    sIdx = 0;
                    break;
                }
                if(sb.charAt(start - 1) == ' '){
                    sIdx = start;
                    break;
                }
                start--;
            }
            
            int eIdx = end;
            while(true){
                if(end == message.length() - 1){
                    eIdx = message.length() -1;
                    break;
                }
                if(sb.charAt(end) == ' '){
                    eIdx = end -1;
                    break;
                }
                end++;
            }
            
            for(int i=sIdx; i<=eIdx; i++){
                spoSb.append(sb.charAt(i));
            }
            spoSb.append(" ");
        }
        spoilers = spoSb.toString().split(" ");
        
        // 돌면서 스포일러 처리하기
        for(String spoiler : spoilers){
            if(!noSpo.contains(spoiler)){
                answer++;
                noSpo.add(spoiler);
            }
        }
        return answer;
    }
}