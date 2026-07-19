import java.util.*;

class Solution {

    static List<Edge>[] graph; 
    static int n;
    static int[] info;
    static int answer = 0;
    static boolean[] visited;
    
    static class Edge{
        int to;
        public Edge(int to){
            this.to = to;
        }
    }
    
    public int solution(int[] info, int[][] edges) {
        
        this.info = info;
        n = info.length;
        
        visited = new boolean [n];
        graph = new List[n];
        for(int i=0; i<n; i++){
            graph[i] = new ArrayList<>(); 
        }
        
        for(int[] e : edges){
            int from = e[0];
            int to = e[1];
            
            graph[from].add(new Edge(to));
        }
        
        List<Integer> list = new ArrayList<>();
        dfs(0, 0, 0, list);
        return answer;
    }
    
    public static void dfs (int cur, int sCount, int wCount, List<Integer> past){
        
        List<Integer> list = new ArrayList<>(past);
        
        if(info[cur] == 0) sCount++;
        else wCount++; 
        
        answer = Math.max(answer, sCount); 
        
        // 안 왔으면 후보에 넣기
        for(Edge e : graph[cur]){
            if(!visited[e.to]){
                list.add(e.to);
            }
        }
        
        for(int i=0; i<list.size(); i++){
            
            if(visited[list.get(i)]) continue;
            
            // 다음 갈 곳이 늑대인 경우
            if(info[list.get(i)] == 1 && sCount - wCount == 1) continue;
            
            visited[list.get(i)] = true;
            dfs(list.get(i), sCount, wCount, list);
            visited[list.get(i)] = false;
        }
    }
}