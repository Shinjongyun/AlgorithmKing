import java.util.*;
import java.io.*;

class Solution {
    
    static List<Integer>[] graph;
    static boolean[] visited;
    static int[][] wire;
    static int answer = Integer.MAX_VALUE;
    static int a;
    static int b;
    
    public int solution(int n, int[][] wires) {
        
        graph = new ArrayList[n + 1]; 
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int[] w : wires) {
            int a = w[0], b = w[1];
            graph[a].add(b);
            graph[b].add(a);
        }
    
        
        for (int[] w : wires) {
            int from = w[0], to = w[1];

            graph[from].remove(Integer.valueOf(to));
            graph[to].remove(Integer.valueOf(from));
            
            visited = new boolean[n+1];
            a = 0;
            b = 0;
            boolean flag = false;
            for(int j=1; j<=n; j++){
                if(!visited[j] && !flag) {
                    dfs(j);
                    flag = true;
                } else if (!visited[j] && flag){
                    dfs2(j);
                }
            }
            
            answer = Math.min(answer, Math.abs(a - b));
            graph[to].add(from);
            graph[from].add(to);  // 백트래킹
        }
        
        return answer;
    }

    public static void dfs(int i) {
        visited[i] = true;  
        a++;
        for (int next : graph[i]) {   
            if (!visited[next]) {     
                dfs(next);         
            }
        }
    }
    
    public static void dfs2(int i) {
        visited[i] = true; 
        b++;
        for (int next : graph[i]) {   
            if (!visited[next]) {     
                dfs2(next);         
            }
        }
    }
}