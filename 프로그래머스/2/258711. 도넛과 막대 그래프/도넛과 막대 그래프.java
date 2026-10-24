import java.util.*;

class Solution {
    
    static int[] answer; 
    static int[] indegree; 
    static int n; 
    static List<Edge>[] graph; 
    
    class Edge{
        int to; 
        boolean visited; 
        
        Edge(int t){
            this.to = t; 
            this.visited = false; 
        }
    }
    
    public int[] solution(int[][] edges) {
        answer = new int [4]; 
        
        n = 1; 
        for(int [] e : edges){
            int um = Math.max(e[0], e[1]);
            n = Math.max(n, um); 
        }
        indegree = new int [n+1]; 
        
        graph = new List[n+1]; for(int i=1; i<=n; i++) graph[i] = new ArrayList<>(); 
        
        int[] isYou = new int [n+1];
        boolean[] isYou1 = new boolean[n+1];
        
        for(int [] e : edges){
            
            int from = e[0];
            int to = e[1];
            isYou1[to] = true; 
            isYou[from]++;
            
            graph[from].add(new Edge(to)); 
            indegree[to]++;
            
        }
        
        for(int i=1; i<=n; i++){
            if(isYou[i] >= 2 && !isYou1[i]){
                answer[0] = i;
                break;
            }
        }
        
        for (Edge ed : graph[answer[0]]) {

            ed.visited = true;

            Queue<Integer> q = new LinkedList<>();

            boolean isEight = false;
            boolean isStick = false;

            q.add(ed.to);

            while (!q.isEmpty()) {

                int cur = q.poll();

                // 막대 그래프
                if (graph[cur].size() == 0) {
                    isStick = true;
                }

                // 8자 그래프
                if (graph[cur].size() == 2) {
                    isEight = true;
                }

                for (Edge e : graph[cur]) {

                    if (e.visited) continue;

                    e.visited = true;
                    q.add(e.to);
                }
            }

            // BFS 종료 후 그래프 종류 결정
            if (isEight) {
                answer[3]++;
            }
            else if (isStick) {
                answer[2]++;
            }
            else {
                answer[1]++;
            }
        }
        
        return answer;
    }
}