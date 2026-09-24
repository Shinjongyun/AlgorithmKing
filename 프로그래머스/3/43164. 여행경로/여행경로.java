import java.util.*;

class Solution {
    
    static int n;
    static String[][] tickets;
    static String[] answer;
    
    public String[] solution(String[][] tickets) {
        
        this.tickets = tickets;
        n = tickets.length;
        
        boolean[] visited = new boolean[n];
        
        // 티켓 n장 -> 지나가는 공항 n+1개
        String[] route = new String[n + 1];
        
        // 무조건 ICN에서 시작
        route[0] = "ICN";
        
        dfs(1, visited, route);
        
        return answer;
    }
    
    public static void dfs(int depth, boolean[] visited, String[] route) {
        
        // 모든 티켓을 사용함
        if(depth == n + 1) {
            
            if(answer == null) {
                answer = route.clone();
                return;
            }
            
            // 현재 route가 answer보다 사전순으로 앞서는지 비교
            for(int i = 0; i < route.length; i++) {
                
                int compare = route[i].compareTo(answer[i]);
                
                if(compare < 0) {
                    answer = route.clone();
                    return;
                }
                
                if(compare > 0) {
                    return;
                }
            }
            
            return;
        }
        
        for(int i = 0; i < n; i++) {
            
            if(visited[i]) continue;
            
            String from = tickets[i][0];
            String to = tickets[i][1];
            
            // 현재 공항과 출발 공항이 같아야 함
            if(route[depth - 1].equals(from)) {
                
                visited[i] = true;
                route[depth] = to;
                
                dfs(depth + 1, visited, route);
                
                visited[i] = false;
                route[depth] = null;
            }
        }
    }
}