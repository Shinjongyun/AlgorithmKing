import java.util.*;

class Solution {
    
    class Edge{
        
        int to;
        int weight; 
        
        Edge(int t, int w){
            this.to = t;
            this.weight = w;
        }
        
    }
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = new int[2]; 
        
        List<Edge>[] graph = new List [n+1]; for(int i=1; i<=n; i++) graph[i] = new ArrayList<>();
        
        for(int [] p : paths){
            graph[p[0]].add(new Edge(p[1], p[2]));
            graph[p[1]].add(new Edge(p[0], p[2]));
        }
        
        boolean[] isGate = new boolean [n+1]; 
        boolean[] isSummit = new boolean [n+1]; 
        
        for(int i=0; i<gates.length; i++){
            isGate[gates[i]] = true; 
        }
        
        for(int i=0; i<summits.length; i++){
            isSummit[summits[i]] = true; 
        }
        
        int low = 1; 
        int high = 10000000;
        
        while(low <= high){
            
            int mid = (low + high) / 2;
            
            boolean flag = false;
            int target = 10000000;
            Queue<int []> q = new LinkedList<>();
            boolean[] visited = new boolean [n+1];
            for(int i=1; i<= n; i++){
                if(isGate[i]) {
                    q.add(new int [] {i, 0});
                    visited[i] = true; 
                }
            }
            
            while(!q.isEmpty()){
                
                int[] cur = q.poll();
                
                if(isSummit[cur[0]]){
                    flag = true; 
                    target = Math.min(target, cur[0]);
                    continue;
                }
                
                for(Edge e : graph[cur[0]]){
                    
                    int next = e.to;
                    
                    if(visited[next] || mid < e.weight) continue; 
                    
                    q.add(new int[] {next, e.weight}); 
                    visited[next] = true;
                }
            }   
                            
            if(flag){
                high = mid - 1;   
                answer[0] = target;
                answer[1] = mid;
            }
            else low = mid + 1;
        }
        
        return answer;
    }
}