import java.util.*;

class Solution {
    
    static int answer = 0;
    static List<Edge>[] graph;
    static int[] typeList;
    static int K, N;
    static int start;
    
    static class Edge{
        
        int to;
        int type;
        public Edge (int to, int type){
            this.to=to;
            this.type=type;
        }
    }
    
    public int solution(int n, int infection, int[][] edges, int k) {
        
        K=k;
        N=n;
        start = infection;
        
        graph = new List[n + 1];
        for(int i=1; i<=n; i++){
            graph[i] = new ArrayList<>();
        }
        typeList = new int[k+1];
        
        for(int i=0; i<edges.length; i++){
            graph[edges[i][0]].add(new Edge(edges[i][1], edges[i][2]));
            graph[edges[i][1]].add(new Edge(edges[i][0], edges[i][2]));
        }
        
        back(1);
        
        return answer;
    }
    
    public static void back(int depth){
        
        if(depth == K+1){
            int count;
            count = bfs(start);
            answer = Math.max(answer, count);
            return;
        }
        
        for(int i = 1; i<=3; i++){
            typeList[depth] = i;
            back(depth+1);
            typeList[depth] = 0;
        }
    }
    
    public static int bfs(int start){

        boolean[] visited = new boolean[N + 1];
        visited[start] = true;

        for(int step = 1; step <= K; step++){
            Queue<Integer> q = new LinkedList<>();

            for(int i = 1; i <= N; i++){
                if(visited[i]) q.add(i);
            }

            while(!q.isEmpty()){
                int cur = q.poll();

                for(Edge e : graph[cur]){
                    if(!visited[e.to] && e.type == typeList[step]){
                        visited[e.to] = true;
                        q.add(e.to);
                    }
                }
            }
        }

        int count = 0;
        for(int i = 1; i <= N; i++){
            if(visited[i]) count++;
        }
        return count;
    }
}