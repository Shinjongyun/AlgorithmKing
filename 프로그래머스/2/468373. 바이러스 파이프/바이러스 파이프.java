import java.util.*;

class Solution {
    
    static int k; 
    static int n;
    static List<Edge>[] graph;
    static int answer = 0;
    static int infection;

    static class Edge{
        int to;
        int type;
        public Edge(int t, int type){
            this.to = t;
            this.type = type;
        }
    }
    
    public int solution(int n, int infection, int[][] edges, int k) {
        
        this.n = n;
        this.k = k;
        this.infection = infection; 
        
        graph = new List[n+1];
        for(int i=1; i<=n; i++){
            graph[i] = new ArrayList<>();
        }
        
        for(int [] e : edges){
            int from = e[0];
            int to = e[1];
            int type = e[2];
            graph[from].add(new Edge(to, type));
            graph[to].add(new Edge(from, type));
        }
        
        int[] choice = new int[k];
        back(0, choice);
        return answer;
    }
    
    public static void back(int depth, int[] choice){
        
        if(depth == k){
            int count = cal(choice);
            answer = Math.max(answer, count);
            return;
        }
        
        for(int i= 1; i<=3; i++){
            choice[depth] = i;
            back(depth + 1, choice);
            choice[depth] = 0; 
        }
    }
    
    public static int cal(int[] choice){
        
        Queue<Integer> q  = new LinkedList<>(); 
        boolean[] visited = new boolean[n+1];
        visited[infection] = true;
        
        for(int i=0; i<k; i++){
            
            for(int j=1; j<=n; j++){
                if(visited[j]) q.add(j);
            }
            
            int thisType = choice[i];
            
            while(!q.isEmpty()){
            
                int cur = q.poll();

                for(Edge e : graph[cur]){
                    if(!visited[e.to] && e.type == thisType){
                        visited[e.to] = true;
                        q.add(e.to);
                    }
                }
            }
        }
        
        int count = 0;
        for(int i=1; i<=n; i++){
            if(visited[i]) count++;
        }
        return count;
    }
}