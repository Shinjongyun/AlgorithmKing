import java.util.*;

class Solution {
    
    static int answer =  0;
    static List<Dart> list;
    
    static class Dart{
        int score;
        char bonus;
        int option;
        public Dart(int s, char b){
            this.score = s;
            this.bonus = b;
            this.option = -1;
        }
        public Dart(int s, char b, int o){
            this.score = s;
            this.bonus = b;
            this.option = o;
        }
    }
    
    public int solution(String dartResult) {
        
        list = new ArrayList<>();
        
        StringBuilder sb = new StringBuilder(dartResult);
        for(int i=0; i<dartResult.length();){
            int s;

            // 점수 읽기
            if (i + 1 < dartResult.length() && sb.charAt(i) == '1' && sb.charAt(i + 1) == '0') {
                s = 10;
                i += 2;
            } else {
                s = sb.charAt(i) - '0';
                i++;
            }

            // 보너스 읽기
            char b = sb.charAt(i);
            i++;

            // 옵션 읽기
            int o = -1;

            if (i < dartResult.length() && (sb.charAt(i) == '*' || sb.charAt(i) == '#')) {
                if (sb.charAt(i) == '*') {
                    o = 1;
                } else {
                    o = 2;
                }
                i++;
            }

            list.add(new Dart(s, b, o));
            
        }
        
        boolean flag = false;
        for(int i=list.size()-1; i>=0; i--){
            Dart cur = list.get(i);
            
            int r = 0;
            if(cur.bonus == 'S'){
                r += cur.score;
            }
            if(cur.bonus == 'D'){
                r += Math.pow(cur.score, 2);
            }
            if(cur.bonus == 'T'){
                r += Math.pow(cur.score, 3);
            }
            if(flag){
                flag = false;
                r *= 2;
            }
            if(cur.option ==1) {
                r *= 2;
                flag = true;
            }
            if(cur.option ==2) r *= -1;
            answer += r; 
        }
                        
        return answer;
    }
}