import java.util.*;

class Solution {
    
    static Map<String, Integer> map = new HashMap<>();
    static int[] answer;
    static int baseTime; // 기본시간
    static int base; // 기본 요금
    static int cycleTime; // 단위 시간
    static int cycle; // 단위 요금
    static boolean[] visited;
    static int last = 23 * 60 + 59;
    
    public int[] solution(int[] fees, String[] records) {
        
        // 여기가 반대였음
        baseTime = fees[0];
        base = fees[1];
        cycleTime = fees[2];
        cycle = fees[3];

        map.clear();
        visited = new boolean[records.length];
        
        for(int i=0; i<records.length; i++){
            if(visited[i]) continue;
            
            String[] in = records[i].split(" ");
            visited[i] = true;
            
            boolean find = false;
            
            for(int j=i+1; j<records.length; j++){
                if(visited[j]) continue;
                
                String[] out = records[j].split(" ");
                
                if(out[1].equals(in[1]) && out[2].equals("OUT")){
                    int m = cal(in[0], out[0], in[1]);
                    
                    map.put(in[1], map.getOrDefault(in[1], 0) + m);
                    
                    visited[j] = true;
                    find = true;
                    break;
                }
            }
            
            // 출차 기록이 없는 경우
            if(!find){
                int m = cal2(in[0], in[1]);
                map.put(in[1], map.getOrDefault(in[1], 0) + m);
            }
        }
        
        List<String> list = new ArrayList<>(map.keySet());
        Collections.sort(list);
        
        answer = new int[list.size()];
        
        for(int i=0; i<list.size(); i++){
            int time = map.get(list.get(i));
            answer[i] = getFee(time);
        }
        
        return answer;
    }
    
    // 여기서는 요금이 아니라 주차 시간만 반환해야 함
    public static int cal(String in, String out, String who){
        int inTime = toTime(in);
        int outTime = toTime(out);
        
        int dist = outTime - inTime;
        
        return dist;
    }
    
    // 23:59 출차 처리
    public static int cal2(String in, String who){
        int inTime = toTime(in);
        
        int dist = last - inTime;
        
        return dist;
    }
    
    // 총 주차 시간으로 요금 계산
    public static int getFee(int dist){
        int count = base;
        
        if(dist <= baseTime){
            return count;
        }
        
        int mock = (dist - baseTime) / cycleTime;
        
        if((dist - baseTime) % cycleTime != 0){
            mock++;
        }
        
        count += mock * cycle;
        
        return count;
    }
    
    public static int toTime(String time){
        int output = 0;
        String[] times = time.split(":");
        
        int hour = Integer.parseInt(times[0]) * 60;
        int min = Integer.parseInt(times[1]);
        
        output = hour + min;
        return output;
    }
}