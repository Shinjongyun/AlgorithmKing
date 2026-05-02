import java.util.*;

class Solution {
    
    static int answer = 0;
    static int n, m;
    static boolean[] isWolf;
    static boolean[][] graph;
    static boolean[] visited;
    
    public int solution(int[] info, int[][] edges) {
        
        n = info.length;
        answer = 0;
        
        isWolf = new boolean[n];
        visited = new boolean[n];
        
        for(int i = 0; i < n; i++){
            if(info[i] == 1) isWolf[i] = true;
        }
        
        graph = new boolean[n][n];
        
        for(int i = 0; i < edges.length; i++){
            int from = edges[i][0];
            int to = edges[i][1];
            
            // 부모 -> 자식 방향만 저장
            graph[from][to] = true;
        }
        
        List<Integer> list = new ArrayList<>();
        list.add(0);
        
        dfs(0, 0, list);
        
        return answer;
    }
    
    public static void dfs(int sheep, int wolf, List<Integer> can){
        
        for(int i = 0; i < can.size(); i++){
            
            int cur = can.get(i);
            
            int nextSheep = sheep;
            int nextWolf = wolf;
            
            if(!isWolf[cur]) nextSheep++;
            else nextWolf++;
            
            if(nextWolf >= nextSheep) continue;
            
            answer = Math.max(answer, nextSheep);
            
            List<Integer> nextCan = new ArrayList<>(can);
            
            // 이번에 방문한 노드는 후보에서 제거
            nextCan.remove(i);
            
            // 현재 노드의 자식들을 후보에 추가
            for(int j = 0; j < n; j++){
                if(graph[cur][j]){
                    nextCan.add(j);
                }
            }
            
            dfs(nextSheep, nextWolf, nextCan);
        }
    }
}