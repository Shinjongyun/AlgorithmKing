import java.util.*;

class Solution {
    
    static int answer = 0;
    static String[] one;
    static String[] two; 
    Map<String, Integer> map1 = new HashMap<>();
    Map<String, Integer> map2 = new HashMap<>();
        
    static int gyo;
    static int hap;
    
    public int solution(String str1, String str2) {
        
        one = new String[str1.length() -1];
        two = new String[str2.length() -1];
        
        
        StringBuilder sb = new StringBuilder(str1);
        for(int i=0; i<str1.length() -1; i++){
            StringBuilder word = new StringBuilder();
            word.append(sb.charAt(i)).append(sb.charAt(i+1));
            one[i] = word.toString();
        }
        
        StringBuilder sb1 = new StringBuilder(str2);
        for(int i=0; i<str2.length() -1; i++){
            StringBuilder word = new StringBuilder();
            word.append(sb1.charAt(i)).append(sb1.charAt(i+1));
            two[i] = word.toString();
        }
        
        validate1();
        validate2();
        
        for(String s : one){
            
            if(s.equals("")) continue;
            
            if(!map1.containsKey(s)) map1.put(s, 1);
            else{
                map1.put(s, map1.get(s) + 1);
            }
        }
        
        for(String s : two){
            
            if(s.equals("")) continue;
            
            if(!map2.containsKey(s)) map2.put(s, 1);
            else{
                map2.put(s, map2.get(s) + 1);
            }
        }
        
        for (Map.Entry<String, Integer> e1 : map1.entrySet()) {
                
                String oneKey = e1.getKey();
                int oneValue = e1.getValue();
                
                // one 에만 있는 경우
                if(!map2.containsKey(oneKey)) {
                    hap += oneValue;
                }
                // 둘 다 있는 경우           
                else {
                    gyo += Math.min(map1.get(oneKey), map2.get(oneKey));
                    hap += Math.max(map1.get(oneKey), map2.get(oneKey));
                }
        }
        
        for (Map.Entry<String, Integer> e2 : map2.entrySet()) {
                
                String twoKey = e2.getKey();
                int twoValue = e2.getValue();
                
                // two 에만 있는 경우
                if(!map1.containsKey(twoKey)) {
                    hap += twoValue;
                }
        }
        
    
        if (hap == 0) return 65536;

        answer = gyo * 65536 / hap;
        return answer;
    }
    
    public static void validate1() {
        for (int i = 0; i < one.length; i++) {
            String s = one[i].toUpperCase();

            char a = s.charAt(0);
            char b = s.charAt(1);

            if (a < 'A' || a > 'Z' || b < 'A' || b > 'Z') {
                one[i] = "";
            } else {
                one[i] = s;
            }
        }
    }
    
    public static void validate2() {
        for (int i = 0; i < two.length; i++) {
            String s = two[i].toUpperCase();

            char a = s.charAt(0);
            char b = s.charAt(1);

            if (a < 'A' || a > 'Z' || b < 'A' || b > 'Z') {
                two[i] = "";
            } else {
                two[i] = s;
            }
        }
    }
}